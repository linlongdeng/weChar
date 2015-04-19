package weChat.web;

import javax.annotation.Resource;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	
    @RequestMapping("/testRabbit")
	public String testRabbit(Customer customer,Model model){
    	
    	model.addAttribute("title", customer.getFirstName());
    	Object receive = rabbitTemplate.convertSendAndReceive(customer);
		return "rabbit/testRabbitMessage";
	}
    
    
    @InitBinder(value="customer")  
    public void initBinder2(WebDataBinder binder) {  
            binder.setFieldDefaultPrefix("customer.");  
    } 
    @RequestMapping("/test2")
    @ResponseBody
    public String test2(String title){
    	return "helllo,world";
    }

}
