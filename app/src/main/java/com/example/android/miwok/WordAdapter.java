package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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

    /** Resource ID for the background color for this list of words */
    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {

        /**
         *  Here we are calling the super class ArrayAdapter constructor. and passing in three arguments.
         *  ---> AndroidAdapter(Context context, int resource, List<T> objects) <---
         *  context            == WordAdapter.java
         *  resource           == 0
         *  a list of objects  == words
         *
         */
        super(context, 0, words);

        this.mColorResourceId = colorResourceId;
    }


    /**
     *  This method gets called when the (@listView) trying to display a list of item at a given position. So the (@listView) will pass in a potential view that we could
     *  potential view that we could reuse (convertView). The last input ViewGroup (parent) refers to the parent view group for all these list items which is the ListView itself.
     *
     *  However, the purpose of this method is to get a list item view and return it to the list view.
     *
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


        // Find the ImageView in the list_item.xml layout with the ID image_image_view
        ImageView resourceIdImageView = (ImageView) listItemView.findViewById(R.id.image_image_view);


        // -------> Checks if the currentWord has an image then set the correct image. <--------
        if (currentWord.hasImage()) {
            // Get the resourceIdImageView from the current Word object
            // set this image on the number ImageView
            resourceIdImageView.setImageResource(currentWord.getmImageResourceId());

            //Make sure the view is visible
            resourceIdImageView.setVisibility(View.VISIBLE);
        }
        else {
            //Otherwise hide the ImageView (set visibility GONE)
            resourceIdImageView.setVisibility(View.GONE); //GONE means the View is hidden and doesn't take any space in the layout.
        }

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

//        Button miwokAudioTranslation = (Button) listItemView.findViewById(R.id.);

        // Return the whole list layout (containing TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
