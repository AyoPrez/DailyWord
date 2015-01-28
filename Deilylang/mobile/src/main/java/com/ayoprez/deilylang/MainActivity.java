package com.ayoprez.deilylang;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.ayoprez.database.SQLMethods;
import com.ayoprez.database.SQLUtils;
import com.ayoprez.newMoment.NewMomentActivity;
import com.ayoprez.notification.StartAndCancelAlarmManager;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private Button newMomentButton;
    private Context mContext;

    private ListView reviewList;
    private ReviewAdapter reviewAdapter;

    private SQLMethods sqlMethods;
    private SQLUtils sqlUtils;
    private UserData userData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mContext = this;
        this.newMomentButton = (Button) findViewById(R.id.b_main);
        this.reviewList = (ListView) findViewById(R.id.reviewList);
        this.sqlMethods = new SQLMethods(this);
        this.sqlUtils = new SQLUtils(this);

        showReviewList();

        momentsButton();

        itemLongClick();
    }

    private void itemLongClick(){
        reviewList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialogToDeleteItem(position);
                return true;
            }
        });
    }

    private void momentsButton() {
        newMomentButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent newMomentIntent = new Intent(mContext, NewMomentActivity.class);
                startActivity(newMomentIntent);
                ((Activity)mContext).finish();
            }
        });
    }

    public ArrayList<UserData> getDataFromDatabaseToListView(){
        ArrayList<UserData> String_Review = new ArrayList<>();
        String[][] DB_Data = sqlMethods.Recover_Data_Review();

        for(int h = 0; h < sqlUtils.getRowsCount(); h++){
            userData = new UserData(DB_Data[h][0], DB_Data[h][3], DB_Data[h][2], DB_Data[h][1]);
            String_Review.add(userData);
        }
        return String_Review;
    }

    private void showReviewList(){
        reviewAdapter = new ReviewAdapter(mContext, getDataFromDatabaseToListView());
        reviewList.setAdapter(reviewAdapter);
    }

    public void showAlertDialogToDeleteItem(final int selectedItem){
        new AlertDialog.Builder(this)
                .setTitle(R.string.deleteMomentDialogTitle)
                .setMessage(R.string.deleteMomentDialog)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteItemFromDatabase(selectedItem);
                        new StartAndCancelAlarmManager(mContext).cancelAlarmManager();
                        showReviewList();
                    }
                }).create().show();
    }

    private void deleteItemFromDatabase(int selectedItem){
        String languageSelected = getDataFromDatabaseToListView().get(selectedItem).getLanguage();
        String timeSelected = getDataFromDatabaseToListView().get(selectedItem).getTime();
        String levelSelected = getDataFromDatabaseToListView().get(selectedItem).getLevel();

        sqlMethods.Delete_Database_Row(levelSelected, timeSelected, languageSelected);
    }
}