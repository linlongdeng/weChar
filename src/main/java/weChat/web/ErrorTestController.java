package weChat.web;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@Autowired
	private DataSource dataSource;
	@RequestMapping("/testDataSource")
	@ResponseBody
	public Object testDataSource(){
		return dataSource.toString();
		
	}
}
