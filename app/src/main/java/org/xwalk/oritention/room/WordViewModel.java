package org.xwalk.oritention.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
/*viewModel 的职责是管理界面数据 不是获取数据 仓库类 数据处理*/
public class WordViewModel extends AndroidViewModel {
    private WordDao wordDao;
    private LiveData<List<Word>>allWordsLive;
    private WordRepository wordRepository;
    public WordViewModel(@NonNull Application application) {
        super(application);
        /*
        WordDatabase wordDatabase= WordDatabase.getDatabase(application);
        wordDao = wordDatabase.getWordDao();
        allWordsLive = wordDao.getAllWordsLivedata();*/
        wordRepository = new WordRepository(application);

    }

    public LiveData<List<Word>> getAllWordsLive() {
        return wordRepository.getAllWordsLive();
    }

    public void InsertWords(Word...words){
        wordRepository.InsertWords(words);
    }
    public void UpdateWords(Word...words){
        wordRepository.UpdateWords(words);
    }
    public void DeleteAllWords(){
        wordRepository.DeleteAllWords();
    }
    public void DeleteAsyncTasks(Word...words){
        wordRepository.DeleteAsyncTasks();
    }

}


