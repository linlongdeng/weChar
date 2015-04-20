package weChat.web;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import weChat.core.RabbitConfiguration;
import weChat.domain.Customer;

@Controller
@RequestMapping("/rabbit")
public class RabbitMessageController {

	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired
	RabbitConfiguration rebbitConfiguration;
	@Autowired
	AmqpAdmin amqpAdmin;
	@Autowired
	DirectExchange directExchange;
	@Autowired
	AbstractMessageListenerContainer messageListenerContainer;

	@SuppressWarnings("unchecked")
	@RequestMapping("/testRabbit")
	public String testRabbit(Customer customer, Model model) {
		String queueName = "queue" + customer.getId() ;
		Queue queue = new Queue(queueName,false);
		amqpAdmin.declareQueue(queue);
		Binding binding = BindingBuilder.bind(queue).to(directExchange)
				.with(queue.getName());
		amqpAdmin.declareBinding(binding);
		messageListenerContainer.addQueueNames(queueName);
		model.addAttribute("title", customer.getFirstName());
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("firstName", customer.getFirstName());

		Map<String, Object> map = (Map<String, Object>) rabbitTemplate
				.convertSendAndReceive(
						queueName,
						param,
						(message) -> {
							MessageProperties properities = message
									.getMessageProperties();
							String corrId = UUID.randomUUID().toString();
							properities.setCorrelationId(corrId.getBytes());

							return message;
						});
		System.out.println(map);
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
