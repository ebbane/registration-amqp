package dev.project.amqp.registration.user;

import dev.project.amqp.registration.user.model.UserDto;
import dev.project.amqp.registration.user.model.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

  UserEntity dtoToEntity(UserDto userDto);

}
