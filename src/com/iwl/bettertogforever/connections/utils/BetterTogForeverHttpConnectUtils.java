package com.iwl.bettertogforever.connections.utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.iwl.bettertogforever.model.WishList;
import com.iwl.bettertogforever.model.request.AddNewList;
import com.iwl.bettertogforever.model.request.AddOrUpdateSecretMessage;
import com.iwl.bettertogforever.model.request.AddRemoveToList;
import com.iwl.bettertogforever.model.request.GetFullList;
import com.iwl.bettertogforever.model.request.RegIdAdd;
import com.iwl.bettertogforever.model.request.CoupleAdd;
import com.iwl.bettertogforever.model.request.UserPasswd;
import com.iwl.bettertogforever.model.request.UsrIdCplId;
import com.iwl.bettertogforever.model.response.AddCoupleStatusMsg;
import com.iwl.bettertogforever.model.response.AuthUserIdStatus;
import com.iwl.bettertogforever.model.response.FullList;

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
	private final String YOU_AND_ME_ADD_NEW_LIST_PATH = "rest/list/addNewList/";
	private final String YOU_AND_ME_MODIFY_LIST_PATH = "rest/list/addremovetolist/";
	private final String YOU_AND_ME_FULL_LIST_PATH = "rest/list/getList/";
	private final String YOU_AND_ME_ALL_COUPLE_LISTS_PATH = "rest/list/getAllWishListsForCouple/";
	
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
	
	public Integer addNewList(Integer cplId, String listName, Integer usrId){
		AddNewList request = new AddNewList();
		request.setCplId(cplId);
		request.setListDescription(listName);
		request.setUsrId(usrId);
		Integer result = template.postForObject(YOU_AND_ME_SERVER + YOU_AND_ME_ADD_NEW_LIST_PATH, request, Integer.class);
	    return result;
	}
	
	public boolean addToList(Map<String, String> itemsToadd, Integer cplId, Integer listId) throws ClassNotFoundException, SQLException, IOException{

		AddRemoveToList request = new AddRemoveToList();
		request.setCplId(cplId);
		request.setItemsToAdd(itemsToadd);
		request.setItemsToRemove(new ArrayList<Integer>());
		request.setListId(listId);
		
		boolean result = template.postForObject(YOU_AND_ME_SERVER + YOU_AND_ME_MODIFY_LIST_PATH, request, Boolean.class);
		return result;
	}
	
	public boolean removeFromList(List<Integer> itemsToRemove, Integer cplId, Integer listId) throws ClassNotFoundException, SQLException, IOException{

		AddRemoveToList request = new AddRemoveToList();
		request.setCplId(cplId);
		request.setItemsToAdd(new HashMap<String, String>());
		request.setItemsToRemove(itemsToRemove);
		request.setListId(listId);
		
		boolean result = template.postForObject(YOU_AND_ME_SERVER + YOU_AND_ME_MODIFY_LIST_PATH, request, Boolean.class);
		return result;
	}
	
	public FullList getFullList(Integer cplId, Integer listId) throws ClassNotFoundException, SQLException, IOException{

		GetFullList request = new GetFullList();
		request.setCplId(cplId);
		request.setListId(listId);
		FullList result = template.postForObject(YOU_AND_ME_SERVER + YOU_AND_ME_FULL_LIST_PATH, request, FullList.class);
		return result;
	}
	
	public List<WishList> getCoupleLists(Integer cplId) throws ClassNotFoundException, SQLException, IOException{

		UsrIdCplId request = new UsrIdCplId();
		request.setCplId(cplId);
		List result = template.postForObject(YOU_AND_ME_SERVER + YOU_AND_ME_ALL_COUPLE_LISTS_PATH, request, List.class);
		
		//Always comes a LinkedHashMap so converting
		List<WishList> resultWishLists = convertToWishListList(result);
		return resultWishLists;
	}

	private List<WishList> convertToWishListList(List result) {
		List<WishList> resultWishLists = new ArrayList<WishList>();
		for(int i =0; i<result.size(); i++){
			HashMap map = (HashMap) result.get(i);
			Integer listId = (Integer) map.get("id");
			String  description = (String) map.get("description");
			
			WishList obj = new WishList();
			obj.setDescription(description);
			obj.setId(listId);
			resultWishLists.add(obj);
		}
		return resultWishLists;
	}
}
