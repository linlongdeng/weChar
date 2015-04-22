package weChat.web;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import weChat.core.rabbit.RabbitClient;
import weChat.core.rabbit.RabbitClientConfig;
import weChat.domain.Customer;

@Controller
@RequestMapping("/rabbit")
public class RabbitMessageController {

	@Autowired
	private RabbitClient rabbitClient;


	@SuppressWarnings("unchecked")
	@RequestMapping("/testRabbit")
	public String testRabbit(@RequestParam Map<String, String>  param, Model model) {
	/*	String queueName = "queue" + customer.getId() ;
		Queue queue = new Queue(queueName,false);
		amqpAdmin.declareQueue(queue);
		Binding binding = BindingBuilder.bind(queue).to(directExchange)
				.with(queue.getName());
		amqpAdmin.declareBinding(binding);
		messageListenerContainer.addQueueNames(queueName);
		model.addAttribute("title", customer.getFirstName());
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("firstName", customer.getFirstName());
*/
		String queueName = (String) param.get("queueName");
		Map<String, Object> resultMap = (Map<String, Object>) rabbitClient.convertSendAndReceive(queueName, param);
		model.addAttribute("result", resultMap);
		return "rabbit/testRabbitMessage";
	}

	@InitBinder(value = "customer")
	public void initBinder2(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("customer.");
	}

	@RequestMapping("/test2")
	@ResponseBody
	public String test2(String title) {
		return "helllo,world";
	}

}
