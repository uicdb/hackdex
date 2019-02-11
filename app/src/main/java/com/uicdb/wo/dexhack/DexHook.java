package com.uicdb.wo.dexhack;
import android.app.*;
import android.os.*;
import android.widget.*;
import de.robv.android.xposed.*;
import de.robv.android.xposed.callbacks.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import android.view.*;

public class DexHook implements IXposedHookLoadPackage
{

	@Override
	public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lparams) throws Throwable
	{
		if(lparams.packageName.equals("com.uicdb.wo.dexhack")){
		XposedHelpers.findAndHookMethod("com.uicdb.wo.dexhack.MainActivity", lparams.classLoader,"isRunInXposed", new XC_MethodReplacement(){

				@Override
				protected Object replaceHookedMethod(XC_MethodHook.MethodHookParam p1) throws Throwable
				{
					makeToast("模块激活成功");
					return true;
				}
			});
		}
		
		if(!lparams.packageName.equals(null)){
			XposedHelpers.findAndHookMethod(Activity.class,"onCreate",Bundle.class,PersistableBundle.class,new XC_MethodHook(){
					@Override 
					protected void	afterHookedMethod(final XC_MethodHook.MethodHookParam xcparams){
						PopupWindowHelper.init((Activity)xcparams.thisObject, new View.OnClickListener(){

								@Override
								public void onClick(View p1)
								{
									HookHelper.hackDex(xcparams.thisObject.getClass(),lparams.packageName,AndroidAppHelper.currentApplication());
								}
							});
						
					}

				});
			XposedHelpers.findAndHookMethod(Activity.class,"onCreate",Bundle.class,new XC_MethodHook(){
					@Override 
					protected void	afterHookedMethod(final XC_MethodHook.MethodHookParam xcparams){
						PopupWindowHelper.init((Activity)xcparams.thisObject, new View.OnClickListener(){

								@Override
								public void onClick(View p1)
								{
									HookHelper.hackDex(xcparams.thisObject.getClass(),lparams.packageName,AndroidAppHelper.currentApplication());
								}
							});

					}

				});
		}
	}
	private void makeToast(String msg){
		Toast.makeText(AndroidAppHelper.currentApplication(),msg,0).show();
	}
}
