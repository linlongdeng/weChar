package weChat.core;

import java.util.concurrent.CountDownLatch;

public class Receiver {
	private CountDownLatch latch = new CountDownLatch(1);

	/**
	 * 接收消息的方法
	 * @param message
	 */
	public void receiveMessage(String message) {
		System.out.println("Received <" + new String(message) + ">");
		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}
}
