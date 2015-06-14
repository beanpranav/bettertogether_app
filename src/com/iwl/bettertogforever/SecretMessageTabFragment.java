package com.iwl.bettertogforever;

import com.iwl.bettertogforever.connections.utils.BetterTogForeverHttpConnectUtils;
import com.iwl.bettertogforever.model.UserIdCoupleIdPair;
import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecretMessageTabFragment extends Fragment implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.secret_msg_tab_fragment, container, false);

//        Button secretMessageButton = (Button)v.findViewById(R.id.addSecretMessage);
//        secretMessageButton.setOnClickListener(this);
  		TextView secretMessage = (TextView)v.findViewById(R.id.secret_message_cta_display);
  		String secretMsg = getSecretMsg(v);
  		
  		if(secretMsg == null){
  			UserIdCoupleIdPair userIdCoupleId = getUserIdCoupleId(v); 
  			secretMsg = new BetterTogForeverHttpConnectUtils().getSecretMessage(userIdCoupleId.getUserId(), userIdCoupleId.getCoupleId());
  			updateSecretMsgTodb(v, secretMsg);
  			if(secretMsg == null || secretMsg.equals("")){
  				secretMsg = "No secret message for you yet";
  			}
  		}
  		secretMessage.setText(secretMsg);
        return v;
    }
    
    
	private UserIdCoupleIdPair getUserIdCoupleId(View v) {
		BetterTogForeverSqlliteDao dbDao = new ActivityImpl().getDataSourceFromContext(v.getContext());
		dbDao.open();
		UserIdCoupleIdPair userCoupleIdDetail = dbDao.getUserIdCoupleIdPair();
		dbDao.close();
		return userCoupleIdDetail;
	}
	
	public void onClick(View view) {
//		EditText secretMessage = (EditText)getView().findViewById(R.id.secretMsgAddText);
//		String msgToUpdate = secretMessage.getText().toString();
//		UserIdCoupleIdPair userIdCoupleId = getUserIdCoupleId(this.getView());
//		new BetterTogForeverHttpConnectUtils().addSecretMessage(userIdCoupleId.getUserId(), userIdCoupleId.getCoupleId(), msgToUpdate);
//		Toast.makeText(getView().getContext(), "Sent secret message to your loved one", Toast.LENGTH_LONG);
	}
    
	private void updateSecretMsgTodb(View v, String msg) {
		BetterTogForeverSqlliteDao dbDao = new ActivityImpl().getDataSourceFromContext(v.getContext());
		dbDao.open();
		dbDao.insertSecretMsg(msg);
		dbDao.close();
	}

	private String getSecretMsg(View v) {
		BetterTogForeverSqlliteDao dbDao = new ActivityImpl().getDataSourceFromContext(v.getContext());
		dbDao.open();
		String secretMsg = dbDao.getSecretMsg();
		dbDao.close();
		return secretMsg;
	}
}
