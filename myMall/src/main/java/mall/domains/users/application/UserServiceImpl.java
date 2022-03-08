package mall.domains.users.application;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import mall.mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	  private final UsersRepository usersRepository; //mapper 역할
	  private final TokenUtils tokenUtils;
	  private final AuthRepository authRepository;
	  private final PasswordEncoder passwordEncoder;
	  private Logger log = LoggerFactory.getLogger(this.getClass());
		

	  public Optional<UsersEntity> findByUserId(String userId) {

	    return usersRepository.findByUserId(userId);
	  }

	  @Transactional
	  public TokenResponse signUp(UserRequest userRequest) {
	    UsersEntity usersEntity =
	        usersRepository.save(
	            UsersEntity.builder()
	                .pw(passwordEncoder.encode(userRequest.getUserPw()))
	                .userId(userRequest.getUserId())
	                .build());

	    String accessToken = tokenUtils.generateJwtToken(usersEntity);
	    String refreshToken = tokenUtils.saveRefreshToken(usersEntity);

	    authRepository.save(
	        AuthEntity.builder().usersEntity(usersEntity).refreshToken(refreshToken).build());

	    return TokenResponse.builder().ACCESS_TOKEN(accessToken).REFRESH_TOKEN(refreshToken).build();
	  }

	  @Transactional
	  public TokenResponse signIn(UserRequest userRequest) throws Exception {
	    UsersEntity usersEntity =
	        usersRepository
	            .findByUserId(userRequest.getUserId())
	            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
	    AuthEntity authEntity =
	        authRepository
	        	.findByUsersEntityId(usersEntity.getUsr_email())
	            .orElseThrow(() -> new IllegalArgumentException("Token 이 존재하지 않습니다."));
	    if (!passwordEncoder.matches(userRequest.getUserPw(), usersEntity.getUsr_pw())) {
	      throw new Exception("비밀번호가 일치하지 않습니다.");
	    }
	    String accessToken = "";
	    String refreshToken = authEntity.getRefreshToken();

	    if (tokenUtils.isValidRefreshToken(refreshToken)) {
	      accessToken = tokenUtils.generateJwtToken(authEntity.getUsersEntity());
	      return TokenResponse.builder()
	          .ACCESS_TOKEN(accessToken)
	          .REFRESH_TOKEN(authEntity.getRefreshToken())
	          .build();
	    } else {
	      accessToken = tokenUtils.generateJwtToken(authEntity.getUsersEntity());
	      refreshToken = tokenUtils.saveRefreshToken(usersEntity);
	      authEntity.refreshUpdate(refreshToken);
	    }

	    return TokenResponse.builder().ACCESS_TOKEN(accessToken).REFRESH_TOKEN(refreshToken).build();
	  }

	  public List<UsersEntity> findUsers() {
	    return usersRepository.findAll();
	  }
}
