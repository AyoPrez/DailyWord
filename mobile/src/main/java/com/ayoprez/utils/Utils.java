package com.ayoprez.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayoprez.database.UserMomentsRepository;
import com.ayoprez.deilylang.DetectDeviceLanguage;
import com.ayoprez.deilylang.MainActivity;
import com.ayoprez.deilylang.R;
import com.ayoprez.notification.DeviceBootReceiver;

import java.util.List;

import deilyword.UserMoments;

public class Utils {
	private static final String TAG = Utils.class.getSimpleName();

	private static Utils instance;

	public static Utils getInstance(){
		if(instance == null){
			instance = new Utils();
		}
		return instance;
	}

	public void Create_Dialog(final Context ctx, String message, String button_title, String dialog_title){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
		alertDialogBuilder.setTitle(dialog_title);
		alertDialogBuilder.setMessage(message);
		alertDialogBuilder.setPositiveButton(button_title, new OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent i = new Intent(ctx, MainActivity.class);
				ctx.startActivity(i);

				((Activity)ctx).finish();
			}
		});
		alertDialogBuilder.show();
	}

	public void Create_Dialog_DoNotFinishActivity(final Context ctx, String message,
                                                         String button_title, String dialog_title,
                                                         DialogInterface.OnClickListener onClickListener){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
		alertDialogBuilder.setTitle(dialog_title);
		alertDialogBuilder.setMessage(message);
		alertDialogBuilder.setPositiveButton(button_title, onClickListener);

		alertDialogBuilder.show();
	}

	public float convertDPToPX(Context context, int dp){
		Resources r = context.getResources();
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, r.getDisplayMetrics());
		return px;
	}

	public String WithZero(int i){
		if(i < 10 && i >= 0){
			return 0+String.valueOf(i);
		}else{
			return String.valueOf(i);
		}
	}
	
	public String TakeOutTimeDots(String s){
		int n = s.indexOf(':');
		return s.substring(0, n) + s.substring(n + 1);
	}

    public String putDotsInTime(String s) throws Exception{
        if(s.length() == 4) {
            return s.substring(0, 2) + ":" + s.substring(2, 4);
        } else {
            throw new Exception();
        }
    }
	
	public int TakeHourFromTime(String time){
		String hour = time.substring(0, 2);
		Log.d("Hour", hour);
		return Integer.valueOf(hour);
	}
	
	public int TakeMinuteFromTime(String time){
		String minute = time.substring(3, 5);
		Log.d("Minute", minute);
		return Integer.valueOf(minute);
	}

	public List<UserMoments> getDataFromDatabaseToListView(Context context) {
		return UserMomentsRepository.getAllMoments(context);
	}

	public boolean isMomentsFull(Context context){
		return getDataFromDatabaseToListView(context).size() > 0;
	}

	public void showMoment(Context context, TextView level, TextView time, TextView language, ImageView iv_flag) throws Exception {
		if(getDataFromDatabaseToListView(context).size() > 0) {
			UserMoments userMoments = getDataFromDatabaseToListView(context).get(0);
			language.setText(userMoments.getLanguage());
			level.setText(userMoments.getLevel());
			iv_flag.setImageBitmap(DetectDeviceLanguage.getISO3FromString(userMoments.getLanguage().toLowerCase()).equals("eng") ?
					getRoundedCornerBitmap(context, R.drawable.uk_flag, 40) :
					getRoundedCornerBitmap(context, R.drawable.spanish_flag, 40));
			time.setText(putDotsInTime(userMoments.getTime()));
		}
	}

	public static Bitmap getRoundedCornerBitmap(Context context, int drawable, int pixels) {

		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawable);

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
				.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

}