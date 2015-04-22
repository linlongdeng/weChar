package weChat.core.rabbit;

import java.util.Hashtable;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.AMQP.Exchange;

@Configuration
@ConfigurationProperties(prefix = "rebbitmq")
public class RabbitClientConfig {

	private String host;

	private Integer port;

	private String username;

	private String password;

	private Integer connectionTimeOut;

	private String exchangeName;

	private long replyTimeout;



	

	private String vhost;


	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
				getHost(), getPort());
		connectionFactory.setConnectionTimeout(getConnectionTimeOut());
		connectionFactory.setUsername(getUsername());
		connectionFactory.setPassword(getPassword());
		connectionFactory.setVirtualHost(getVhost());
		return connectionFactory;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
			MessageConverter messageConverter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setExchange(getExchangeName());
		rabbitTemplate.setMessageConverter(messageConverter);
		rabbitTemplate.setReplyTimeout(getReplyTimeout());
		return rabbitTemplate;

	}
/*
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(getExchangeName());
	}

	
	 * @Bean(name = "myQueue") public Queue myQueue() { return new
	 * Queue(getQueueName()); }
	 * 
	 * @Bean Binding binding(@Qualifier("myQueue") Queue queue, DirectExchange
	 * exchange) { return
	 * BindingBuilder.bind(queue).to(exchange).with(getQueueName()); }
	 * 
	 * @Bean SimpleMessageListenerContainer container( ConnectionFactory
	 * connectionFactory,
	 * 
	 * @Qualifier("listenerAdapter") MessageListenerAdapter listenerAdapter) {
	 * SimpleMessageListenerContainer container = new
	 * SimpleMessageListenerContainer();
	 * container.setConnectionFactory(connectionFactory);
	 * container.setQueueNames(getQueueName());
	 * container.setMessageListener(listenerAdapter); return container; }
	 * 
	 * @Bean Receiver receiver() { return new Receiver(); }
	 * 
	 * @Bean(name = "listenerAdapter") MessageListenerAdapter
	 * listenerAdapter(Receiver receiver, MessageConverter messageConverter) {
	 * MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(
	 * receiver, "receiveMessage");
	 * listenerAdapter.setMessageConverter(messageConverter); return
	 * listenerAdapter; }
	 */

	@Bean
	MessageConverter messageConverter() {
		// return new JsonMessageConverter();
		return new Jackson2JsonMessageConverter();

	}

	/*
	 * @Bean public SimpleMessageListenerContainer
	 * replyListenerContainer(RabbitTemplate rabbitTemplate) {
	 * SimpleMessageListenerContainer container = new
	 * SimpleMessageListenerContainer();
	 * container.setConnectionFactory(connectionFactory());
	 * container.setQueues(replyQueue());
	 * container.setMessageListener(rabbitTemplate); return container; }
	 * 
	 * @Bean public Queue replyQueue() { return new Queue("my.reply.queue"); }
	 */
	
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getConnectionTimeOut() {
		return connectionTimeOut;
	}

	public void setConnectionTimeOut(Integer connectionTimeOut) {
		this.connectionTimeOut = connectionTimeOut;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getVhost() {
		return vhost;
	}

	public void setVhost(String vhost) {
		this.vhost = vhost;
	}

	public long getReplyTimeout() {
		return replyTimeout;
	}

	public void setReplyTimeout(long replyTimeout) {
		this.replyTimeout = replyTimeout;
	}
}