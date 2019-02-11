package com.uicdb.wo.dexhack;

import android.app.*;
import android.os.*;
import android.widget.*;
import java.io.*;
import java.lang.reflect.*;
import android.content.*;

public class HookHelper
{
	public static void hackDex(Class<?> be_hook_clazz,String pkg_name,Context ctx){
		try{
			Class clazz=Class.forName("java.lang.Class");
			Method getDex=clazz.getMethod("getDex",new Class[0]);
			Class Dex = Class.forName("com.android.dex.Dex");
			Method Dex_getBytes = Dex.getDeclaredMethod("getBytes", new Class[0]);
			byte[] bArr = (byte[]) Dex_getBytes.invoke(getDex.invoke(be_hook_clazz, new Object[0]), new Object[0]);
			File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+pkg_name+"_output.dex");
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream fout=new FileOutputStream(file);
			fout.write(bArr);
			fout.flush();
			fout.close();
			Toast.makeText(ctx,"脱壳后的dex在:"+file.getAbsolutePath()+"里",0).show();
		}catch(Exception e){
			Toast.makeText(ctx,e.toString(),0).show();
		}
	}
}
