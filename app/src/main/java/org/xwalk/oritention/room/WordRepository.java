package org.xwalk.oritention.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private LiveData<List<Word>>allWordsLive;
    WordDao wordDao;

    public WordRepository(Context context) {
       WordDatabase wordDatabase=WordDatabase.getDatabase(context.getApplicationContext());
        wordDao= wordDatabase.getWordDao();
       allWordsLive=wordDao.getAllWordsLivedata();
    }
    public void InsertWords(Word...words){
        new InsertAsyncTasks(wordDao).execute(words);
    }
    public void UpdateWords(Word...words){
        new UpdateAsyncTasks(wordDao).execute(words);
    }
    public void DeleteAllWords(){
        new DeleteAllAsyncTasks(wordDao).execute();
    }

    public LiveData<List<Word>> getAllWordsLive() {
        return allWordsLive;
    }

    public void DeleteAsyncTasks(Word...words){
        new DeleteAsyncTasks(wordDao).execute(words);
    }
    static class InsertAsyncTasks extends AsyncTask<Word,Void,Void> {
        private WordDao wordDao;
        public InsertAsyncTasks(WordDao wordDao){
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

    static class UpdateAsyncTasks extends AsyncTask<Word,Void,Void>{
        private WordDao wordDao;
        public UpdateAsyncTasks(WordDao wordDao){
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWords();
            return null;
        }
    }
    static class DeleteAllAsyncTasks extends AsyncTask<Word,Void,Void> {
        private WordDao wordDao;

        public DeleteAllAsyncTasks(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteAllWords();
            return null;
        }
    }
    static class DeleteAsyncTasks extends AsyncTask<Word,Void,Void>{
        private WordDao wordDao;
        public DeleteAsyncTasks(WordDao wordDao){
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords();
            return null;
        }
    }
}
