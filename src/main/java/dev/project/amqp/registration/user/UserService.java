package dev.project.amqp.registration.user;

import dev.project.amqp.registration.user.model.Message;
import dev.project.amqp.registration.user.model.UserDto;
import dev.project.amqp.registration.user.model.UserMb;
import dev.project.amqp.registration.user.model.UserStatus;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final AmqpTemplate amqpTemplate;

  public UserService(UserMapper userMapper, UserRepository userRepository,
      AmqpTemplate amqpTemplate) {
    this.userMapper = userMapper;
    this.userRepository = userRepository;
    this.amqpTemplate = amqpTemplate;
  }

  public Long register(UserDto userDto) {
    var userMb = userMapper.dtoToEntity(userDto);
    userMb.setStatus(UserStatus.PENDING_VERIFICATION);
    userRepository.insertUser(userMb);
    verifyEmail(userMb);
    return userMb.getId();
  }

  private void verifyEmail(UserMb userMb) {
    Message message = userMapper.mbToMessage(userMb);
    amqpTemplate.convertAndSend("emailVerification", message);
  }

  public void confirmRegistration(String value) {
    userRepository.confirmUser(value);
  }
}
