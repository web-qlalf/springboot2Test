package mall.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mall.domain.CommonResponse;
import mall.domain.MemberDto;
import mall.exception.LoginFailedException;
import mall.service.JwtAuthToken;
import mall.service.LoginService;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public CommonResponse login(@RequestBody MemberDto loginRequestDTO) throws Exception {
    	System.out.println("CommonResponse login");
        Optional<MemberDto> optionalMemberDTO = loginService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPw());

        if (optionalMemberDTO.isPresent()) {

            JwtAuthToken jwtAuthToken = (JwtAuthToken) loginService.createAuthToken(optionalMemberDTO.get());

            return CommonResponse.builder()
                    .code("LOGIN_SUCCESS")
                    .status(200)
                    .message(jwtAuthToken.getToken())
                    .build();

        } else {
            throw new LoginFailedException();
        }
    }
}