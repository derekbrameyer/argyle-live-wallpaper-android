package com.doomonafireballstudios.argylelivewallpaper;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: derek
 * Date: 12/13/11
 * Time: 4:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArgylePattern extends View {
    private String primaryColorHex;
    private String secondaryColorHex;
    private String bgColorHex;
    private String lineColorHex;
    private float lineStrokeWidth;
    private int vertDiamonds;
    private int horizDiamonds;
    private float totalWidth;
    private float totalHeight;
    private boolean startLineInCorner;
    private boolean saveToDisk;
    private boolean setAsWallpaper;
    private Context mContext;

    public ArgylePattern(Context context, String primaryColorHex,
                         String secondaryColorHex, String bgColorHex,
                         String lineColorHex, float lineStrokeWidth,
                         int horizDiamonds, int vertDiamonds,
                         float totalWidth, float totalHeight,
                         boolean startLineInCorner, boolean saveToDisk,
                         boolean setAsWallpaper, Context mContext) {
        super(context);

        this.primaryColorHex = primaryColorHex;
        this.secondaryColorHex = secondaryColorHex;
        this.bgColorHex = bgColorHex;
        this.lineColorHex = lineColorHex;
        this.lineStrokeWidth = lineStrokeWidth;
        this.vertDiamonds = vertDiamonds;
        this.horizDiamonds = horizDiamonds;
        this.totalWidth = totalWidth;
        this.totalHeight = totalHeight;
        this.startLineInCorner = startLineInCorner;
        this.saveToDisk = saveToDisk;
        this.setAsWallpaper = setAsWallpaper;
        this.mContext = mContext;

        if (saveToDisk || setAsWallpaper) {
            setDrawingCacheEnabled(true);
        }

        if (setAsWallpaper) {
            setDrawingCacheEnabled(true);
            this.totalWidth = this.totalWidth * 2.0f;
            this.totalHeight = this.totalHeight * 2.0f;
            this.horizDiamonds = this.horizDiamonds * 2;
            this.vertDiamonds = this.vertDiamonds * 2;
        }
        Log.d("ArgyleLiveWallpaper", "totalWidth: " + this.totalWidth);
        Log.d("ArgyleLiveWallpaper", "totalHeight: " + this.totalHeight);
    }

    private int getColorIntFromString(String s) {
        return Integer.parseInt(s, 16) + 0xFF000000;
    }

    private void drawDiamond(Canvas c, float startX, float startY,
                             float halfWidth, float halfHeight, Paint p) {
        float currX = startX;
        float currY = startY;

        Path path = new Path();
        path.moveTo(currX, currY);
        currX += halfWidth;
        currY += halfHeight;
        path.lineTo(currX, currY);
        currX += halfWidth;
        currY -= halfHeight;
        path.lineTo(currX, currY);
        currX -= halfWidth;
        currY -= halfHeight;
        path.lineTo(currX, currY);
        currX -= halfWidth;
        currY += halfHeight;
        path.lineTo(currX, currY);

        c.drawPath(path, p);
    }

    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);

        Paint bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(getColorIntFromString(bgColorHex));
        c.drawPaint(bgPaint);

        Paint primaryPaint = new Paint();
        primaryPaint.setStyle(Paint.Style.FILL);
        primaryPaint.setAntiAlias(true);
        primaryPaint.setColor(getColorIntFromString(primaryColorHex));

        Paint secondaryPaint = new Paint();
        secondaryPaint.setStyle(Paint.Style.FILL);
        secondaryPaint.setAntiAlias(true);
        secondaryPaint.setColor(getColorIntFromString(secondaryColorHex));

        Paint linePaint = new Paint();
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(lineStrokeWidth);
        linePaint.setColor(getColorIntFromString(lineColorHex));

        float diamondWidth = totalWidth / ((float) horizDiamonds);
        float diamondHeight = totalHeight / ((float) vertDiamonds);
        float diamondHalfWidth = (float) diamondWidth / 2.0f;
        float diamondHalfHeight = (float) diamondHeight / 2.0f;

        float primX = 0.0f;
        float primY = 0.0f;
        float primYReset = 0.0f;
        float secX = diamondWidth;
        float secY = 0.0f;
        float secYReset = 0.0f;
        float lineX = 0.0f;
        float lineY = diamondHalfHeight;
        float lineYReset = diamondHalfHeight;
        int horizLineDiamonds = horizDiamonds;
        int vertLineDiamonds = vertDiamonds;
        if (startLineInCorner) {
            primY = diamondHalfHeight;
            primYReset = diamondHalfHeight;
            secY = diamondHalfHeight;
            secYReset = diamondHalfHeight;
            lineY = 0.0f;
            lineYReset = 0.0f;
            horizLineDiamonds += 1;
            vertLineDiamonds += 1;
        } else {
            horizDiamonds++;
            vertDiamonds++;
        }
        // draw primary diamonds
        for (int i = 0; i < horizDiamonds; i += 2) {
            for (int j = 0; j < vertDiamonds; j++) {
                drawDiamond(c, primX, primY, diamondHalfWidth, diamondHalfHeight, primaryPaint);
                primY += diamondHeight;
            }
            primX += (2 * diamondWidth);
            primY = primYReset;
        }

        // draw secondary diamonds
        for (int i = 1; i < horizDiamonds; i += 2) {
            for (int j = 0; j < vertDiamonds; j++) {
                drawDiamond(c, secX, secY, diamondHalfWidth, diamondHalfHeight, secondaryPaint);
                secY += diamondHeight;
            }
            secX += (2 * diamondWidth);
            secY = secYReset;
        }

        // draw lines
        for (int i = 0; i < horizLineDiamonds; i++) {
            for (int j = 0; j < vertLineDiamonds; j++) {
                drawDiamond(c, lineX, lineY, diamondHalfWidth, diamondHalfHeight, linePaint);
                lineY += diamondHeight;
            }
            lineX += diamondWidth;
            lineY = lineYReset;
        }
        if (saveToDisk) {
            try {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File argyleDir = new File(Environment.getExternalStorageDirectory() + "/argyle/");
                    argyleDir.mkdirs();
                    int numFiles = argyleDir.list().length;
                    File outputFile = new File(argyleDir, "argyle" + Integer.toString(numFiles) + ".png");
                    Bitmap bm = Bitmap.createBitmap(getDrawingCache());
                    bm.compress(Bitmap.CompressFormat.PNG, 100,
                        new FileOutputStream(outputFile));
                    /*getDrawingCache().compress(Bitmap.CompressFormat.PNG, 100,
                        new FileOutputStream(outputFile));*/
                    Toast.makeText(mContext, "Saved image to " + outputFile.getPath() + "!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "There was an error saving, please make sure your external storage is mounted.", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Log.d("ArgyleLiveWallpaper", e.toString());
                Toast.makeText(mContext, "There was an error saving, please make sure your external storage is mounted.", Toast.LENGTH_LONG).show();
            }
        }
        if (setAsWallpaper) {
            try {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                    File argyleDir = new File(Environment.getExternalStorageDirectory() + "/argyle/");
                    argyleDir.mkdirs();
                    int numFiles = argyleDir.list().length;
                    File outputFile = new File(argyleDir, "argyle" + Integer.toString(numFiles) + ".png");
                    Bitmap bm = Bitmap.createBitmap(getDrawingCache());
                    bm.compress(Bitmap.CompressFormat.PNG, 100,
                        new FileOutputStream(outputFile));
                    /*getDrawingCache().compress(Bitmap.CompressFormat.PNG, 100,
                        new FileOutputStream(outputFile));*/
                    WallpaperManager wallpaperManager = WallpaperManager.getInstance(mContext);
                    //wallpaperManager.setStream(new FileInputStream(outputFile));
                    wallpaperManager.setBitmap(bm);
                    Toast.makeText(mContext, "Your wallpaper has been set!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "There was an error saving, please make sure your external storage is mounted.", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Log.d("ArgyleLiveWallpaper", e.toString());
                Toast.makeText(mContext, "There was an error setting this as your live wallpaper.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
