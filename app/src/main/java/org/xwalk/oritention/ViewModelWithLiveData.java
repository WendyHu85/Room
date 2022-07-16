package org.xwalk.oritention;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class ViewModelWithLiveData extends ViewModel {
  //  private MutableLiveData<Integer> likedNumber;
    private  SavedStateHandle handle;// ViewModelSavedState

public ViewModelWithLiveData(SavedStateHandle handle){
    this.handle = handle;
}
    public MutableLiveData<Integer> getLikedNumber(){
    if(!handle.contains(MainActivity.KEY_NUMBER)){
        handle.set(MainActivity.KEY_NUMBER,0);
    }
    return handle.getLiveData(MainActivity.KEY_NUMBER);
//        if(likedNumber == null){
//            likedNumber =  new MutableLiveData<>();
//            likedNumber.setValue(0);
//        }
//        return  likedNumber;
    }
    public void addLikedNumber(int n){
     //   likedNumber.setValue(likedNumber.getValue()+n);
        getLikedNumber().setValue(getLikedNumber().getValue()+1);
    }
}
