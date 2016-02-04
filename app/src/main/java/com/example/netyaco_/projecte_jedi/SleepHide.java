package com.example.netyaco_.projecte_jedi;

/**
 * Created by netyaco_ on 03/02/2016.
 */

import android.util.Log;
import java.lang.Runnable;
import android.content.Context;

public class SleepHide implements Runnable {

    private Context context;
    private int[] positions;
    private ImageAdapter adapter;

    public SleepHide(Context c, ImageAdapter b, int[] pos) {
        context = c;
        adapter = b;
        positions = pos;

        Log.d("SleepHide", "click!");
        adapter.removeClick(pos[0]);
        adapter.removeClick(pos[1]);
    }

    public void run() {
        Log.d("SleepHide", "run!");
        adapter.hide(positions[0]);
        adapter.hide(positions[1]);
        adapter.installClick(positions[0]);
        adapter.installClick(positions[1]);
    }
}
