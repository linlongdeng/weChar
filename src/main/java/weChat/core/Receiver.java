package weChat.core;

import java.util.concurrent.CountDownLatch;

import weChat.domain.Customer;

public class Receiver {
	private CountDownLatch latch = new CountDownLatch(1);

	/**
	 * 接收消息的方法
	 * @param message
	 */
	public Object receiveMessage(Customer message) {
		System.out.println("Received <" + new String(message.getFirstName()) + ">");
		latch.countDown();
		return message;
		
	}

	public CountDownLatch getLatch() {
		return latch;
	}
}
