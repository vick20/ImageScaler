package com.cylique.graphics.scale;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.SimpleTarget;
import com.cylique.graphics.R;
import com.bumptech.glide.Glide;
import android.view.View;

import com.bumptech.glide.request.transition.Transition;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import com.cylique.graphics.Tools;
import java.util.concurrent.Future;
import javax.security.auth.callback.Callback;

public class ScaleView extends View {//View implements Scaller.OnScaleCompeleteListener{
	Bitmap bitmap;
	Paint paint;
	public ScaleView(Context c,Bitmap bitmap){
		super(c);
		this.bitmap=bitmap;
		paint=new Paint();
		paint.setTextSize(20);
		paint.setColor(0xfefebeab);
	}
	

	public void setBitmap(Bitmap bitmap){
		this.bitmap=bitmap;
		invalidate();
	}
	@Override 
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		 
		 if(bitmap!=null)
				 canvas.drawBitmap(bitmap,0,0,paint);
				 else canvas.drawText("booo",100,20,paint);
	}
}