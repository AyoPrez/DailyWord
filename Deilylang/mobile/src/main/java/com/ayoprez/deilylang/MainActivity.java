package com.ayoprez.deilylang;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.ayoprez.database.CreateDatabase;
import com.ayoprez.database.UserMomentsRepository;
import com.ayoprez.newMoment.NewMomentActivity;
import com.ayoprez.notification.StartAndCancelAlarmManager;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemLongClick;
import deilyword.UserMoments;

public class MainActivity extends Activity {

    @InjectView(R.id.reviewList) ListView mReviewList;

    @OnClick(R.id.b_main) void mMomentsButton(){
        Intent newMomentIntent = new Intent(this, NewMomentActivity.class);
        startActivity(newMomentIntent);
        ((Activity) this).finish();
    }

    @OnItemLongClick(R.id.reviewList) boolean longItem(int position){
        showAlertDialogToDeleteItem(this, position);
        return true;
    }

    private ReviewAdapter mReviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        new CreateDatabase(this);

        showReviewList(this);
    }

    public List<UserMoments> getDataFromDatabaseToListView(Context mContext) {
        return new UserMomentsRepository().getAllMoments(mContext);
    }

    private void showReviewList(Context mContext) {
        mReviewAdapter = new ReviewAdapter(mContext, getDataFromDatabaseToListView(mContext));
        mReviewList.setAdapter(mReviewAdapter);
    }

    public void showAlertDialogToDeleteItem(final Context mContext, final int selectedItem) {
        UserMomentsRepository userMomentsRepository = new UserMomentsRepository();
        List<UserMoments> userMoments = getDataFromDatabaseToListView(mContext);
        final int momentId = (int)userMomentsRepository.getIdFromData(mContext, userMoments.get(selectedItem));

        new AlertDialog.Builder(this)
                .setTitle(R.string.deleteMomentDialogTitle)
                .setMessage(R.string.deleteMomentDialog)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            deleteItemFromDatabase(mContext, selectedItem);
                            new StartAndCancelAlarmManager(mContext, momentId).cancelAlarmManager();
                            Log.e("MomentId", "" + momentId);
                            showReviewList(mContext);
                        } catch (Exception e) {
                            Toast.makeText(mContext, getString(R.string.errorDeletingMoment), Toast.LENGTH_LONG).show();
                        }
                    }
                }).create().show();
    }

    private void deleteItemFromDatabase(Context context, int selectedItem) {
        new UserMomentsRepository().deleteMomentWithId(context, getIdToDelete(context, selectedItem));
    }

    private long getIdToDelete(Context context, int selectedItem){
        UserMoments userMoments = new UserMoments();
        userMoments.setLanguage(getDataFromDatabaseToListView(context).get(selectedItem).getLanguage());
        userMoments.setTime(getDataFromDatabaseToListView(context).get(selectedItem).getTime());
        userMoments.setLevel(getDataFromDatabaseToListView(context).get(selectedItem).getLevel());

        return new UserMomentsRepository().getIdFromData(context, userMoments);
    }
}