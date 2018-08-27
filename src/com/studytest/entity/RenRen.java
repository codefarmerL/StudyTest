package com.studytest.entity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
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

public class RenRen {
    /*以下是模拟登陆程序*/
    /*输入你的用户名及密码 ，这里输入*/
   private static String userName = "13647434862";  
   private static String password = "19950708wl?";  
   // Don't change the following URL  
   private static String renRenLoginURL = "http://www.renren.com/PLogin.do";  
   // The HttpClient is used in one session  
   private HttpResponse response;  
   private DefaultHttpClient httpclient = new DefaultHttpClient();  
   /*输入抓包的参数，即传递的参数*/
   private boolean login() {  
       HttpPost httpost = new HttpPost(renRenLoginURL);  
       // All the parameters post to the web site
      //建立一个NameValuePair数组，用于存储欲传送的参数，添加相关参数，见上图中的参数
       List<NameValuePair> nvps = new ArrayList<NameValuePair>(); 
       nvps.add(new BasicNameValuePair("email", userName));  
       nvps.add(new BasicNameValuePair("password", password));  
       try {  
           /*登陆成功，获取返回的数据，即html文件*/
           httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
           response = httpclient.execute(httpost);  
       } catch (Exception e) {  
           e.printStackTrace();  
           return false;  
       } finally {  
           httpost.abort();  
       }  
       return true;  
   }  

   private String getRedirectLocation() { 
       /*获取响应的头 url*/ 
       Header locationHeader = response.getFirstHeader("Location");  
       if (locationHeader == null) {  
           return null;  
       }  
       return locationHeader.getValue();  
   }  
    /*获取html文本*/ 
   private String getText(String redirectLocation) {  
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

   public void printText() {
       /*如果注册成功了，输入相应后的html*/   
       if (login()) {  
           String redirectLocation = getRedirectLocation();  
           if (redirectLocation != null) {  
               System.out.println(getText(redirectLocation));  
           }  
       }  
   }  
   /*main方法*/
   public static void main(String[] args) {  
       RenRen renRen = new RenRen();  
       int i=0;
       //renRen.printText(); 
       RenRen r = new RenRen();
       if(r.login()){
    	   System.out.println(r.response.getFirstHeader("Location").getValue());
    	   
    	   try {
    		String redirectLocation = r.getRedirectLocation();  
    		String entity = r.getText(redirectLocation);
    		System.out.println(entity);
    		Document doc = Jsoup.parse(entity);
    		Elements imgs=doc.select("a").select("img[src]");
    		for(Element e : imgs){
	    		String imgSrc = e.attr("abs:src");
	    		if("".equals(imgSrc)){
	    			continue;
	    		}
	    		System.out.println(imgSrc);
	    		URL url=new URL(imgSrc);  
		        URLConnection con=url.openConnection();  
		        con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
		        InputStream is=con.getInputStream();  
		        byte[] bs=new byte[1024];  
		        int len;
		      //存储在自定义的目录路径下  
		        OutputStream os=new FileOutputStream(new File("E:/Users/lzy/Workspaces/MyEclipse10/StudyTest/lib", "test"+i+".png"));
		        while ((len=is.read(bs))!=-1) {  
		            os.write(bs,0,len);  
		            os.flush();  
		        }  
		        os.close();  
		        is.close();
		        i++;
    		}
			//String entity = EntityUtils.toSstring (r.response.getEntity(),"utf-8");
			//System.out.println(entity);
			//Document doc = Jsoup.parse(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       }
   }  
}
