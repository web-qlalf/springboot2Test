package mall.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import mall.domain.CommonResponse;

@Slf4j
@Configuration
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<CommonResponse> handleRuntimeException(RuntimeException e) {

		log.info("handleRuntimeException", e);

		CommonResponse response = CommonResponse.builder().code("test").message(e.getMessage())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomAuthenticationException.class)
	protected ResponseEntity<CommonResponse> handleCustomAuthenticationException(CustomAuthenticationException e) {

		log.info("handleCustomAuthenticationException", e);

		CommonResponse response = CommonResponse.builder().code(ErrorCode.AUTHENTICATION_FAILED.getCode())
				.message(e.getMessage()).status(ErrorCode.AUTHENTICATION_FAILED.getStatus()).build();

		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(LoginFailedException.class)
	protected ResponseEntity<CommonResponse> handleLoginFailedException(LoginFailedException e) {

		log.info("handleLoginFailedException", e);

		CommonResponse response = CommonResponse.builder().code(ErrorCode.Login_FAILED.getCode())
				.message(e.getMessage()).status(ErrorCode.Login_FAILED.getStatus()).build();

		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(BadCredentialsException.class)
	protected ResponseEntity<CommonResponse> handleBadCredentialsException(BadCredentialsException e) {

		log.info("handleBadCredentialsException", e);

		CommonResponse response = CommonResponse.builder().code(ErrorCode.Login_FAILED.getCode())
				.message(e.getMessage()).status(ErrorCode.Login_FAILED.getStatus()).build();
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(AccessDeniedException.class)
	protected ResponseEntity<CommonResponse> handleAccessDeniedException(AccessDeniedException e) {

		log.info("handleAccessDeniedException", e);

		CommonResponse response = CommonResponse.builder().code(ErrorCode.ACCESS_DENIED.getCode())
				.message(ErrorCode.ACCESS_DENIED.getMessage()).status(ErrorCode.ACCESS_DENIED.getStatus()).build();

		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(InsufficientAuthenticationException.class)
	protected ResponseEntity<CommonResponse> handleInsufficientAuthenticationException(InsufficientAuthenticationException e) {

		log.info("handleInsufficientAuthenticationException", e);

		CommonResponse response = CommonResponse.builder().code(ErrorCode.AUTHENTICATION_FAILED.getCode())
				.message(ErrorCode.AUTHENTICATION_FAILED.getMessage())
				.status(ErrorCode.AUTHENTICATION_FAILED.getStatus()).build();

		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

}