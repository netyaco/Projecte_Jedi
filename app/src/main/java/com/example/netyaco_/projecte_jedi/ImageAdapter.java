package com.example.netyaco_.projecte_jedi;

/**
 * Created by netyaco_ on 03/02/2016.
 */

import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.GridView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.os.Handler;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import android.util.Log;

import java.lang.Thread;
import java.lang.InterruptedException;

public class ImageAdapter extends BaseAdapter{
    private Context mContext;
    private Integer[] pieces;
    private List imageViews;
    private int piece_up = -1;

    public ImageAdapter(Context c) {
        mContext = c;
        List ipieces = new ArrayList();
        for(int i=0; i<8; i++) {
            ipieces.add(i);
            ipieces.add(i);
        }
        Collections.shuffle(ipieces);
        pieces = (Integer[]) ipieces.toArray(new Integer[0]);
        _createImageViews();
    }

    private void _createImageViews() {
        imageViews = new ArrayList();
        for(int position = 0; position < getCount(); position++) {
            ImageView imageView;

            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);

            imageView.setImageResource(R.drawable.saw_icon);
            imageViews.add(imageView);

            installClick(position);
        }
    }

    public int getCount() {
        return 16; //mThumbIds.length;
    }

    public Object getItem(int position) {
        return imageViews.get(position);
    }

    public long getItemId(int position) {
        return pieces[position].longValue();
    }

    // create a new ImageView for each item referenced by the Adapter
    public synchronized View getView(int position, View convertView, ViewGroup parent) {
        return (ImageView) imageViews.get(position);
    }

    public void installClick(int position) {
        // final int pos = position;
        final ImageAdapter self = this;
        Log.d("ImageAdapter", "click *" + Integer.toString(position));
        ImageView imageView =(ImageView)  imageViews.get(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int pos = imageViews.indexOf(v);
                Log.d("ImageAdapter", "click!" + Integer.toString(pos));
                show(pos);


                // FIXME: UI update
                // http://developer.android.com/resources/articles/timed-ui-updates.html
                if (piece_up == -1 || piece_up == pos) {
                    // first click
                    piece_up = pos;
                } else {
                    // second click
                    if (pieces[pos] == pieces[piece_up]) {
                        // ok, it's equal
                        Toast.makeText(mContext, "good!", Toast.LENGTH_LONG).show();
                        //
                        // remove click handler
                        removeClick(pos);
                        removeClick(piece_up);

                    } else {
                        // try again
                        int aux[] = {piece_up, pos};
                        SleepHide update = new SleepHide(mContext, self, aux);
                        Handler mHandler = new Handler();
                        mHandler.postDelayed(update, 2000);
                    }

                    piece_up = -1;
                }

            }
        });
    }

    public void removeClick(int position) {
        ImageView aux;
        aux = (ImageView) imageViews.get(position);
        aux.setOnClickListener(null);
    }

    public void hide(int position) {
        ImageView img;
        img = (ImageView) imageViews.get(position);
        int piece = pieces[position];
        img.setImageResource(R.drawable.saw_icon);
    }

    public void show(int position) {
        ImageView img;
        img = (ImageView) imageViews.get(position);
        int piece = pieces[position];
        img.setImageResource(mThumbIds[piece]);

    }
    //
    // references to our images
    private Integer[] mThumbIds = {
            /*R.drawable.memory1,
            R.drawable.memory2,
            R.drawable.memory3,
            R.drawable.memory4,
            R.drawable.memory5,
            R.drawable.memory6,
            R.drawable.memory7,
            R.drawable.memory8,*/
    };
}
