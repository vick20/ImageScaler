package com.cylique.graphics.scale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.cylique.graphics.Tools;
import android.widget.FrameLayout;

public class ScalerViewGroup extends ViewGroup {
	public ScalerImageView img=null;
	
	
	interface OnActionCompeleteListener{
		public void onScaled(Bitmap bit, int reqCode);
	}
    
	
	void init(Context c){
		img=new ScalerImageView(c);
		addView(img);
	}
	public ScalerViewGroup(Context c){
		super(c);
		init(c);
	}
	
	
	
	public ScalerViewGroup(Context c,AttributeSet attr){
		super(c,attr);
		init(c);
	}
	
	
	@Override
	protected void onLayout(boolean arg0, int left, int top, int right, int bottom) {
		View v=getChildAt(0);
		int top2=v.getWidth()+top;
		int bottom2=top;
		v.layout(left,top2,right,bottom2);
		
		v=getChildAt(1);
		if(v!=null)
				v.layout(left,top,right,bottom);
		
		
	}
	
	public static class ScalerImageView extends ImageView{
		Bitmap bitmap=null;
		boolean requesting=true;
		int reqCode;
		OnActionCompeleteListener listener;
		
		public ScalerImageView(Context context){
			super(context);
		}
		public void setOnActionCompeleteListener(OnActionCompeleteListener listener){
			this.listener=listener;
		}
		public void setScaleBitmap(Bitmap b, int width,int height){
			requesting=true;
			Glide.with(getContext()).
			      load(b).
				  override(width,height).
				  into(this);
				  
		}
		public void setImageBitmap(Bitmap b){
			bitmap=b;
			super.setImageBitmap(b);
			listener.onScaled(bitmap,reqCode);
		}
	    
		public void setImageDrawable(Drawable dr){
			bitmap=Tools.getBitmap(dr);
			super.setImageDrawable(dr);
			listener.onScaled(bitmap,reqCode);
		}
	
     	@Override 
     	protected  void onDraw(Canvas canvas){
	      	if(bitmap!=null){
				  requesting=false;
			      listener.onScaled(bitmap,reqCode);
	      	}
         }
		 public void addListener(OnActionCompeleteListener listener){
			 this.listener=listener;
		 }
		 
		 public void setReqCode(int code){
			 reqCode=code;
		 }
		 
		 
		 
   }
}