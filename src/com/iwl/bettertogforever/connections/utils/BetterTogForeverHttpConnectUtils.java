package com.iwl.bettertogforever.connections.utils;

import org.springframework.web.client.RestTemplate;

import com.iwl.bettertogforever.model.request.AddOrUpdateSecretMessage;
import com.iwl.bettertogforever.model.request.RegIdAdd;
import com.iwl.bettertogforever.model.request.CoupleAdd;
import com.iwl.bettertogforever.model.request.UserPasswd;
import com.iwl.bettertogforever.model.request.UsrIdCplId;
import com.iwl.bettertogforever.model.response.AddCoupleStatusMsg;
import com.iwl.bettertogforever.model.response.AuthUserIdStatus;

public class BetterTogForeverHttpConnectUtils {
	
	private RestTemplate template = new RestTemplate(true);
	private final String YOU_AND_ME_SERVER = "https://you-and-me.herokuapp.com/";
	private final String YOU_AND_ME_LOGIN_PATH = "rest/user/isuser/";
	private final String YOU_AND_ME_ADD_COUPLE_PATH = "rest/couple/addcouple/";
	private final String YOU_AND_ME_ACCEPT_SPOUSE_PATH = "rest/couple/acceptspouse/";
	private final String YOU_AND_ME_DECLINE_SPOUSE_PATH = "rest/couple/declinespouse/";
	private final String YOU_AND_ME_ADD_REGID_PATH = "rest/regid/addregid/";
	private final String YOU_AND_ME_RESEND_ADD_NOTIFICATION_PATH = "rest/couple/resendaddcouplerequest/";
	private final String YOU_AND_ME_SECRET_MESSGAE_PATH = "rest/secretmsg/getsecretmsg/";
	private final String YOU_AND_ME_ADD_SECRET_MESSGAE_PATH = "rest/secretmsg/addsecretmsg/";
	
	public AuthUserIdStatus authenticateUser(String email, String passwd){
		
		UserPasswd request = new UserPasswd();
		request.setEmail(email);
		request.setPasswd(passwd);
		request.setAppId(1);
		AuthUserIdStatus result = template.postForObject(YOU_AND_ME_SERVER + YOU_AND_ME_LOGIN_PATH, request, AuthUserIdStatus.class);
	    return result;
	}
	
	public AddCoupleStatusMsg addCouple(Integer userId, String coupleEmail){
		
		CoupleAdd request = new CoupleAdd();
		request.setUserId(userId);
		request.setSpouseEmail(coupleEmail);
		AddCoupleStatusMsg result = template.postForObject(YOU_AND_ME_SERVER + YOU_AND_ME_ADD_COUPLE_PATH, request, AddCoupleStatusMsg.class);
	    return result;
	}
	
	public void acceptSpouse(Integer coupleId, Integer usrId){
		UsrIdCplId request = new UsrIdCplId();
		request.setCplId(coupleId);
		request.setUsrId(usrId);
		template.postForObject(YOU_AND_ME_SERVER + YOU_AND_ME_ACCEPT_SPOUSE_PATH, request, boolean.class);
	}
	
	public void declineSpouse(Integer coupleId, Integer usrId){
		UsrIdCplId request = new UsrIdCplId();
		request.setCplId(coupleId);
		request.setUsrId(usrId);
		template.postForObject(YOU_AND_ME_SERVER + YOU_AND_ME_DECLINE_SPOUSE_PATH, coupleId, boolean.class);
	}
	
	public boolean addRegid(Integer userId, String regId, String phone){
		RegIdAdd request = new RegIdAdd();
		request.setUsrId(userId);
		request.setRegId(regId);
		request.setPhoneNo(phone);
		boolean result = template.postForObject(YOU_AND_ME_SERVER + YOU_AND_ME_ADD_REGID_PATH, request, Boolean.class);
	    return result;
	}
	
	public String resendAddSpouseNotification(Integer userId, Integer cplId){
		UsrIdCplId request = new UsrIdCplId();
		request.setCplId(cplId);
		request.setUsrId(userId);
		String result = template.postForObject(YOU_AND_ME_SERVER + YOU_AND_ME_RESEND_ADD_NOTIFICATION_PATH, request, String.class);
	    return result;
	}
	
	public String getSecretMessage(Integer userId, Integer cplId){
		UsrIdCplId request = new UsrIdCplId();
		request.setCplId(cplId);
		request.setUsrId(userId);
		String result = template.postForObject(YOU_AND_ME_SERVER + YOU_AND_ME_SECRET_MESSGAE_PATH, request, String.class);
	    return result;
	}
	
	public String addSecretMessage(Integer userId, Integer cplId, String scrtMsg){
		AddOrUpdateSecretMessage request = new AddOrUpdateSecretMessage();
		request.setCplId(cplId);
		request.setUsrId(userId);
		request.setMsg(scrtMsg);
		String result = template.postForObject(YOU_AND_ME_SERVER + YOU_AND_ME_ADD_SECRET_MESSGAE_PATH, request, String.class);
	    return result;
	}
}
