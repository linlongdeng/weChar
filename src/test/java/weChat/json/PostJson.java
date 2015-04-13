package weChat.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostJson {
	// 设置代理
	static{
	/*	System.setProperty("http.proxyHost", "localhost");
		System.setProperty("http.proxyPort", "8888");*/
	}
	
	private static String  ip ="http://127.0.0.1:8080";
	/**
	 * 发送HttpPost请求
	 * 
	 * @param strURL
	 *            服务地址
	 * @param params
	 *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
	 * @return 成功:返回json字符串<br/>
	 */
	public static String post(String actionPath, String params) {
		String strURL = ip + actionPath;
		System.out.println(strURL);
		System.out.println(params);
		try {
			URL url = new URL(strURL);// 创建连接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type",
					"application/json;charset=UTF-8"); // 设置发送数据的格式
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 设置通用的请求属性
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream(), "UTF-8"); // utf-8编码
			out.append(params);
			out.flush();
			out.close();

			BufferedReader in = null;
			String result = "";
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error"; // 自定义错误信息
	}


}
