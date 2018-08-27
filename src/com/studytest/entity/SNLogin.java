package com.studytest.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SNLogin {

	private static String userName = "jd_6acc91f557b9f";  
	private static String password = "19970405tl";  
	private static String SNLoginURL = "https://passport.suning.com/ids/login";
	private static HttpResponse response;  
	private static DefaultHttpClient httpclient = new DefaultHttpClient(); 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Document doc = null;  
		try {
			doc = Jsoup.connect(SNLoginURL).get();
			System.out.println(doc);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>(); 
			nvps.add(new BasicNameValuePair("jsonViewType", "true"));  
			nvps.add(new BasicNameValuePair("username", userName));  
			nvps.add(new BasicNameValuePair("password2", password));
			nvps.add(new BasicNameValuePair("loginTheme", "b2c"));
			HttpPost httpost = new HttpPost(SNLoginURL); 
			//httpost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
			try {  
			    /*登陆成功，获取返回的数据，即html文件*/
			    httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
			    response = httpclient.execute(httpost);  
			    System.out.println(response.getStatusLine().getStatusCode());
			    System.out.println(EntityUtils.toString(response.getEntity()));
			} catch (Exception e) {  
				e.printStackTrace();  
			} finally {  
			    httpost.abort();  
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
