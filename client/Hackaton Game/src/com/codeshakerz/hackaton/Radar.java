package com.codeshakerz.hackaton;

import com.codeshakerz.hackaton.RestClient.RequestMethod;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

public class Radar {
	private Point	window;
	private Point	center;
	
	private Paint	radarPaint;
	private int		radarRadius;
	
	private Paint	compassPaint;
	private RectF	compassNorth;
	private RectF	compassSouth;
	private float	compassRotation;
	
	private Paint	textPaint;
	
	private Player[] players;
	
	Radar(int x, int y, Player[] players) {
		window = new Point(x,y);
		center = new Point(window.x/2, window.y/2);
		
		radarRadius = Math.min(center.x, center.y) - 30;
        
        radarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        radarPaint.setAntiAlias(true);
        radarPaint.setStyle(Paint.Style.STROKE);
        
        compassPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        compassPaint.setAntiAlias(true);
        compassPaint.setStyle(Paint.Style.FILL);
        
        compassRotation = -45.0f;
        
        compassNorth = new RectF((center.x-5),(center.y-radarRadius-17),(center.x+5),(center.y-radarRadius));
        compassSouth = new RectF((center.x-5),(center.y+radarRadius+17),(center.x+5),(center.y+radarRadius));
        
        compassPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        compassPaint.setAntiAlias(true);
        compassPaint.setStyle(Paint.Style.FILL);
        
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setARGB(255, 255, 255, 255);
        
        this.players = players;
	}
	
	public void Draw(Canvas canvas) {
		
		
		
		canvas.drawColor(Color.BLACK);
		
		compassPaint.setARGB(255, 255, 0, 0);
        canvas.drawRect(compassSouth, compassPaint);
        compassPaint.setARGB(255, 0, 150, 255);
        canvas.drawRect(compassNorth, compassPaint);
		
		radarPaint.setARGB(255, 115, 230, 25);
        radarPaint.setStrokeWidth(1.5f);
        canvas.drawCircle(window.x/2, window.y/2, radarRadius, radarPaint);
        canvas.drawCircle(window.x/2, window.y/2, radarRadius+18, radarPaint);
        canvas.drawCircle(window.x/2, window.y/2, 1, radarPaint);
        //canvas.drawCircle((float)((window.x/2)+(radarRadius+5)*Math.cos(Math.toRadians(720))),(float)((window.y/2)+(radarRadius+5)*Math.sin(Math.toRadians(720))), 5, compassPaint);
        //canvas.drawCircle(compassSouth.right,compassSouth.bottom, 2, compassPaint);
        radarPaint.setStrokeWidth(1.0f);
        radarPaint.setARGB(120, 115, 230, 25);
        for (int i = radarRadius/5, j = 0; j < 4; i += radarRadius/5, j++) {
        	canvas.drawCircle(window.x/2, window.y/2, i, radarPaint);
        }
        canvas.drawLine(window.x/2-radarRadius, window.y/2, window.x/2+radarRadius, window.y/2, radarPaint);
        canvas.drawLine(window.x/2, window.y/2-radarRadius, window.x/2, window.y/2+radarRadius, radarPaint);
        
        textPaint.setTextSize(20);
        canvas.drawText("Liczba graczy w pobli¿u: "+(players.length-1), 120, window.y - 100,textPaint);
        textPaint.setTextSize(15);
        //canvas.drawText("mug3tsu", 165, 100,textPaint);
        //canvas.drawText(response,50, 100,textPaint);
	}
	
};