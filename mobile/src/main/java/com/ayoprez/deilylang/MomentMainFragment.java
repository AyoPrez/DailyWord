package com.ayoprez.deilylang;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayoprez.database.UserMomentsRepository;
import com.ayoprez.notification.StartAndCancelAlarmManager;
import com.ayoprez.utils.Utils;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.CustomEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import deilyword.UserMoments;

/**
 * Created by ayo on 04.03.16.
 */
public class MomentMainFragment extends Fragment {
    private static final String TAG = MomentMainFragment.class.getSimpleName();

    private static MomentMainFragment instance;

    //Tests
//    @OnClick(R.id.buttonn) void newNotification(){
//        Test.testNotification(this);
//    }

    @Bind(R.id.iv_flag)
    ImageView iv_flag;

    @Bind(R.id.tv_language)
    TextView tv_language;

    @Bind(R.id.tv_level)
    TextView tv_level;

    @Bind(R.id.tv_time)
    TextView tv_time;

    @OnClick(R.id.ib_delete_moment)
    void deleteMoment(){
        Crashlytics.getInstance().answers.logCustom(new CustomEvent("DeleteMoment"));
        showAlertDialogToDeleteItem();
    }

    public static Fragment getInstance(){
        if(instance == null){
            instance = new MomentMainFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_withmoment, container, false);
        ButterKnife.bind(this, view);

        fillFields();

        return view;
    }

    private void fillFields(){
        try {
            Utils.getInstance().showMoment(getActivity(), tv_level, tv_time, tv_language, iv_flag);
        } catch (Exception e) {
            ErrorHandler.getInstance().Error(TAG, e.toString());
            ErrorHandler.getInstance().informUser(getActivity(), getActivity().getString(R.string.errorDefault));
        }
    }

    private void checkMoments(){
        if(Utils.getInstance().isMomentsFull()){
            fillFields();
        }else{
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, NoMomentMainFragment.getInstance()).commit();
        }
    }

    private void showAlertDialogToDeleteItem() {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                try {
                    deleteItemFromDatabase(getActivity(), 0);
                    checkMoments();
                } catch (Exception e) {
                    ErrorHandler.getInstance().Error(TAG, e.toString());
                    ErrorHandler.getInstance().informUser(getActivity(), getActivity().getString(R.string.errorDefault));
                }
            }
        };

        Utils.getInstance().Create_Dialog_DoNotFinishActivity(getActivity(), getActivity().getString(R.string.deleteMomentDialog),
                getActivity().getString(android.R.string.yes), getActivity().getString(R.string.deleteMomentDialogTitle), onClickListener);

    }

    private void deleteItemFromDatabase(Context context, int selectedItem) {
        try {
            new StartAndCancelAlarmManager(context, Utils.getInstance().getDataFromDatabaseToListView().get(selectedItem)).cancelAlarmManager();
            UserMomentsRepository.deleteMomentWithId(getIdToDelete(selectedItem));
        }catch(Exception e){
            ErrorHandler.getInstance().Error(TAG, e.toString());
            ErrorHandler.getInstance().informUser(context, context.getString(R.string.errorDefault));
        }
    }

    private long getIdToDelete(int selectedItem){
        UserMoments userMoments = new UserMoments();
        userMoments.setLanguage(Utils.getInstance().getDataFromDatabaseToListView().get(selectedItem).getLanguage());
        userMoments.setTime(Utils.getInstance().getDataFromDatabaseToListView().get(selectedItem).getTime());
        userMoments.setLevel(Utils.getInstance().getDataFromDatabaseToListView().get(selectedItem).getLevel());

        return UserMomentsRepository.getIdFromData(userMoments);
    }
}
