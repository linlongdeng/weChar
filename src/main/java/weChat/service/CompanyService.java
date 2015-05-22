package weChat.service;

import org.springframework.ui.Model;

public interface CompanyService {

	/**
	 * 验证商家编码是否存在，如果不存在，直接抛出异常。
	 * 接下来验证商家编码，如果不存在，直接抛出异常
	 * 如果正常，把商家对象放入到model中
	 * 
	 * @param companycode
	 * @param wechatpubinfoid
	 * @param model
	 */
	public void validateCompany(String companycode, String companypsw,
			int wechatpubinfoid, Model model);
}
