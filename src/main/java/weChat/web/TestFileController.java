package weChat.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import weChat.parameter.manage.MResponseParam;
import weChat.utils.ResponseUtils;

@Controller
@RequestMapping("/testFile")
public class TestFileController {

	@RequestMapping("/testFile")
	@ResponseBody
	public MResponseParam testFilePost(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "test", required = false) String test,
			@RequestParam("file") MultipartFile file) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
		String line = null;
		System.out.println("文件内容是");
		while((line = br.readLine()) != null){
			System.out.println(line);
		}
		br.close();
		System.out.println("成功了");
		return ResponseUtils.successMR();
	}
}
