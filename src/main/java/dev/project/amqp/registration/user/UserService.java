package dev.project.amqp.registration.user;

import dev.project.amqp.registration.user.model.UserDto;
import dev.project.amqp.registration.user.model.UserStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;

  public UserService(UserMapper userMapper, UserRepository userRepository) {
    this.userMapper = userMapper;
    this.userRepository = userRepository;
  }

  public Long register(UserDto userDto) {
    var userToSave = userMapper.dtoToEntity(userDto);
    userToSave.setStatus(UserStatus.CREATED);
    return userRepository.save(userToSave).getId();
  }
}
