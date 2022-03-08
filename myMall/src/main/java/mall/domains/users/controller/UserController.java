package mall.domains.users.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mall.domains.users.application.UserService;
import mall.domains.users.application.dto.TokenResponse;
import mall.domains.users.application.dto.UserRequest;
import mall.domains.users.domain.UsersEntity;


@RequiredArgsConstructor
@RestController
public class UserController {

  private final UserService userService;

  @PostMapping("/user/signUp")
  public ResponseEntity signUp(@RequestBody UserRequest userRequest) {
    return userService.findByUserId(userRequest.getUserId()).isPresent()
        ? ResponseEntity.badRequest().build()
        : ResponseEntity.ok(userService.signUp(userRequest));
  }

  @PostMapping("/user/signIn")
  public ResponseEntity<TokenResponse> signIn(@RequestBody UserRequest userRequest) throws Exception {

    return ResponseEntity.ok().body(userService.signIn(userRequest));
  }

  @GetMapping("/info")
  public ResponseEntity<List<UsersEntity>> findUser() {
    return ResponseEntity.ok().body(userService.findUsers());
  }
}
