package weChat.core.utils;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;

public class CommonUtils {
	/**UTF-8**/
	public static final String UTF_8="utf-8";

	/**
	 * 判断对象是否Empty(null或元素为0)<br>
	 * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
	 * 
	 * @param pObj
	 *            待检查对象
	 * @return boolean 返回的布尔值
	 */
	public static boolean isEmpty(Object pObj) {
		if (pObj == null)
			return true;
		if (pObj == "")
			return true;
		if (pObj instanceof String) {
			if (((String) pObj).length() == 0) {
				return true;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection) pObj).size() == 0) {
				return true;
			}
		} else if (pObj instanceof Map) {
			if (((Map) pObj).size() == 0) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断任意对象是否为空
	 * @param pObj
	 * @return
	 */
	public static boolean isEmpty(Object... pObj){
		if(pObj != null){
			for(int i = 0; i < pObj.length; i++){
				//一个有空，立即返回
				if(isEmpty(pObj[i])){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断对象是否为NotEmpty(!null或元素>0)<br>
	 * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
	 * 
	 * @param pObj
	 *            待检查对象
	 * @return boolean 返回的布尔值
	 */
	public static boolean isNotEmpty(Object pObj) {
		if (pObj == null)
			return false;
		if (pObj == "")
			return false;
		if (pObj instanceof String) {
			if (((String) pObj).length() == 0) {
				return false;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection) pObj).size() == 0) {
				return false;
			}
		} else if (pObj instanceof Map) {
			if (((Map) pObj).size() == 0) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 获取当前时间戳
	 * @return
	 */
	public static Timestamp  currentTimestamp(){
		return new Timestamp(System.currentTimeMillis()); 
	}

}
