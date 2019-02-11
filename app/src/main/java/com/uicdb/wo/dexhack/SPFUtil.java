package com.uicdb.wo.dexhack;
import android.app.*;
import android.content.*;

public class SPFUtil
{
	public SharedPreferences spf;
	public SharedPreferences.Editor spf_edit;
	public SPFUtil(Context ctx,String spfName){
		spf=ctx.getSharedPreferences(spfName,Activity.MODE_WORLD_READABLE);
		spf_edit=spf.edit();
	}
	public void putString(String key,String value){
		spf_edit.putString(key,value);
		spf_edit.commit();
	}
	public void getString(String key,String def){
		spf.getString(key,def);
	}
}
