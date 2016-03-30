package com.example.vivian.fadingshapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;

import android.util.DisplayMetrics;
import java.util.Random;
/**
 * Created by Vivian on 3/21/16.
 */
public class Circle extends Shape {
    Paint paint = new Paint();
    float alphaShape;

    // Gets device screen width and height
    DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
    final int width = metrics.widthPixels;
    final int height = metrics.heightPixels;

    Random r = new Random();
    int x = r.nextInt(width - 250) + 80;
    int y = r.nextInt(height - 350) + 80;
    int radius = r.nextInt(width/7) + 25;

    public Circle (Context context) {
        super(context);
    }

    public String getShapeType() {
        return "circle";
    }

    @Override
    public void onDraw(Canvas canvas) {

        alphaShape = super.getShapeAlpha();
        setAlpha(alphaShape);

        int red = r.nextInt(256);
        int blue = r.nextInt(256);
        int green = r.nextInt(256);
        int currentColor =  Color.rgb(red, green, blue);
        paint.setColor(currentColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, radius, paint);
    }

}