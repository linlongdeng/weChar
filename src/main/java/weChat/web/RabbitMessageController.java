package weChat.web;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import weChat.core.RabbitConfiguration;

@Controller
@RequestMapping("/rabbit")
public class RabbitMessageController {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired
	RabbitConfiguration rebbitConfiguration;
	
    @RequestMapping("/testRabbit")
	public String testRabbit(@RequestParam(value="title",defaultValue="测试") String title,Model model){
    	model.addAttribute("title", title);
    	rabbitTemplate.convertAndSend(rebbitConfiguration.getExchangeName(),
				rebbitConfiguration.getQueueName(),title);
		return "rabbit/testRabbitMessage";
	}

}
