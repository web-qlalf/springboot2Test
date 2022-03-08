package mall.domains.users.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/loginForm")
	public String loginForm() throws Exception {
		log.info("로그인 창 진입");
		return "security/loginForm";
	}
}
