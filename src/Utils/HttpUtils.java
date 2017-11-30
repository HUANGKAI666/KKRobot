package Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import bean.ChatMessage;
import bean.ChatMessage.Type;
import bean.Result;

public class HttpUtils {
	private static String URL = "http://www.tuling123.com/openapi/api";
	private static String KEY = "7a0d22c653e648bfa665f24dfb1560e5";
	
	//������Ϣ�õ����ص���Ϣ
	public static ChatMessage sendMessage(String msg){
		ChatMessage chatMessage = new ChatMessage(msg, null, null);
		String jsonRes = doGet(msg);
		Gson gson = new Gson();
		Result result = null;
		
		try {
			result = gson.fromJson(jsonRes, Result.class);//json变成对象
			chatMessage.setMsg(result.getText());
			if (result.getUrl()!=null) {
				chatMessage.setUrl(result.getUrl());
			}
		
		} catch (JsonSyntaxException e) {
			// TODO: handle exception
			chatMessage.setMsg("�ո�С�翪�˸�С��������¡���");
		}
		chatMessage.setDate(new Date());
		chatMessage.setType(Type.INCOMING);
		
		
		return chatMessage;
		
		
	}
	public static String doGet(String msg) {
		// TODO Auto-generated method stub
		String  result = "";
		String url=setParams(msg);
		InputStream is = null;
		ByteArrayOutputStream bao = null;
		try {
			
			java.net.URL neturl = new java.net.URL(url);
			 HttpURLConnection conn = (HttpURLConnection) neturl.openConnection();
			 conn.setReadTimeout(5*1000);
			 conn.setConnectTimeout(5*1000);
			 conn.setRequestMethod("GET");
			 
			 
			  is=  conn.getInputStream();
			  int len = -1;
			  byte[] buf = new byte[128];
			  bao = new ByteArrayOutputStream();
			
			  while((len = is.read(buf) )!= -1){
				  bao.write(buf, 0, len);
			  }
			 bao.flush();
			result = new String(bao.toByteArray());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (bao!=null) {
				try {
					bao.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
		
	}
	public static String setParams(String msg) {
		// TODO Auto-generated method stub;
		String url="";
		try {
			url = URL+"?key="+KEY+"&info="+URLEncoder.encode(msg,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	
	
}
