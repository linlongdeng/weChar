package weChat.core;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import weChat.domain.Customer;

public class Receiver {


	/**
	 * 接收消息的方法
	 * @param message
	 */
	public Map<String, Object> receiveMessage(Map<String, Object> message) {
		Set<Entry<String, Object>> set = message.entrySet();
		System.out.println("收到消息");
		for(Entry<String, Object> entry : set){
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		return message;
		
	}

}
