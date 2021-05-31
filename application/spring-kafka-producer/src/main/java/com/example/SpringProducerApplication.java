package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;


@SpringBootApplication
public class SpringProducerApplication implements CommandLineRunner{

    private static String TOPIC_NAME = "test";

    // KafkaTemplate을 @Autowired 어노테이션으로 주입받는다.
    // 사용자가 선언하지 않은 bean 객체이지만 스프링 카프카에서 제공하는 기본 KakfaTemplate 객체로 주입된다.
    // application.yaml 에서 선언한 옵션값은 자동으로 주입된다.
    @Autowired
    private KafkaTemplate<Integer, String> template;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringProducerApplication.class);
        application.run(args);
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        for(int i = 0 ; i < 10 ; i++) {
            template.send(TOPIC_NAME, "test" + i);
        }
        System.exit(0);
    }
}
