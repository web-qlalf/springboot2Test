package mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@EntityScan(basePackages = {"mall.*"})
@SpringBootApplication(scanBasePackages = "mall.*")
public class MyMallApplication extends SpringBootServletInitializer{
	// SpringBootServletInitializer 없으면 jsp 출력 안 됨

	public static void main(String[] args) {
		SpringApplication.run(MyMallApplication.class, args);
	}
	
	

}
