//package com.ayoprez.deilylang;
//
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.widget.ListView;
//
//import com.ayoprez.database.SQLMethods;
//import com.ayoprez.database.SQLUtils;
//
//import java.util.ArrayList;
//
//public class ReviewMoments {
//
//	private Context ctx;
//	private ReviewAdapter R_Adapter;
//	private Dialog Dialog;
//	private ArrayList<UserData> String_Review;
//	private ListView Lv_Review;
//
//	private SQLMethods SQLMETHODS;
//	private SQLUtils SQLUTILS;
//
////	public ReviewMoments(Context context, final String Language){
////		this.ctx = context;
////		this.String_Review = new ArrayList<UserData>();
////		this.SQLMETHODS = new SQLMethods(context);
////		this.SQLUTILS = new SQLUtils(context);
////
////		this.Dialog = new Dialog(context);
////		this.Dialog.setContentView(R.layout.reviewmoments_dialog);
////		this.Dialog.setTitle(R.string.moment_review);
////		this.Lv_Review = (ListView) Dialog.findViewById(R.id.lV_review);
////		Button B = (Button) Dialog.findViewById(R.id.button1);
////		B.setOnClickListener(new OnClickListener(){
////
////			@Override
////			public void onClick(View v) {
////				Bundle b = new Bundle();
////				b.putString("applan", Language);
////				//Intent i = new Intent(ctx, NewMomentActivity.class);
////				//i.putExtras(b);
////                //ctx.startActivity(i);
////				Dialog.dismiss();
////				//((Activity)ctx).finish();
////			}
////		});
////
////		this.Lv_Review.setOnItemLongClickListener(new OnItemLongClickListener(){
////
////			@Override
////			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
////				Delete_Dialog(position);
////				return false;
////			}
////		});
////	}
//
//	public void Delete_Dialog(final int position){
//
//		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
//		alertDialogBuilder.setTitle(R.string.dialog_title);
//		alertDialogBuilder.setMessage(R.string.dialog_message);
//		alertDialogBuilder.setPositiveButton(R.string.dialog_positive_button, new DialogInterface.OnClickListener() {
//		    	public void onClick(DialogInterface dialog, int id2) {
//		    		//Some actions delete row
//		    		SQLMETHODS.Delete_Database_Row(String_Review.get(position).getLevel(), String_Review.get(position).getTime(),
//		    				String_Review.get(position).getLanguage());
//
//		    		String_Review.remove(position);
//					Dialog.cancel();
//		     	}
//			}
//		);
//
//		alertDialogBuilder.setNegativeButton(R.string.dialog_negative_button, null);
//
//		alertDialogBuilder.show();
//	}
//
//}