package com.ayoprez.deilylang;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.ayoprez.database.SQLMethods;
import com.ayoprez.database.SQLUtils;
import com.ayoprez.newMoment.NewMomentActivity;

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
    }

    private void momentsButton() {
        newMomentButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent newMomentIntent = new Intent(mContext, NewMomentActivity.class);
                startActivity(newMomentIntent);
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

//    private void deleteListItem(){
//        reviewList.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                int positionSelected = reviewList.getSelectedItemPosition();
//
//                String languageSelected = getDataFromDatabaseToListView().get(positionSelected).getLanguage();
//                String timeSelected = getDataFromDatabaseToListView().get(positionSelected).getTime();
//                String levelSelected = getDataFromDatabaseToListView().get(positionSelected).getLevel();
//
//                sqlMethods.Delete_Database_Row(levelSelected, timeSelected, languageSelected);
//                return false;
//            }
//        });
//    }

}