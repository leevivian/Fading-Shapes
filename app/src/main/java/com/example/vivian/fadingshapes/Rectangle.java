package com.example.vivian.fadingshapes;

import android.graphics.Canvas;
import android.content.Context;

import android.graphics.Paint;
import android.graphics.Color;

import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Random;

/**
 * Created by Vivian on 3/21/16.
 */
public class Rectangle extends Shape {

    Rect rectangle;
    Paint paint = new Paint();
    float alphaShape;

    DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
    final int width = metrics.widthPixels;
    final int height = metrics.heightPixels;

    Random r = new Random();
    int xleft = r.nextInt(width - 400) + 25;
    int xright = xleft + r.nextInt(500 - 100) + 30;
    int ytop = r.nextInt(height - 500) + 25;
    int ybottom = ytop + r.nextInt(500 - 200) + 30;


    public Rectangle (Context context) {
        super(context);
    }

    public String getShapeType() {
        return "rectangle";
    }

    public void onDraw(Canvas canvas) {
        rectangle = new Rect(xleft, ytop, xright, ybottom);

        alphaShape = super.getShapeAlpha();
        Log.i("Test getShapeAlpha in R", Float.toString(alphaShape));
        setAlpha(alphaShape);

        int red = r.nextInt(255);
        int blue = r.nextInt(255);
        int green = r.nextInt(255);

        int currentColor =  Color.rgb(red, green, blue);

        paint.setColor(currentColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rectangle, paint);
        Log.i("Rect", "Rectangle finished drawing!");
    }

}
