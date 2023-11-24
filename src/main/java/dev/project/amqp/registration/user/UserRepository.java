package dev.project.amqp.registration.user;

import dev.project.amqp.registration.user.model.UserMb;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserRepository {

  @Insert(
      """
          INSERT INTO person (firstname, lastname, email, status)
          VALUES (#{firstname}, #{lastname}, #{email}, #{status})
          """
  )
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertUser(UserMb user);

  @Update(
      """
          UPDATE person SET
          status = 'VERIFIED'
          WHERE email = #{email}
          """
  )
  void confirmUser(String email);

}
