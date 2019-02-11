package com.uicdb.wo.dexhack;
import android.widget.*;
import android.app.*;
import android.view.*;
import android.view.View.*;
import android.opengl.*;

public class PopupWindowHelper
{
	public static void init(Activity ctx,View.OnClickListener onclicklistener){
	 final PopupWindow popupWindow=new PopupWindow(-2,-1);
		Button b=new Button(ctx);
		b.setText("-\n点\n击\n脱\n壳\n-\nΘ\n-\n长\n按\n消\n失\n-");
		b.setOnClickListener(onclicklistener);
		b.setOnLongClickListener(new View.OnLongClickListener(){

				@Override
				public boolean onLongClick(View view)
				{
					view.setVisibility(View.GONE);
					popupWindow.setTouchable(false);
					return true;
				}
			});
		popupWindow.setContentView(b);
		final View decor=ctx.getWindow().getDecorView();
		decor.post(new Runnable(){

				@Override
				public void run()
				{
					popupWindow.showAtLocation(decor,Gravity.CENTER|Gravity.RIGHT,0,0);
				}
			});
	}
}
