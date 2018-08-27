package com.studytest.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.http.NameValuePair;

/**
 * @author:合肥工业大学 管理学院 钱洋
 * @email:1563178220@qq.com
 * @ 
 */
public class Test {

    public static void main(String[] args) throws Exception {    
    	String listUrl = "http://localhost:8080/seckill/seckill/list";
    	Document doc = null;  
    	doc = Jsoup.connect(listUrl).get();
    	//String ee = doc.select("tbody").select("td").select("a").attr("href");
    	Elements ee = doc.select("tbody").select("td").select("a");
    	Elements eee = doc.select("tbody").select("td").select("span");
    	System.out.println(eee);
    	System.out.println(ee);
    	String[] seckillName = new String[eee.size()];;
    	String[] seckillId = new String[ee.size()];
    	String[] md5 = new String[ee.size()];
    	int i=0;
    	int j = 0;
    	for(Element e : ee){
    		seckillId[i] = StringUtils.substringBetween(e.attr("href"), "seckill/", "/detail");
    		i++;
    	}
    	for(Element e : eee){
    		seckillName[j] = StringUtils.substringBetween(e.toString(), "<span>", "</span>");
    		j++;
    	}
    	System.out.println(seckillId[2]);
//    	String detailUrl = "http://localhost:8080/seckill/" + ee;
//    	String seckillId = StringUtils.substringBetween(ee, "seckill/", "/detail");
//    	System.out.println(seckillId);
//    	HttpClient httpClient = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet(detailUrl);
//        String content = null;
//        String JSESSIONID = null;
//        String cookiesInfo = null;
//        try {
//            HttpResponse httpResponse = httpClient.execute(httpGet);
//            if (200 == httpResponse.getStatusLine().getStatusCode()) {
//                content = EntityUtils.toString(httpResponse.getEntity());
//                cookiesInfo = httpResponse.getFirstHeader("Set-Cookie").getValue().toString();
//                JSESSIONID = StringUtils.substringBetween(cookiesInfo, "JSESSIONID=", ";");
//                //System.out.println(JSESSIONID);
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    	for(int k=0;k<seckillId.length;k++){
	        DefaultHttpClient client = new DefaultHttpClient();  
	        String exposerUrl = "http://localhost:8080/seckill/seckill/"+seckillId[k]+"/exposer";  
	        HttpPost post = new HttpPost(exposerUrl);  
	        //设置参数，可有可无，并不是最关键的
	
	        post.addHeader("Content-Type", "application/json;charset=UTF-8");  
	        post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");  
	        post.addHeader("Host", "localhost:8080");  
	        post.addHeader("Accept", "*/*");  
	        post.addHeader("Accept-Encoding", "gzip, deflate, br");  
	        post.addHeader("Accept-Language", "zh-CN,zh;q=0.8");  
	        post.addHeader("X-Requested-With", "XMLHttpRequest");
	        org.apache.http.HttpResponse httpResponse = client.execute(post);  
	        
	        String responseString = EntityUtils.toString(httpResponse.getEntity());  
	        md5[k] = StringUtils.substringBetween(responseString.replaceAll("\"", ""), "md5:", ",");
	        System.out.println(md5[k]);
    	}
//        System.out.println(responseString); 
//        DefaultHttpClient client1 = new DefaultHttpClient();  
//        String executionUrl = "http://localhost:8080/seckill/seckill/"+seckillId+"/"+md5+"/execution";
//        System.out.println(executionUrl);
//        HttpPost post1 = new HttpPost(executionUrl);  
//        post1.addHeader(new BasicHeader("Cookie",  
//                "JSESSIONID="+JSESSIONID+";"+"killPhone=99999999999"));  
//        post1.addHeader(new BasicHeader("Cookie", "userName=aqw; killPhone=11111111111; killEmail=1232321312; killPostaddress=3123213123123"));
//        post1.addHeader("Content-Type", "application/json;charset=UTF-8");  
//        post1.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");  
//        post1.addHeader("Host", "localhost:8080");  
//        post1.addHeader("Accept", "*/*");  
//        post1.addHeader("Accept-Encoding", "gzip, deflate, br");  
//        post1.addHeader("Accept-Language", "zh-CN,zh;q=0.8");  
//        post1.addHeader("X-Requested-With", "XMLHttpRequest");
//        post1.addHeader("Referer", "http://localhost:8080/seckill/seckill/1000/detail");
//        post1.addHeader("Origin", "http://localhost:8080");
//        org.apache.http.HttpResponse httpResponse1 = client1.execute(post1);  
//        String responseString1 = EntityUtils.toString(httpResponse1.getEntity());
//        System.out.println(responseString1);
//    	
//    	String newUrl = "http://www.renren.com/PLogin.do";
//    	HttpClient httpclient = new DefaultHttpClient(); 
//    	HttpPost post = new HttpPost(newUrl);
//    	String userName = "13647434862";
//    	String passWord = "19950708wl?";
//        
//    	List<NameValuePair> nvps = new ArrayList<NameValuePair>(); 
//        nvps.add(new BasicNameValuePair("email", userName));  
//        nvps.add(new BasicNameValuePair("password", passWord));  
//    	post.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
//        HttpResponse response = httpclient.execute(post);
//    	System.out.println(response.getFirstHeader("Location").getValue());
//    	System.out.println(response.getEntity().getContent());
//    	Document doc = null;  
//    	doc = Jsoup.connect("http://localhost:8080/seckill/seckill/1000/detail").get();
//    	System.out.println(doc);
    }
    
}