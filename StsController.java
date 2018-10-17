package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import com.aliyuncs.DefaultAcsClient; 
import com.aliyuncs.profile.DefaultProfile; 
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.vod.model.v20170321.GetMezzanineInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoListRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoListResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest; 
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

/**
 * STS Server
 *
 * @author zhilong <a href="mailto:zhilong.lzl@alibaba-inc.com">Contact me.</a>
 * @version 1.0
 * @since 2018/1/8 下午8:13
 */
@Controller
@RequestMapping("/sts")
public class StsController {
    private static final Logger logger = LoggerFactory.getLogger("sts-server");
    private static String  TAG="showUrl";
    @Value("${REGION}")
    private String region;
    @Value("${STS_API_VERSION}")
    private String stsApiVersion;
    @Value("${ACCESS_KEY_ID}")
    private String accessKeyId;
    @Value("${ACCESS_KEY_SECRET}")
    private String accessKeySecret;
    @Value("${STS_ARN}")
    private String roleArn;
    private static final String ROLE_SESSION_NAME = "rolevidioplayer";

    @ResponseBody
    @RequestMapping("/get")
    public String getToken() {
        // 此处必须为 HTTPS
    	String sts="AccessKeyID：LTAI2oGMCNlkKJBu   AccessKeySecret：x34WPH9IMaHHWxnjdmPT5EzvXswsOg"
    			+ "VideoId=ff35a6eb36344c85839340bd46a36c2a&Format=JSON&<";
        ProtocolType protocolType = ProtocolType.HTTPS;
        try {
            final AssumeRoleResponse response = assumeRole(accessKeyId, accessKeySecret,
                    roleArn, ROLE_SESSION_NAME, protocolType);
            logger.info("Expiration: {}", response.getCredentials().getExpiration());
            logger.info("Access Key Id: {}", response.getCredentials().getAccessKeyId());
            logger.info("Access Key Secret: {}", response.getCredentials().getAccessKeySecret());
            logger.info("Security Token: {}", response.getCredentials().getSecurityToken());

            return JSON.toJSONString(response.getCredentials());
        } catch (ClientException e) {
            logger.error("Failed to get a token, code = {}, message = {}.", e.getErrCode(), e.getErrMsg());
        }

        return "fail";
    }

    private AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret,
                                                 String roleArn, String roleSessionName,
                                                 ProtocolType protocolType) throws ClientException {
        // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
        IClientProfile profile = DefaultProfile.getProfile(region, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        // 创建一个 AssumeRoleRequest 并设置请求参数
        final AssumeRoleRequest request = new AssumeRoleRequest();
        request.setVersion(stsApiVersion);
        request.setMethod(MethodType.POST);
        request.setProtocol(protocolType);
        request.setRoleArn(roleArn);
        request.setRoleSessionName(roleSessionName);
        // 发起请求，并得到response
        return client.getAcsResponse(request);
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
    		 logger.info(TAG, "run: " + response.body().string());
    		} 
    		catch (IOException e)
    		{ e.printStackTrace(); 
    		} } }).start();


    }
    
   public void getVidioinfo(){
	   
	   
	   
   }
   
   GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client) {
	   DefaultProfile profile = DefaultProfile.getProfile( "cn-shanghai", accessKeyId, accessKeySecret);
       GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
       request.setVideoId("视频ID");
       GetVideoPlayAuthResponse response = null;
       try {
           response = client.getAcsResponse(request);
       } catch (ServerException e) {
           throw new RuntimeException("GetVideoPlayAuthRequest Server failed");
       } catch (ClientException e) {
           throw new RuntimeException("GetVideoPlayAuthRequest Client failed");
       }
       response.getPlayAuth();              // 播放凭证
       response.getVideoMeta();             // 视频Meta信息
       return response;
   }
   @ResponseBody
   @RequestMapping("/getvid")
   public  String   getVideoPlayAuth()  {
	   ProtocolType protocolType = ProtocolType.HTTPS;
	   String SaccessKeyId=new String();
	   String SaccessKeySecret=new String();
	   String Sstoken=new String();
	   try {
           final AssumeRoleResponse response = assumeRole(accessKeyId, accessKeySecret,
                   roleArn, ROLE_SESSION_NAME, protocolType);
           SaccessKeySecret=response.getCredentials().getAccessKeySecret();
           SaccessKeyId=response.getCredentials().getAccessKeyId();
           Sstoken=response.getCredentials().getSecurityToken();
           
	   } catch (ClientException e) {
           logger.error("Failed to get a token, code = {}, message = {}.", e.getErrCode(), e.getErrMsg());
       }
           
           
	   String videoId = "47607882ca824b7fb034cf97820d8395";
	   String regionId = "cn-shanghai";
	   DefaultProfile profile = DefaultProfile.getProfile(regionId,SaccessKeyId,SaccessKeySecret,Sstoken);
	   DefaultAcsClient client = new DefaultAcsClient(profile);
	 // GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
      GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
	  request.setVideoId(videoId);
      GetVideoPlayAuthResponse  response;
	try {
		response = client.getAcsResponse(request);
	} catch (ServerException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (ClientException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	   try {
           response = client.getAcsResponse(request);
          // response.getTotal();      
          // response.getVideoMeta();      
          // String  videoid= response.getVideoList().get(0).getVideoId();
           logger.debug(TAG, "run: " + response.getPlayAuth());
           logger.debug(TAG, "run: " +  response.getVideoMeta());
       } catch (ServerException e) {
           throw new RuntimeException("GetVideoPlayAuthRequest Server failed");
       } catch (ClientException e) {
           throw new RuntimeException("GetVideoPlayAuthRequest Client failed");
       }
	   return JSON.toJSONString(response.getPlayAuth());
	   //return response;
	   }

   
   @RequestMapping("/getvidmeta")
   public  GetVideoInfoResponse getVideoMetaAuth()  {
	   ProtocolType protocolType = ProtocolType.HTTPS;
	   String SaccessKeyId=new String();
	   String SaccessKeySecret=new String();
	   String Sstoken=new String();
	   try {
           final AssumeRoleResponse response = assumeRole(accessKeyId, accessKeySecret,
                   roleArn, ROLE_SESSION_NAME, protocolType);
           SaccessKeySecret=response.getCredentials().getAccessKeySecret();
           SaccessKeyId=response.getCredentials().getAccessKeyId();
           Sstoken=response.getCredentials().getSecurityToken();
           
	   } catch (ClientException e) {
           logger.error("Failed to get a token, code = {}, message = {}.", e.getErrCode(), e.getErrMsg());
       }
           
	 
	   String videoId = "47607882ca824b7fb034cf97820d8395";
	   String regionId = "cn-shanghai";
	   DefaultProfile profile = DefaultProfile.getProfile(regionId,SaccessKeyId,SaccessKeySecret,Sstoken);
	   DefaultAcsClient client = new DefaultAcsClient(profile);
	  // GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
	   GetVideoInfoRequest request = new GetVideoInfoRequest();
	   request.setVideoId(videoId);
	   GetVideoInfoResponse response = new GetVideoInfoResponse();
	try {
		response = client.getAcsResponse(request);
	} catch (ServerException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (ClientException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	   try {
           response = client.getAcsResponse(request);
           //response.getTotal();      
          // response.getVideoMeta();      
          // logger.info(TAG, "run: " + response.getVideo().getSize());
          //logger.info(TAG, "run: " +  response.getVideoList().get(0).getTitle());
       } catch (ServerException e) {
           throw new RuntimeException("GetVideoPlayAuthRequest Server failed");
       } catch (ClientException e) {
           throw new RuntimeException("GetVideoPlayAuthRequest Client failed");
       }
	   return response;
	   }
   @ResponseBody
   @RequestMapping("/getVideoUrl")
   public  String getVideoUrlAuth()  {
	   ProtocolType protocolType = ProtocolType.HTTPS;
	   String SaccessKeyId=new String();
	   String SaccessKeySecret=new String();
	   String Sstoken=new String();
	   try {
           final AssumeRoleResponse response = assumeRole(accessKeyId, accessKeySecret,
                   roleArn, ROLE_SESSION_NAME, protocolType);
           SaccessKeySecret=response.getCredentials().getAccessKeySecret();
           SaccessKeyId=response.getCredentials().getAccessKeyId();
           Sstoken=response.getCredentials().getSecurityToken();
           
	   } catch (ClientException e) {
           logger.error("Failed to get a token, code = {}, message = {}.", e.getErrCode(), e.getErrMsg());
       }
           
	 
	   String videoId = "47607882ca824b7fb034cf97820d8395";
	   String regionId = "cn-shanghai";
	   DefaultProfile profile = DefaultProfile.getProfile(regionId,SaccessKeyId,SaccessKeySecret,Sstoken);
	   DefaultAcsClient client = new DefaultAcsClient(profile);
	  // GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
	   GetPlayInfoRequest  request = new GetPlayInfoRequest ();
	   request.setVideoId(videoId);
	   GetPlayInfoResponse  response = new GetPlayInfoResponse ();
	try {
		response = client.getAcsResponse(request);
	} catch (ServerException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (ClientException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	   try {
           response = client.getAcsResponse(request);
           List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
           GetPlayInfoResponse.PlayInfo   pinfo=playInfoList.get(0);
           return JSON.toJSONString(pinfo);
          // String  url=pinfo.getPlayURL();
           //response.getTotal();      
          // response.getVideoMeta();      
          // logger.info(TAG, "run: " + response.getVideo().getSize());
          //logger.info(TAG, "run: " +  response.getVideoList().get(0).getTitle());
       } catch (ServerException e) {
           throw new RuntimeException("GetVideoPlayAuthRequest Server failed");
       } catch (ClientException e) {
           throw new RuntimeException("GetVideoPlayAuthRequest Client failed");
       }

	   }

}
