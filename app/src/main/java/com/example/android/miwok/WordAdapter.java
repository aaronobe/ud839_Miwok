package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a WordAdapter class that extends/inherits the ArrayAdapter<Word> .
 * The ArrayAdapter<Word> will expert a list of Word objects.
 *
 * Created by Aaron on 16/10/2017.
 */
public class WordAdapter extends ArrayAdapter<Word> {

    private static final String LOG_TAG = Word.class.getSimpleName();

    public WordAdapter(Activity context, ArrayList<Word> words) {



        /**
         *  Here we are calling the super class ArrayAdapter constructor. and passing in three arguments.
         *  ---> AndroidAdapter(Context context, int resource, List<T> objects) <---
         *  context            == WordAdapter.java
         *  resource           == 0
         *  a list of objects  == words
         *
         */
        super(context, 0, words);
    }

    /**
     *  Provides a view for an AdapterView (ListView, GridView, etc..)
     *
     *  We are overriding the getView method from the (Super Class) and adding in our own
     *  logic for a specific Use Case.
     *
     * @param position      The AdapterView position that is requesting a view.
     * @param convertView   The recycled view to populate.
     *                      (search online for "android view recycling" to learn more)
     * @param parent        The parent viewGroup that is used for inflation.
     * @return              The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view
        TextView miwokTranslationTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the miwokTranslation from the current Word object
        // set this text on the number TextView
        miwokTranslationTextView.setText(currentWord.getmMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view
        TextView defaultTranslationTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the defaultTranslation from the current Word object
        // set this text on the number TextView
        defaultTranslationTextView.setText(currentWord.getmDefaultTranslation());

        // Return the whole list layout (containing TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
