package weChat.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}
}
