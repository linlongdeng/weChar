package weChat.service;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;

import weChat.domain.Customer;





public interface CustomerService {
	


	@Transactional
	 @Modifying
	public void saveCustomer(Customer customer, Boolean flag);
}
