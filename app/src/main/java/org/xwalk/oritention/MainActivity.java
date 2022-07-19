package org.xwalk.oritention;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.adapters.SeekBarBindingAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.xwalk.oritention.databinding.ActivityMainBinding;
import org.xwalk.oritention.room.Word;
import org.xwalk.oritention.room.WordDao;
import org.xwalk.oritention.room.WordDatabase;
import org.xwalk.oritention.room.WordViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    final static String KEY_NUMBER = "key_number";
            WordDatabase wordDatabase;
            WordDao wordDao;
            Button insert,update,delete,clear;
            TextView textView;
            LiveData<List<Word>>allWordsLive;
            WordViewModel wordViewModel;
RecyclerView recyclerView;
MyRecyclerAdapter myRecyclerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_main_layout);
        wordViewModel =  ViewModelProviders.of(this).get(WordViewModel.class);
        wordViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                int tmp = myRecyclerAdapter.getItemCount();
                if(tmp!=words.size()) {
                    StringBuilder text = new StringBuilder();
                    for (int i = 0; i < words.size(); i++) {
                        Word word = words.get(i);
                        text.append(word.getId()).append(":").append(word.getWord()).append("=").append(word.getChineseName()).append("\n");
                    }
                    //      textView.setText(text);
                    Log.d(TAG, "onChanged: " + words.size());
                    myRecyclerAdapter.setAllWords(words);
                    myRecyclerAdapter.notifyDataSetChanged();
                }
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        myRecyclerAdapter = new MyRecyclerAdapter(wordViewModel,false);
        Log.d(TAG, "MyRecyclerAdapter: ");
        recyclerView.setAdapter(myRecyclerAdapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));





        /* view model instead
        wordDatabase = Room.databaseBuilder(this,WordDatabase.class,"word_database").allowMainThreadQueries().build();
        wordDao = wordDatabase.getWordDao();
        allWordsLive = wordDao.getAllWordsLivedata();
        allWordsLive.observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                StringBuilder text =new StringBuilder();
                for(int i=0;i<words.size();i++){
                    Word word = words.get(i);
                    text.append(word.getId()).append(":").append(word.getWord()).append("=").append(word.getChineseName()).append("\n");
                }
                textView.setText(text);
            }
        });
        */

        insert = findViewById(R.id.insert);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        clear = findViewById(R.id.clear);
    //    textView = findViewById(R.id.textView6);
                insert.setOnClickListener(v->{
            Word word1 = new Word("hello","你好");
            Word word2 = new Word("hello","你好啊");
            String[] word={"hello1",
                    "hello2",
                    "hello3",
                    "hello4",
                    "hello5"};
            String[] name={"你好1",
                            "你好2",
                            "你好3",
                            "你好4",
                            "你好5"};
            for(int i=0;i<word.length;i++) {
                wordViewModel.InsertWords(new Word(word[i], name[i]));
            }
          //  wordDao.insertWords(word1,word2);
            //        new InsertAsyncTasks(wordDao).execute(word1,word2);

        });
        clear.setOnClickListener(v->{
        //    wordDao.deleteAllWords();
          //  new DeleteAllAsyncTasks(wordDao).execute();
            wordViewModel.DeleteAllWords();

        });
        update.setOnClickListener(v->{
            Word word = new Word("hi","huhu");
            word.setId(48);
          //  new UpdateAsyncTasks(wordDao).execute();

        });
        delete.setOnClickListener(v->{
            Word word = new Word("hi","huhu");
            word.setId(37);
          //  new DeleteAsyncTasks(wordDao).execute();

        });

    }


    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.room_main_layout);
//        wordDatabase = Room.databaseBuilder(this,WordDatabase.class,"word_database").allowMainThreadQueries().build();
//        wordDao = wordDatabase.getWordDao();
//        insert = findViewById(R.id.insert);
//        update = findViewById(R.id.update);
//        delete = findViewById(R.id.delete);
//        clear = findViewById(R.id.clear);
//        textView = findViewById(R.id.textView6);
//        insert.setOnClickListener(v->{
//            Word word1 = new Word("hello","你好");
//            Word word2 = new Word("hello","你好啊");
//            wordDao.insertWords(word1,word2);
//            updateView();
//        });
//        clear.setOnClickListener(v->{
//            wordDao.deleteAllWords();
//            updateView();
//        });
//        update.setOnClickListener(v->{
//            Word word = new Word("hi","huhu");
//            word.setId(48);
//            wordDao.updateWords(word);
//            updateView();
//        });
//        delete.setOnClickListener(v->{
//            Word word = new Word("hi","huhu");
//            word.setId(37);
//            wordDao.deleteWords(word);
//            updateView();
//        });
//
//    }

//    void updateView(){
//        List<Word> list = wordDao.getAllWords();
//        String text = "";
//        for(int i = 0; i<list.size();i++){
//            Word word = list.get(i);
//            text += word.getId()+":"+word.getWord()+"="+word.getChineseName()+"\n";
//        }
//       textView.setText(text);
//    }



    /*20以前 lifecycle livedata navigation viewmodel dataBinding
    MyViewModel myViewModel;
    ViewModelWithLiveData viewModelWithLiveData;
    ActivityMainBinding binding;
    TextView textView;
    final static String KEY_NUMBER = "key_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*LiveData viewmodel
        setContentView(R.layout.activity_main);
        textView =  findViewById(R.id.textView);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModelWithLiveData = ViewModelProviders.of(this).get(ViewModelWithLiveData.class);
        viewModelWithLiveData.getLikedNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText(String.valueOf(integer));
            }
        });///////
      //  binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
     //   binding.textView.setText("dd");
        //viewModelWithLiveData=   new ViewModelProvider(this).get(ViewModelWithLiveData.class) ;
//
//     viewModelWithLiveData = new ViewModelProvider(this,new SavedStateViewModelFactory(getApplication(), new SavedStateRegistryOwner() {
//         @NonNull
//         @Override
//         public SavedStateRegistry getSavedStateRegistry() {
//             return null;
//         }
//
//         @NonNull
//         @Override
//         public Lifecycle getLifecycle() {
//             return null;
//         }
//     })).get(ViewModelWithLiveData.class) ;

        //    viewModelWithLiveData = ViewModelProviders.of(this).get(ViewModelWithLiveData.class);
        //  binding.setData(viewModelWithLiveData);
       //binding.setLifecycleOwner(this);

        // SharedPreferences shp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences shp = getSharedPreferences("MY_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt("NUMBER", 100);
        editor.apply();

        int x = shp.getInt("NUMBER", 0);

        //内存泄露 this getApplicationContext


         setContentView(R.layout.navigation_layout);
//        NavController controller = Navigation.findNavController(this, R.id.fragmentContainerView2);
//        NavigationUI.setupActionBarWithNavController(this,controller);
    }

    @Override
    protected void onStart() {
        super.onStart();
        NavController controller = Navigation.findNavController(this, R.id.fragmentContainerView2);
        NavigationUI.setupActionBarWithNavController(this,controller);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController controller = Navigation.findNavController(this,R.id.fragmentContainerView2);
        return controller.navigateUp();
       // return super.onSupportNavigateUp();
    }
    */
}