package hello.hellospring; // 이 패키지 하위여야만 스캔되어 스프링 컨테이너에 스프링 빈으로 등록 가능

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
