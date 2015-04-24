package weChat;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.internal.ast.QueryTranslatorImpl;
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


	public void testHql(){
		String hql =" select m from MemberCache m ";
	}
	
	@Test
	public void contextLoads() {
	}

	



}
