package com.ayoprez.savedWords;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by AyoPrez on 8/08/15.
 */
public class SpinnerAdapter extends ArrayAdapter<CharSequence>{

    public SpinnerAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(CharSequence object) {
        super.add(object);
    }

    public void removeAll(){
      for (int i = 0; i < getCount(); i++){
          super.remove(getItem(i));
      }
    }
}
