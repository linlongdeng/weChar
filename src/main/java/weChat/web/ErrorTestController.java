package weChat.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorTestController {

	@RequestMapping("/testError")
	public String testError(@RequestParam("flag") Boolean flag, ModelMap model) {
		if (flag) {
			throw new RuntimeException("测试错误3");

		} else {
			model.put("name", "林龙灯");
			return "greeting";
		}
	}
}
