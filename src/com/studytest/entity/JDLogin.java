package com.studytest.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JDLogin {

	private static String userName = "jd_6acc91f557b9f";  
	private static String password = "19970405tl";  
	private static String JDLoginURL = "https://passport.jd.com/new/login.aspx";
	private static HttpResponse response;  
	private static DefaultHttpClient httpclient = new DefaultHttpClient(); 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Document doc = null;  
        try {
			doc = Jsoup.connect(JDLoginURL).get();
			String sa_token = doc.getElementById("sa_token").attr("value");
			String uuid = doc.getElementById("uuid").attr("value");
			String eid = doc.getElementById("eid").attr("value");
			String fp = doc.getElementById("sessionId").attr("value");
			String _t = doc.getElementById("token").attr("value");
			//String loginType = doc.getElementById("loginType").attr("value");
			String main_flag = doc.getElementById("main_flag").attr("value");
			String pubKey = doc.getElementById("pubKey").attr("value");
			
			   // All the parameters post to the web site
			  //建立一个NameValuePair数组，用于存储欲传送的参数，添加相关参数，见上图中的参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>(); 
			nvps.add(new BasicNameValuePair("sa_token", sa_token));  
			nvps.add(new BasicNameValuePair("uuid", uuid));  
			nvps.add(new BasicNameValuePair("eid", eid));
			nvps.add(new BasicNameValuePair("fp", fp));
			nvps.add(new BasicNameValuePair("_t", _t));
			nvps.add(new BasicNameValuePair("loginType", "f"));
			nvps.add(new BasicNameValuePair("main_flag", main_flag));
			nvps.add(new BasicNameValuePair("pubKey", pubKey));
			nvps.add(new BasicNameValuePair("authcode", ""));
			nvps.add(new BasicNameValuePair("loginname", userName));
			nvps.add(new BasicNameValuePair("nloginpwd", password));
			nvps.add(new BasicNameValuePair("loginpwd", password));
			
			String postUrl = "https://passport.jd.com/uc/loginService?uuid=" + uuid + "&&r="+Math.random()+"&version=2015";
			//String postUrl = "https://passport.jd.com/uc/loginService";
			System.out.println(postUrl);
			HttpPost httpost = new HttpPost(postUrl); 
			httpost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
			try {  
			    /*登陆成功，获取返回的数据，即html文件*/
			    httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
			    response = httpclient.execute(httpost);  
			    /*CookieStore cookie = httpclient.getCookieStore();
			    System.out.println(cookie);*/
			    System.out.println(response.getStatusLine().getStatusCode());
			    System.out.println(EntityUtils.toString(response.getEntity()));
			} catch (Exception e) {  
				e.printStackTrace();  
			} finally {  
			    httpost.abort();  
			}  
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*HttpPost httpost = new HttpPost(JDLoginURL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>(); 
	    nvps.add(new BasicNameValuePair("loginname", userName));  
	    nvps.add(new BasicNameValuePair("nloginpwd", password));
	    JDLogin jd = new JDLogin();
	    try {  
	    	登陆成功，获取返回的数据，即html文件
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
            jd.response = jd.httpclient.execute(httpost);  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    } finally {  
	        httpost.abort();  
	    }
	    System.out.println(jd.response.getFirstHeader("Location").getValue());*/
	}
	
	private static String getRedirectLocation() { 
	       /*获取响应的头 url*/ 
	       Header locationHeader = response.getFirstHeader("Location");  
	       if (locationHeader == null) {  
	           return null;  
	       }  
	       return locationHeader.getValue();  
	   }  
	    /*获取html文本*/ 
	   private static String getText(String redirectLocation) {  
	       HttpGet httpget = new HttpGet(redirectLocation);  
	       // Create a response handler  
	       ResponseHandler<String> responseHandler = new BasicResponseHandler();  
	       String responseBody = "";  
	       try {  
	           responseBody = httpclient.execute(httpget, responseHandler);  
	       } catch (Exception e) {  
	           e.printStackTrace();  
	           responseBody = null;  
	       } finally {  
	           httpget.abort();  
	           httpclient.getConnectionManager().shutdown();  
	       }  
	       return responseBody;  
	   }  

}
