package com.doomonafireballstudios.argylelivewallpaper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

public class SetAsWallpaperActivity extends Activity {
    private boolean startLinesInCorner;
    private String primaryColor;
    private String secondaryColor;
    private String bgColor;
    private String lineColor;
    private float lineThickness;
    private int diamondsX;
    private int diamondsY;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            startLinesInCorner = extras.getBoolean("start_lines_in_corner", true);
            primaryColor = extras.getString("primary_color");
            secondaryColor = extras.getString("secondary_color");
            bgColor = extras.getString("bg_color");
            lineColor = extras.getString("line_color");
            lineThickness = extras.getFloat("line_thickness", 1.0f);
            diamondsX = extras.getInt("diamonds_x", 2);
            diamondsY = extras.getInt("diamonds_y", 2);
        }

        DisplayMetrics metrics = new DisplayMetrics();
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        display.getMetrics(metrics);
        int dpiClass = metrics.densityDpi;
        //float widthF = (float) (((float) width) * (((float) dpiClass) / 160.0f));
        //float heightF = (float) (((float) height) * (((float) dpiClass) / 160.0f));
        float widthF = (float) width;
        float heightF = (float) height;
        Log.v("ArgyleLiveWallpaper", "DPIClass: " + dpiClass + ", Width: " + widthF + ", Height: " + heightF);

        ArgylePattern a = new ArgylePattern(this,
            primaryColor, secondaryColor, bgColor, lineColor,
            lineThickness, diamondsX, diamondsY, widthF, heightF, startLinesInCorner, false, true, mContext);
        setContentView(a);
    }
}
