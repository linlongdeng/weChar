package weChat.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Type;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class MyConfiguration {

	@Bean
	public MappingJackson2HttpMessageConverter customConverters() {
		return new MappingJackson2HttpMessageConverter(){

			@Override
			public Object read(Type type, Class<?> contextClass,
					HttpInputMessage inputMessage) throws IOException,
					HttpMessageNotReadableException {
				return super.read(type, contextClass, inputMessage);
			}
			
		};

	}

}