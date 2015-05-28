package weChat.core.utils;

import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * HTTP工具类
 * 
 * @author deng
 * @date 2015年5月28日
 * @version 1.0.0
 */
public class HttpClientUtils {

	private static ObjectMapper mapper = new ObjectMapper();;

	/**
	 * 把请求发送fiddler代理服务器
	 * 
	 * @param url
	 * @param requestParam
	 * @param valueType
	 * @return
	 * @throws Exception
	 */
	public static <T> T postProxy(String url, Object requestParam,
			Class<T> valueType) throws Exception {
		HttpHost proxyHost = new HttpHost("127.0.0.1", 8888);
		RequestConfig config = RequestConfig.custom().setProxy(proxyHost)
				.build();
		return post(url, requestParam, valueType, config);
	}

	/**
	 * 发送http post application/json请求
	 * 
	 * @param url
	 * @param requestParam
	 * @param valueType
	 * @return
	 * @throws Exception
	 */
	public static <T> T post(String url, Object requestParam, Class<T> valueType)
			throws Exception {
		// 设置超时时间
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(1000 * 10).setSocketTimeout(1000 * 10)
				.build();
		return post(url, requestParam, valueType, config);
	}

	/**
	 * 发送http post application/json请求
	 * 
	 * @param url
	 * @param objectParam
	 *            请求参数
	 * @param valueType
	 *            返回参数类型
	 * @return
	 * @throws Exception
	 */
	public static <T> T post(String url, Object requestParam,
			Class<T> valueType, RequestConfig config) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			// 设置自定义请求配置
			httpPost.setConfig(config);
			String sParam = mapper.writeValueAsString(requestParam);
			HttpEntity entity = new StringEntity(sParam,
					ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity respEntity = response.getEntity();
					ContentType contentType = ContentType
							.getOrDefault(respEntity);
					Charset charset = contentType.getCharset();
					InputStream inputStream = respEntity.getContent();
					T value = mapper.readValue(inputStream, valueType);
					EntityUtils.consume(respEntity);
					return value;
				}
			} finally {
				response.close();
			}

		} finally {
			httpclient.close();
		}
		return null;
	}
}
