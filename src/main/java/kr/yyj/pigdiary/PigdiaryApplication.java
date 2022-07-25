package kr.yyj.pigdiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // AuditingEntityListner 활성화를 위함
public class PigdiaryApplication {
    public static void main(String[] args) {
        SpringApplication.run(PigdiaryApplication.class, args);
    }

}
