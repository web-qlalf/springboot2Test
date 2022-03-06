package mall.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, AuthenticationFailureHandler {
	private final HandlerExceptionResolver handlerExceptionResolver;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		System.out.println("commence handlerExceptionResolver");
		
		if(request.getRequestURI().contains("/api/vi")) {
			//api랑 구분
			handlerExceptionResolver.resolveException(request, response, null, authException);
		}else {
			
			onAuthenticationFailure(request, response, authException);
		}

	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		System.out.println("authenticationFailureHandler");
		String loginid = request.getParameter("my_id");
		String errormsg = "";

		if (exception instanceof BadCredentialsException) {
			loginFailureCount(loginid);
			errormsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			loginFailureCount(loginid);
			errormsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
		} else if (exception instanceof DisabledException) {
			errormsg = "계정이 비활성화되었습니다. 관리자에게 문의하세요.";
		} else if (exception instanceof CredentialsExpiredException) {
			errormsg = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의하세요.";
		}

		request.setAttribute("username", loginid);
		request.setAttribute("error_message", errormsg);

		request.getRequestDispatcher("/loginForm?error").forward(request, response);
	}

	// 비밀번호를 3번 이상 틀릴 시 계정 잠금 처리
	protected static void loginFailureCount(String username) {
		/*
		 * // 틀린 횟수 업데이트 userDao.countFailure(username); // 틀린 횟수 조회 int cnt =
		 * userDao.checkFailureCount(username); if(cnt==3) { // 계정 잠금 처리
		 * userDao.disabledUsername(username); }
		 */
	}
	

	 
}
