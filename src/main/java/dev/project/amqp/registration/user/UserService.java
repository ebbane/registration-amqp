package dev.project.amqp.registration.user;

import dev.project.amqp.registration.user.model.Message;
import dev.project.amqp.registration.user.model.UserDto;
import dev.project.amqp.registration.user.model.UserMb;
import dev.project.amqp.registration.user.model.UserStatus;
import dev.project.amqp.registration.utils.JwtUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final AmqpTemplate amqpTemplate;
  private final JwtUtil jwtUtils;

  public UserService(UserMapper userMapper, UserRepository userRepository,
      AmqpTemplate amqpTemplate, JwtUtil jwtUtils) {
    this.userMapper = userMapper;
    this.userRepository = userRepository;
    this.amqpTemplate = amqpTemplate;
    this.jwtUtils = jwtUtils;
  }

  public Long register(UserDto userDto) {
    var userMb = userMapper.dtoToEntity(userDto);
    userMb.setStatus(UserStatus.PENDING_VERIFICATION);
    userRepository.insertUser(userMb);
    verifyEmail(userMb);
    return userMb.getId();
  }

  private void verifyEmail(UserMb userMb) {
    final String token = jwtUtils.generateJwtToken(userMb.getEmail());
    Message message = userMapper.mbToMessage(userMb, token);
    amqpTemplate.convertAndSend("emailVerification", message);
  }

  public void confirmRegistration(String jwt) {
    String email = jwtUtils.getEmailFromToken(jwt);
    userRepository.confirmUser(email);
  }
}
