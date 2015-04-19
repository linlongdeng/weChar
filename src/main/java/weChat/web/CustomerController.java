package weChat.web;

import java.util.List;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import weChat.bean.BusiBean;
import weChat.domain.Customer;
import weChat.domain.CustomerRepository;
import weChat.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping("/list")
	public List<Customer> findList(String firstname) {
		return customerRepository.findList(firstname);
	}

	@RequestMapping("/getCustomer")
	public Object getCustomer(@RequestBody Customer body) {
		return body;

	}
	

	@RequestMapping("/testGenerics")
	public Object testGenerics(@RequestBody BusiBean<Customer> body) {
		System.out.println(body.getApi_params().getFirstName());
		return body;
	}

}
