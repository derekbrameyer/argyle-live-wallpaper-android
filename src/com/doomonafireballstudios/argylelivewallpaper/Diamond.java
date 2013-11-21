package com.doomonafireballstudios.argylelivewallpaper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by IntelliJ IDEA.
 * User: derek
 * Date: 12/13/11
 * Time: 4:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Diamond extends View {
    private String primaryColorHex;
    private String secondaryColorHex;
    private String bgColorHex;
    private String lineColorHex;
    private int vertDiamonds;
    private int horizDiamonds;
    private boolean startInCorner;

    public Diamond(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);

        Paint bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(Color.parseColor(bgColorHex));
        c.drawPaint(bgPaint);

        Paint primaryPaint = new Paint();
        primaryPaint.setStyle(Paint.Style.FILL);
        primaryPaint.setColor(Color.parseColor(primaryColorHex));

        Paint secondaryPaint = new Paint();
        secondaryPaint.setStyle(Paint.Style.FILL);
        secondaryPaint.setColor(Color.parseColor(secondaryColorHex));

        Paint linePaint = new Paint();
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(Color.parseColor(lineColorHex));

        Path diamondPath = new Path();
        diamondPath.moveTo(160.0f, 240.0f); // Move to center of screen
        diamondPath.lineTo(140.0f, 200.0f); // Line to left corner
        diamondPath.lineTo(160.0f, 160.0f); // Line to top corner
        diamondPath.lineTo(180.0f, 200.0f); // Line to right corner
        diamondPath.lineTo(160.0f, 240.0f); // Line to bottom corner

        c.drawPath(diamondPath, primaryPaint);
    }
}
