package dev.project.amqp.registration.user;

import dev.project.amqp.registration.user.model.Message;
import dev.project.amqp.registration.user.model.UserDto;
import dev.project.amqp.registration.user.model.UserMb;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

  UserMb dtoToEntity(UserDto userDto);

  Message mbToMessage(UserMb userMb);

}
