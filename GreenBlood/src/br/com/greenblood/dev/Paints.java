package br.com.greenblood.dev;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Paints {
    public static final Paint BLACK, BLACK_STROKE, BLUE, WHITE, RED;
    public static final Paint STROKE_BLUE;
    public static final Paint BLANK = new Paint();
    
    static{
        BLACK = new Paint();
        BLACK.setColor(Color.BLACK);
        
        BLACK_STROKE = new Paint();
        BLACK_STROKE.setColor(Color.BLACK);
        BLACK_STROKE.setStyle(Style.STROKE);
        
        BLUE = new Paint();
        BLUE.setColor(Color.BLUE);
        
        WHITE = new Paint();
        WHITE.setColor(Color.WHITE);
        
        RED = new Paint();
        RED.setColor(Color.RED);
        
        STROKE_BLUE = new Paint();
        STROKE_BLUE.setColor(Color.BLUE);
        STROKE_BLUE.setStyle(Style.STROKE);
    }
}
