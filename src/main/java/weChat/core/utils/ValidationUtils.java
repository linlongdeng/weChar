package weChat.core.utils;

import static weChat.core.utils.CommonUtils.isEmpty;
import static weChat.utils.AppConstants.ARGUMENT_NOT_EMPTY_INFO;

import java.util.Map;

import org.springframework.validation.Errors;

public abstract class ValidationUtils {

	private final static String DEFAULT_EMPTY_CODE = "ARGUMENT_NOT_EMPTY_INFO";

	/**
	 * 判断target为是否为空，并且target对应的key的值的属性为空，任意条件为空则拒绝该参数值
	 * 
	 * @param tagget
	 * @param e
	 * @param paramKey
	 */
	public static void rejectIfEmpty(
			@SuppressWarnings("rawtypes") Map target, String objectName, Errors e, String... keys) {
		if(rejectIfEmpty(target, objectName,e)){
			if (keys != null) {
				for (String key : keys) {
					if (isEmpty(target.get(key))) {
						e.reject(ARGUMENT_NOT_EMPTY_INFO, new Object[] { key },
								null);
					}
				}
			}
		}
		

	}

	/**
	 * 如果对象为空，则拒绝,并返回false
	 * 
	 * @param target
	 * @param objectName
	 * @param e
	 * @param keys
	 */
	public static boolean rejectIfEmpty(Object target, String objectName,
			Errors e) {
		if (isEmpty(target)) {
			e.reject(ARGUMENT_NOT_EMPTY_INFO, new Object[] { objectName }, null);
			return false;
		}
		return true;

	}
}
