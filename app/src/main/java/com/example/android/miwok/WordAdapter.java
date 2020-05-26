package com.example.android.miwok;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    int colorID;

    @SuppressLint("ResourceType")
    public WordAdapter(@NonNull Activity context, ArrayList<Word> words, int colorID) {
        super(context, 42, words);
        this.colorID = colorID;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // this uses method getItem from the original ArrayAdapter class
        Word currentWord = getItem(position);
        int imageID = currentWord.getImageId();

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if (listItemView == null) {
            if (imageID == -1)
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item_nopic, parent, false);
            else
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item, parent, false);
        }

        int color = ContextCompat.getColor(getContext(),colorID);
        listItemView.setBackgroundColor(color);

        TextView miwokTextView = listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        TextView defaultTextView = listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        if (imageID != -1){
            ImageView imageView = listItemView.findViewById(R.id.the_image_view);
            imageView.setImageResource(imageID);
            imageView.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.tan_background));
        }


        return listItemView;
    }

}
