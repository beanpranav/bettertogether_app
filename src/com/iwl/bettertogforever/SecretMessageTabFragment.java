package com.iwl.bettertogforever;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecretMessageTabFragment extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.secret_msg_tab_fragment, container, false);
        TextView tv = (TextView) v.findViewById(R.id.secretMsg_tab1);
        tv.setText(this.getTag() + " Content");
        return v;
    }
}