package com.cylique.graphics.scale;

import android.accounts.OnAccountsUpdateListener;
import android.content.Context;
import android.widget.ImageView;
import android.os.Handler;
import com.bumptech.glide.Glide;
import android.widget.TextView;
import com.cylique.graphics.Tools;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import java.util.List;

public class Scaller {
	List<Scaleable> scaleableList;
	OnScaleCompeleteListener listener;
	Handler scaleHandler; 
	static Context context;
	ScalerViewGroup.ScalerImageView img;
	
	//temp object
	Scaleable scaleable;
	int index=0;
	ScalerViewGroup.OnActionCompeleteListener scaleActionListener=new ScalerViewGroup.
	                OnActionCompeleteListener(){
						public void onScaled(Bitmap b,int id){
							for(int i=0;i<scaleableList.size();i++){
								Scaleable sc=scaleableList.get(i);
								if(sc.id==id){
									sc.bitmap=b;
									listener.onScaleCompelete(sc);
								}
							}
							index++;
							if(index<scaleableList.size())
									startScale(index);
						}
					};
	public Scaller(Context context,ScalerViewGroup.ScalerImageView img){
		this.context=context;
		scaleableList=new ArrayList<Scaleable>();
		this.img=img;
	}
	
	
	
	public void add(Scaleable sc){
		scaleableList.add(sc);
	}
	
	public void setOnScaleCompleteListener(OnScaleCompeleteListener scl){
		listener=scl;
	}
	
	public void startScale(int i){
		img.setOnActionCompeleteListener(scaleActionListener);
		//for(int i=0;i<scaleableList.size();i++){
			Scaleable sc=scaleableList.get(i);
			img.setReqCode(sc.id);
		    img.setScaleBitmap(Tools.getBitmap(sc.drawable),sc.width,sc.height);
		//}
		
	}
	
	public static class Scaleable{
		Drawable drawable;
		int resId=-1;
		public Bitmap bitmap;
		public int id;
		public int width;
		public int height;
		public Scaleable setDrawable(Drawable drawable){
			this.drawable=drawable;
			if (drawable==null && resId>0)
				drawable=context .getResources().getDrawable(resId);
			return this;
		}
		
		
		
		
		
	}
	
	public static interface OnScaleCompeleteListener{
		public void onScaleCompelete(Scaleable scaleable);
	}
	
	
}