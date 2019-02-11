package com.uicdb.wo.dexhack;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		TextView textview=new TextView(this);
		textview.setText("激活本模块后，即可在每个应用里看到悬浮窗，需要脱壳的点击按钮即可,不需要的则长按，悬浮窗会消失");
		textview.setTextSize(30);
		setContentView(textview);
		isRunInXposed();
		PopupWindowHelper.init(this, new Button.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					makeToast("脱壳测试");
					HookHelper.hackDex(MainActivity.this.getClass(),"脱壳测试",MainActivity.this);
				}
			});
    }
	private boolean isRunInXposed(){
		new AlertDialog.Builder(this).setTitle("Wrong:警告").setMessage("本模块未被启用,只能测试脱壳本应用，不能在别的应用里面使用脱壳(其实本应用没有壳)").create().show();
		return false;
	}
	private void makeToast(String msg){
		Toast.makeText(this,msg,0).show();
	}
}
