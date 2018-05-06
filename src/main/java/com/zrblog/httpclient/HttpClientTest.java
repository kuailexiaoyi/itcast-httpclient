package com.zrblog.httpclient;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * @ClassName: HttpClientTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zrblog
 * @date 2017年12月28日
 * 
 */

public class HttpClientTest {

	@Test
	public void doGet() {
		// 1.创建httpClient对象
		CloseableHttpClient client = HttpClients.createDefault();

		// 2.创建http Get请求
		HttpGet httpGet = new HttpGet("http://www.taotao.com/");

		CloseableHttpResponse response = null;

		try {
			response = client.execute(httpGet);

			// 判断返回的状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println(content.length());
				FileUtils.writeStringToFile(new File("D:\\log.html"), content, "UTF-8");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Title: doGet
	 * @Description: TODO 带参数的Get请求
	 * @author 曾 睿
	 * @date 2017年12月28日
	 */
	@Test
	public void doParameterGet() {
		// 1.创建httpClient对象
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			URI uri = new URIBuilder("http://manager.taotao.com/rest/content").setParameter("categoryId", "10")
					.setParameter("page", "1").setParameter("rows", "20").build();

			System.out.println(uri);
			// 2.创建http Get请求
			HttpGet httpGet = new HttpGet(uri);

			response = client.execute(httpGet);

			// 判断返回的状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println(content.length());
				FileUtils.writeStringToFile(new File("D:\\log.html"), content, "UTF-8");
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Title: doPost
	 * @Description: TODO POST请求
	 * @author 曾 睿
	 * @date 2017年12月28日
	 */
	@Test
	public void doPost() {
		// 1.创建httpClient对象
		CloseableHttpClient client = HttpClients.createDefault();

		// 2.创建http Post请求
		HttpPost httpPost = new HttpPost("http://www.oschina.net/");

		httpPost.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.75 Safari/537.36");

		CloseableHttpResponse response = null;

		try {
			response = client.execute(httpPost);

			// 判断返回的状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				System.out.println(response.getStatusLine());
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println(content.length());
				FileUtils.writeStringToFile(new File("D:\\log.html"), content, "UTF-8");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void doParameterPost() {
		// 1.创建httpClient对象
		CloseableHttpClient client = HttpClients.createDefault();
		// 2.创建http Post请求
		HttpPost httpPost = new HttpPost("http://manager.taotao.com/rest/item/interface");

		List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
		parameters.add(new BasicNameValuePair("price", "9999"));
		parameters.add(new BasicNameValuePair("title", "doPost请求"));
		parameters.add(new BasicNameValuePair("cid", "11"));
		parameters.add(new BasicNameValuePair("status", "1"));
		parameters.add(new BasicNameValuePair("num", "123"));
		parameters.add(new BasicNameValuePair("id", "20"));

		CloseableHttpResponse response = null;
		// 构造一个form表单式的实体
		try {
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
			// 将请求实体设置到Http请求中
			httpPost.setEntity(formEntity);
			response = client.execute(httpPost);

			// 判断返回的状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				System.out.println(response.getStatusLine());
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println(content.length());
				System.out.println(content);
				FileUtils.writeStringToFile(new File("D:\\log.html"), content, "UTF-8");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
