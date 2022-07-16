package org.xwalk.oritention;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends AndroidViewModel {
    public  int number = 0;
    SavedStateHandle handle;
    String key = getApplication().getResources().getString(R.string.data_key);
    String shp_name = getApplication().getResources().getString(R.string.shp_name);
    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;

        if(handle.contains(key)){
            load();
        }
    }
    public LiveData<Integer> getNumber(){
            return handle.getLiveData(key);
    }
    public void load() {
        SharedPreferences shp = getApplication().getSharedPreferences(shp_name, Context.MODE_PRIVATE);
        int x =shp.getInt(key,0);
        handle.set(key,x);
    }
    void save(){
        SharedPreferences shp = getApplication().getSharedPreferences(shp_name,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt(key,getNumber().getValue());
    }
    public void add(int x){
        handle.set(key,getNumber().getValue()+x);
        save();
    }
}
