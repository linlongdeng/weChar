package weChat.service;

import weChat.core.metatype.Dto;

/**
 * 调用KM的服务
 * 
 * @author deng
 * @date 2015年5月28日
 * @version 1.0.0
 */
public interface InvokeKmService {

	/**
	 * 获取授权码
	 * 
	 * @return
	 */
	public Dto getKmAuth();

	/**
	 * 获取所有商家信息
	 * 
	 * @return
	 */
	public Dto getAllCompanyFromKm();

	/**
	 * 根据商家ID，获取KM商家信息
	 * 
	 * @param companyid
	 * @return
	 */
	public Dto getKmCompanyById(int companyid);

	/**
	 * 根据手机号码生成K米会员
	 * 
	 * @param pDto
	 * @return
	 */
	public Dto registerByPhone(Dto pDto);

	/**
	 * 完善K米会员资料
	 * 
	 * @param pDto
	 */
	public Dto fillCustomerInfo(Dto pDto);
	/**
	 * 发送短信
	 * @param pDto
	 * @return
	 */
	public Dto  sendsms(Dto pDto);

}
