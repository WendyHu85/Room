package org.xwalk.oritention;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.xwalk.oritention.room.Word;
import org.xwalk.oritention.room.WordViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WordsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WordsFragment extends Fragment {
private WordViewModel wordViewModel;
private RecyclerView recyclerView;
private MyRecyclerAdapter myRecyclerAdapter1,myRecyclerAdapter2;
private FloatingActionButton floatingActionButton;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WordsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WordsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WordsFragment newInstance(String param1, String param2) {
        WordsFragment fragment = new WordsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_words, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        wordViewModel = ViewModelProviders.of(requireActivity()).get(WordViewModel.class);
        recyclerView = requireActivity().findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        myRecyclerAdapter1 = new MyRecyclerAdapter(wordViewModel,false);
        myRecyclerAdapter2 = new MyRecyclerAdapter(wordViewModel,true);

        recyclerView.setAdapter(myRecyclerAdapter1);

        //    myRecyclerAdapter1.setAllWords(wordViewModel.getAllWordsLive().getValue());
        wordViewModel.getAllWordsLive().observe(requireActivity(), new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                int tmp = myRecyclerAdapter1.getItemCount();
                myRecyclerAdapter1.setAllWords(words);
                myRecyclerAdapter2.setAllWords(words);
                Log.d(TAG, "onChanged1: " + words.size());
                if(tmp!=words.size()) {

                    Log.d(TAG, "onChanged: " + words.size());
                    myRecyclerAdapter1.setAllWords(words);
                    myRecyclerAdapter1.notifyDataSetChanged();
                    myRecyclerAdapter2.notifyDataSetChanged();
                }
            }});
        floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(v->{
            NavController navController= Navigation.findNavController(v);
            navController.navigate(R.id.action_wordsFragment_to_addFragment);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG, "onActivityCreated: ");
    }
}