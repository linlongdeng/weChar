package weChat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:jdbc.properites","classpath:rabbitmq.properties","classpath:km.properties" }) 
public class WeChatApplication {

	private String center_url;
	
    public static void main(String[] args) {
        SpringApplication.run(WeChatApplication.class, args);
    }
}
