package weChat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weChat.domain.Customer;
import weChat.domain.CustomerRepository;
import weChat.service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository ){
		this.customerRepository = customerRepository;
	}

	@Override
	public void saveCustomer(Customer customer, Boolean flag) {
		customerRepository.save(customer);
		if(!flag){
			throw new RuntimeException("测试事务异常");
		}
	}

}
