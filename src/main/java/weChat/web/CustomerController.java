package weChat.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
