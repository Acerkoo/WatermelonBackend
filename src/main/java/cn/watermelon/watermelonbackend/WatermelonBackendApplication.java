package cn.watermelon.watermelonbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@MapperScan("cn.watermelon.watermelonbackend.mapper")
@EnableScheduling
public class WatermelonBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatermelonBackendApplication.class, args);
    }

}
