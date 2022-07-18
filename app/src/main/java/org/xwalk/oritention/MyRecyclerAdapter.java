package org.xwalk.oritention;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.xwalk.oritention.room.Word;

import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.ListAdapter;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    List<Word> allWords = new ArrayList<>();


    public void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
        Log.d(TAG, "setAllWords: ");

    }

 //   @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View itemView = layoutInflater.inflate(R.layout.cell_normal_layout, parent, false);
//        Log.d(TAG, "onCreateViewHolder");
        View itemView = layoutInflater.inflate(R.layout.cell_card, parent, false);
        return new MyViewHolder(itemView);

    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Word word = allWords.get(position);
        Log.d(TAG, "onBindViewHolder: position"+position);
        holder.textViewNumber.setText(String.valueOf(position));
        holder.textViewEnglish.setText(word.getWord());
        holder.textViewChinese.setText(word.getChineseName());
        holder.itemView.setOnClickListener(v->{
            Uri uri= Uri.parse("https://m.youdao.com/dict?le=eng&q="+holder.textViewEnglish.getText());
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }

    //static 修饰内部类 防止内存泄露
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber, textViewEnglish, textViewChinese;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textViewNumber1);
            textViewEnglish = itemView.findViewById(R.id.textViewEnglish1);
            textViewChinese = itemView.findViewById(R.id.textViewChinese1);
        }
    }

}
