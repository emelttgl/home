package com.example.home3d.outils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawRectangle extends View {
    protected Paint paint;
    protected int x,y,x1,y1;


    protected Canvas canvas;

    public DrawRectangle(Context context, Paint p, Canvas c, int x, int y, int x1, int y1) {
        super(context);
        this.paint =p;
        this.canvas = c;
        this.x=x;
        this.y=y;
        this.x1=x1;
        this.y1=y1;

    }

    @Override
    protected void onDraw(Canvas canvas){

        //paint.setAlpha(50);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setColor(Color.BLACK);
        canvas.drawRect(x,y,x1,y1, paint);
    }
}
