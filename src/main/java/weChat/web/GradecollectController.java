package weChat.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import weChat.core.web.CommonController;
import weChat.parameter.manage.MRequestParam;
import weChat.parameter.manage.MResponseParam;
import weChat.service.GradecollectService;

/**
 * @author deng
 * @date 2015年4月23日
 * @version 1.0.0
 */
@RestController
@RequestMapping("/Membersync")
public class GradecollectController extends CommonController {

	@Autowired
	private GradecollectService gradecollectService;

	/**
	 * 会员等级同步
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/GradeCollect")
	public MResponseParam syncGrade(@RequestBody MRequestParam param) {
		return gradecollectService.syncGrade(param);

	}
	
/*	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleException(Exception ex,
			HttpServletRequest request) {
		Map<String, Object> body = new HashMap<String,Object>();
		body.put("error", ex.getMessage());
		HttpStatus status = HttpStatus.OK;
		return new ResponseEntity<Map<String, Object>>(body, status);
	}*/

}
