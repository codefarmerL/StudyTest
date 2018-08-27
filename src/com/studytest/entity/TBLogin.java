package com.studytest.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TBLogin {

	private static String userName = "13647434862";  
	private static String password = "19970405tl";  
	private static String JDLoginURL = "https://login.taobao.com/member/login.jhtml?redirectURL=https%3A%2F%2Fwww.taobao.com%2F";
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
			String ncoToken = doc.getElementById("J_NcoToken").attr("value");
			/*String sa_token = doc.getElementById("sa_token").attr("value");
			String uuid = doc.getElementById("uuid").attr("value");
			String eid = doc.getElementById("eid").attr("value");
			String fp = doc.getElementById("sessionId").attr("value");
			String _t = doc.getElementById("token").attr("value");
			String loginType = doc.getElementById("loginType").attr("value");
			String main_flag = doc.getElementById("main_flag").attr("value");
			String pubKey = doc.getElementById("pubKey").attr("value");*/
			
			   // All the parameters post to the web site
			  //建立一个NameValuePair数组，用于存储欲传送的参数，添加相关参数，见上图中的参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>(); 
			nvps.add(new BasicNameValuePair("TPL_username", userName));  
			nvps.add(new BasicNameValuePair("TPL_password", password));  
			nvps.add(new BasicNameValuePair("ncoSig", ""));
			nvps.add(new BasicNameValuePair("ncoSessionid", ""));
			nvps.add(new BasicNameValuePair("ncoToken", ncoToken));
			nvps.add(new BasicNameValuePair("slideCodeShow", "false"));
			nvps.add(new BasicNameValuePair("useMobile", "false"));
			nvps.add(new BasicNameValuePair("lang", "zh_CN"));
			nvps.add(new BasicNameValuePair("loginsite", "0"));
			nvps.add(new BasicNameValuePair("newlogin", "0"));
			nvps.add(new BasicNameValuePair("TPL_redirect_url", "https://www.taobao.com/"));
			nvps.add(new BasicNameValuePair("from", "tbTop"));
			nvps.add(new BasicNameValuePair("fc", "default"));
			nvps.add(new BasicNameValuePair("style", "default"));
			nvps.add(new BasicNameValuePair("css_style", ""));
			nvps.add(new BasicNameValuePair("keyLogin", "false"));
			nvps.add(new BasicNameValuePair("qrLogin", "true"));
			nvps.add(new BasicNameValuePair("newMini", "false"));
			nvps.add(new BasicNameValuePair("newMini2", "false"));
			nvps.add(new BasicNameValuePair("tid", ""));
			nvps.add(new BasicNameValuePair("loginType", "3"));
			nvps.add(new BasicNameValuePair("minititle", ""));
			nvps.add(new BasicNameValuePair("minipara", ""));
			nvps.add(new BasicNameValuePair("pstrong", ""));
			nvps.add(new BasicNameValuePair("sign", ""));
			nvps.add(new BasicNameValuePair("need_sign", ""));
			nvps.add(new BasicNameValuePair("isIgnore", ""));
			nvps.add(new BasicNameValuePair("full_redirect", ""));
			nvps.add(new BasicNameValuePair("sub_jump", ""));
			nvps.add(new BasicNameValuePair("popid", ""));
			nvps.add(new BasicNameValuePair("callback", ""));
			nvps.add(new BasicNameValuePair("guf", ""));
			nvps.add(new BasicNameValuePair("not_duplite_str", ""));
			nvps.add(new BasicNameValuePair("need_user_id", ""));
			nvps.add(new BasicNameValuePair("poy", ""));
			nvps.add(new BasicNameValuePair("gvfdcname", "10"));
			nvps.add(new BasicNameValuePair("gvfdcre", "68747470733A2F2F7777772E74616F62616F2E636F6D2F"));
			nvps.add(new BasicNameValuePair("from_encoding", ""));
			nvps.add(new BasicNameValuePair("sub", ""));
			nvps.add(new BasicNameValuePair("TPL_password_2", password));
			nvps.add(new BasicNameValuePair("loginASR", "1"));
			nvps.add(new BasicNameValuePair("loginASRSuc", "1"));
			nvps.add(new BasicNameValuePair("allp", ""));
			nvps.add(new BasicNameValuePair("oslanguage", "zh-CN"));
			nvps.add(new BasicNameValuePair("sr", "1366*768"));
			nvps.add(new BasicNameValuePair("osVer", "windows|6.1"));
			nvps.add(new BasicNameValuePair("naviVer", "chrome|61.031631"));
			nvps.add(new BasicNameValuePair("osACN", "Mozilla"));
			nvps.add(new BasicNameValuePair("osAV", "5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36"));
			nvps.add(new BasicNameValuePair("osPF", "Win32"));
			nvps.add(new BasicNameValuePair("miserHardInfo", ""));
			nvps.add(new BasicNameValuePair("appkey", "00000000"));
			nvps.add(new BasicNameValuePair("nickLoginLink", ""));
			nvps.add(new BasicNameValuePair("mobileLoginLink", "https://login.taobao.com/member/login.jhtml?spm=a21bo.2017.754894437.1.5af911d9GbAedT&f=top&redirectURL=https://www.taobao.com/&useMobile=true"));
			nvps.add(new BasicNameValuePair("showAssistantLink", ""));
			nvps.add(new BasicNameValuePair("um_token", "HV01PAAZ0b82afee249ddd6a5b06feed0002088b"));
			nvps.add(new BasicNameValuePair("ua", "108#TAb/1/R+/YIbhsqdcbiEDWEx6gjKcLd80l3JIfmHWvZ/0LFH0dYymg/eEp+//YMT5B5Y90/vOlFAFA54/zp2Ri3qTVdgjW4pKZmj4Eo4qU1t46v4AWBdp5mtEwYhcvnThhKjns6fWXACWRsntIEZbMPVDGhRofewU0By6UdfxvZS9sydPXkRDsl2Rm2oKav1KlK712gC1mJFU/lTKJrbxxB/zM3bHBJN1CACrbtRbX723Vnk3ydB980PzXG3u9z55t2vSTan94Qq8QLvgoCKFSW2t14TIpELcikJR5yolLsQn+aPWKI8WjIHt9ycTBnRnDU02J4cQ/5QmCamMj8I/rOSOJCm3NsSSQm++yJKI/txuFP2TBa/VwGb3K9XgRyj9NDyE2OsIllFR9ontNTkkH2EHgBc2N9/F2hx9FAk/kMsa3M8zXqVfHTE+ZP4YLuGMOuJ88eyUY5hOLB1uzbAh11eBaaWktvhjNCc7j3D0PdcRUMwB+mBGxx/Wk2ZI38LvNuuJa0PHf/5pYfUGhchEJtEuE9PJAzauZYM9LlHy9zf5xKlkWzI7decNRlgfHnHYf614TuucJpejQmjXTFrflsV3nxO+YiupNjNVIiQ+ffiVcPSwXFe8+IbXfVbGhNfa/Djs5iSQwEMLwFew/dxtd4cYpTCawbqct/qYOPGlG0Oo07ikA1nt4KRn1fnLaxuR2M1g/UEmTMZDN9fzxBHzJL/yOly56/XrptY1izxVLeUsP5pFwLFsWnoyWw2O729SBJSpI+yXpHRY89hrTKwDa/VpBSrNArX0xkymnrOxyWDt88xqrvs5zJKmKWRorAI9ITiAR3EWQpj6JDLk5n2jVHNGhrOi0MQgX2bHffXVPs/itaYaaqOlpFJkO9FhvvNBKA3oZePZsP8yDNkPKaEoUb9WCE/Kospt5ij+DwinZU2E5iSa7+UgXsSbXXVvJYSMJT3iBlGDPDsSsSN5aDdiUA79w5jGLc="));

			HttpPost httpost = new HttpPost(JDLoginURL); 
			httpost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
			try {  
			    /*登陆成功，获取返回的数据，即html文件*/
			    httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
			    response = httpclient.execute(httpost);  
			    /*CookieStore cookie = httpclient.getCookieStore();
			    System.out.println(cookie);*/
			    System.out.println(response.getStatusLine().getStatusCode());
			   // System.out.println(response.getFirstHeader("Location").getValue());
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
	}

}


