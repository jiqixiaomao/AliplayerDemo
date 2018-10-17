package com.example.demo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
@SpringBootApplication
public class MyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
		
	
		        String regionId = "cn-shanghai";
		        String endpoint = "sts.aliyuncs.com";
		        String accessKeyId = "LTAIFKMgbVKNitRb";
		        String accessKeySecret = "PX6ptwCeJ4rtv1iZALCi4DEzuX9GJ1";
		        String roleArn = "Arnacs:ram::1207688914473752:role/rolevidioplayer";
		        String roleSessionName = "rolevidioplayer";
		        String url="http://techbyself.cn?Action=GetPlayInfo&"
		    			+ "VideoId=ff35a6eb36344c85839340bd46a36c2a&Format=JSON";
		    	OkHttpClient okHttpClient = new OkHttpClient(); 
		    	final Request requestx = new Request.Builder() .url(url) .build(); 
		    	final Call call = okHttpClient.newCall(requestx); 
		    	new Thread(new Runnable() { 
		    		@Override public void run() 
		    		{ try { Response response = call.execute(); 
		    		 System.out.println("run: ");
		    		 System.out.println("run: " + response.body().string());
		    		} 
		    		catch (IOException e)
		    		{ e.printStackTrace(); 
		    		} } }).start();
		    	
		        String policy = "{\n" +
		                "    \"Version\": \"1\", \n" +
		                "    \"Statement\": [\n" +
		                "        {\n" +
		                "            \"Action\": [\n" +
		                "                \"oss:*\"\n" +
		                "            ], \n" +
		                "            \"Resource\": [\n" +
		                "                \"acs:oss:*:*:*\" \n" +
		                "            ], \n" +
		                "            \"Effect\": \"Allow\"\n" +
		                "        }\n" +
		                "    ]\n" +
		                "}";
		     
		        try {
		           DefaultProfile.addEndpoint(regionId,regionId, "Sts", endpoint);
		           IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
		           DefaultAcsClient client = new DefaultAcsClient(profile);
		           final AssumeRoleRequest request = new AssumeRoleRequest();
		           request.setMethod(MethodType.POST);
		           request.setRoleArn(roleArn);
		           request.setRoleSessionName(roleSessionName);
		           request.setPolicy(null); //可选，此处可以填写为NULL
		           final AssumeRoleResponse response = client.getAcsResponse(request);
		           System.out.println("Expiration: " + response.getCredentials().getExpiration());
		           System.out.println("Access Key Id: " + response.getCredentials().getAccessKeyId());
		           System.out.println("Access Key Secret: " + response.getCredentials().getAccessKeySecret());
		           System.out.println("Security Token: " + response.getCredentials().getSecurityToken());
		        } catch (Exception e) {
		        }
		
	}
	
	
	public void showUrl(){
    	String url="http://vod.cn-shanghai.aliyuncs.com/?Action=GetPlayInfo&"
    			+ "VideoId=ff35a6eb36344c85839340bd46a36c2a&Format=JSON";
    	OkHttpClient okHttpClient = new OkHttpClient(); 
    	final Request request = new Request.Builder() .url(url) .build(); 
    	final Call call = okHttpClient.newCall(request); 
    	new Thread(new Runnable() { 
    		@Override public void run() 
    		{ try { Response response = call.execute(); 
    		 System.out.println("run: ");
    		 System.out.println("run: " + response.body().string());
    		} 
    		catch (IOException e)
    		{ e.printStackTrace(); 
    		} } }).start();


    }
}
