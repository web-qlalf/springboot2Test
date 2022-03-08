package mall.domains.users.application;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import mall.commons.Jwt.TokenUtils;
import mall.domains.auth.domain.AuthEntity;
import mall.domains.auth.domain.AuthRepository;
import mall.domains.users.application.dto.TokenResponse;
import mall.domains.users.application.dto.UserRequest;
import mall.domains.users.domain.UsersEntity;
import mall.domains.users.domain.UsersRepository;


public interface UserService {

	Object findByUserId(String userId) throws Exception;

	Object signUp(UserRequest userRequest) throws Exception;

	Object findUsers() throws Exception;

	Object signIn(UserRequest userRequest) throws Exception;

}
