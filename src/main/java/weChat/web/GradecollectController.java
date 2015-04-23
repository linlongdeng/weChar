package weChat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class GradecollectController {

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

}
