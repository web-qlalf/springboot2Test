package mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@EntityScan(basePackages = {"mall.*"})
@SpringBootApplication(scanBasePackages = "mall.*")
public class MyMallApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(MyMallApplication.class, args);
	}
	
	

}
