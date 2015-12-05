package com.ayoprez.deilylang;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.ListView;
import android.widget.Toast;

import com.ayoprez.database.UserMomentsRepository;
import com.ayoprez.notification.StartAndCancelAlarmManager;
import com.ayoprez.utils.Utils;

import java.util.List;

import deilyword.UserMoments;

/**
 * Created by AyoPrez on 10.10.15.
 */
public class ReviewList {

    private static final String LOG_TAG = ReviewList.class.getSimpleName();

    protected ReviewAdapter reviewAdapter;
    private ListView reviewList;

    public ReviewList(ListView reviewList){
        this.reviewList = reviewList;
    }

    private List<UserMoments> getDataFromDatabaseToListView(Context context) {
        return new UserMomentsRepository().getAllMoments(context);
    }

    public void showReviewList(Context context) {
        reviewAdapter = new ReviewAdapter(context, getDataFromDatabaseToListView(context));
        reviewList.setAdapter(reviewAdapter);
    }

    public void showAlertDialogToDeleteItem(final Context context, final int selectedItem) {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                try {
                    deleteItemFromDatabase(context, selectedItem);
                    showReviewList(context);
                } catch (Exception e) {
                    ErrorHandler.getInstance().Error(LOG_TAG, e.toString());
                    ErrorHandler.getInstance().informUser(context, context.getString(R.string.errorDefault));
                }
            }
        };

        Utils.Create_Dialog_DoNotFinishActivity(context, context.getString(R.string.deleteMomentDialog),
                context.getString(android.R.string.yes), context.getString(R.string.deleteMomentDialogTitle), onClickListener);

    }

    private long getIdToDelete(Context context, int selectedItem){
        UserMoments userMoments = new UserMoments();
        userMoments.setLanguage(getDataFromDatabaseToListView(context).get(selectedItem).getLanguage());
        userMoments.setTime(getDataFromDatabaseToListView(context).get(selectedItem).getTime());
        userMoments.setLevel(getDataFromDatabaseToListView(context).get(selectedItem).getLevel());

        return new UserMomentsRepository().getIdFromData(context, userMoments);
    }

    private void deleteItemFromDatabase(Context context, int selectedItem) {
        try {
            new StartAndCancelAlarmManager(context, getDataFromDatabaseToListView(context).get(selectedItem)).cancelAlarmManager();
            new UserMomentsRepository().deleteMomentWithId(context, getIdToDelete(context, selectedItem));
        }catch(Exception e){
            ErrorHandler.getInstance().Error(LOG_TAG, e.toString());
            ErrorHandler.getInstance().informUser(context, context.getString(R.string.errorDefault));
        }
    }
}