package org.xwalk.oritention;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.xwalk.oritention.room.Word;
import org.xwalk.oritention.room.WordViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LoggingMXBean;

import androidx.recyclerview.widget.ListAdapter;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    List<Word> allWords = new ArrayList<>();
    WordViewModel wordViewModel;
    Boolean useCardView;

    public MyRecyclerAdapter(WordViewModel wordViewModel, Boolean useCardView) {
        this.wordViewModel = wordViewModel;
        this.useCardView = useCardView;
    }

    public void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
        Log.d(TAG, "setAllWords: ");

    }

 //   @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
       View itemView = layoutInflater.inflate(R.layout.cell_normal_layout, parent, false);
//        Log.d(TAG, "onCreateViewHolder");
      //  View itemView = layoutInflater.inflate(R.layout.cell_card, parent, false);
        final MyViewHolder holder=new MyViewHolder(itemView);

        holder.itemView.setOnClickListener(v->{
            Uri uri= Uri.parse("https://m.youdao.com/dict?le=eng&q="+holder.textViewEnglish.getText());
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            holder.itemView.getContext().startActivity(intent);
        });
        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Word word=(Word) holder.itemView.getTag(R.id.word_for_view_holder);
                if(b) {
                    holder.textViewChinese.setVisibility(View.GONE);
                    word.setChineseInvisible(true);
                    wordViewModel.UpdateWords(word);
                }else {
                    holder.textViewChinese.setVisibility(View.VISIBLE);
                    word.setChineseInvisible(false);
                    wordViewModel.UpdateWords(word);
                }
            }
        });

        return holder;

    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Word word = allWords.get(position);
        holder.itemView.setTag(R.id.word_for_view_holder,word);
        Log.d(TAG, "onBindViewHolder: position"+position);
        holder.textViewNumber.setText(String.valueOf(position));
        holder.textViewEnglish.setText(word.getWord());
        holder.textViewChinese.setText(word.getChineseName());

        if(word.isChineseInvisible()){
            holder.textViewChinese.setVisibility(View.GONE);
            holder.aSwitch.setChecked(true);
        }
        else {
            holder.textViewChinese.setVisibility(View.VISIBLE);
            holder.aSwitch.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }

    //static 修饰内部类 防止内存泄露
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber, textViewEnglish, textViewChinese;
        Switch aSwitch ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            textViewEnglish = itemView.findViewById(R.id.textViewEnglish);
            textViewChinese = itemView.findViewById(R.id.textViewChinese);
            aSwitch =  itemView.findViewById(R.id.aSwitchChineseInvisible);
        }
    }

}
