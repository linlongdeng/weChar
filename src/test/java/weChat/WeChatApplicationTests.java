package weChat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import weChat.core.rabbit.RabbitClientConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WeChatApplication.class)
@WebAppConfiguration
public class WeChatApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired
	RabbitClientConfig rebbitConfiguration;



}
