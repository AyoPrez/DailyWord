package com.ayoprez.restfulservice;

/**
 * Created by AyoPrez on 9/08/15.
 */
public class SaveWordResult {

    private int Res;

    public SaveWordResult(Integer Res){
        this.Res = Res;
    }

    public boolean getRes() {
        if(Res == 1){
            return true;
        }else{
            return false;
        }
    }

    public void setRes(int res) {
        Res = res;
    }
}
