package dev.project.amqp.registration.user;

import dev.project.amqp.registration.user.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
