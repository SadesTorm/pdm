package com.sadestorm.pdm;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class Onboarding extends View {

    public Onboarding(Context context) {
        super(context);
    }

    public Onboarding(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public Onboarding(Context context,  AttributeSet attrs, int defStyleAttr) {

        // quandp acrescenta noo xml sozinho

        super(context, attrs, defStyleAttr);
    }

    public Onboarding(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        // quando tem definiçao de cores e atributos e definicao de novos estilos

        super(context, attrs, defStyleAttr, defStyleRes);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // desenha um retangulo no componente minhas funçoes
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        Rect r = new Rect(0, 0, 300,300);
        canvas.drawRect(r,p);

        p.setColor(Color.RED);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        int x = 0;
        int y;

        Rect r2 = new Rect(50,50,200,200);
        while ( x <300) {
            canvas.drawCircle(x, 50, 50f, p);
            canvas.drawRect(r,p);
            canvas.drawCircle(x+1, 50, 50f, p);
        }
    }
}
