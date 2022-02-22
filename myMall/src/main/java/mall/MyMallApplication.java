package mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan(basePackages = {"mall.*"})
@SpringBootApplication(scanBasePackages = "mall.*")
public class MyMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMallApplication.class, args);
	}

}
