package dev.project.amqp.registration.user;

import dev.project.amqp.registration.user.model.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Long> createMovie(
      @Valid() @RequestBody() UserDto userDto) {
    return ResponseEntity.ok()
        .body(userService.register(userDto));
  }

  @GetMapping("/action/validation/{jwt}")
  public String emailConfirmation(@PathVariable String jwt) {
    userService.confirmRegistration(jwt);
    return "redirect:/user/confirmation-success";
  }

  @GetMapping("/confirmation-success")
  public String confirmationSuccess() {
    return "confirmation";
  }

}
