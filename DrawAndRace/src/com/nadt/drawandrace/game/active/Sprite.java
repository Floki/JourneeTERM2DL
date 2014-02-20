package com.nadt.drawandrace.game.active;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;

 

public class Sprite extends ShapeDrawable{

	private float pX;
	private float pY;
	private float pWidth;
	private float pHeight;
	private float pAngle;
	
	public Sprite(float pX, float pY, float pWidth, float pHeight, float pAngle) {
		this.pX = pX;
		this.pY = pY;
		this.pWidth = pWidth;
		this.pHeight = pHeight;
		this.pAngle = pAngle;
	}
	
	public void setPosition(float pX, float pY) {
		this.pX = pX;
		this.pY = pY;
	}
	
	public void setSize(float pWidth, float pHeight) {
		this.pHeight = pHeight;
		this.pWidth = pWidth;
	}
	
	public void setAngle(float pAngle) {
		this.pAngle = pAngle;
	}
	
	public float getX() {
		return this.pX;
	}

	public float getY() {
		return this.pY;
	}
	
	public float getWidth() {
		return this.pWidth;
	}
	
	public float getHeight() {
		return this.pHeight;
	}
	
	public void draw(Canvas canvas, Paint paint) {
		canvas.save();
		canvas.rotate(pAngle, pX, pY);
		RectF car = new RectF(pX - pWidth / 2, pY - pHeight / 2, pX + pWidth / 2, pY + pHeight / 2);
		RectF glass = new RectF(pX - pWidth / 4, pY - pHeight / 4, pX + pWidth / 4, pY - pHeight);
		paint.setColor(Color.WHITE);
		canvas.drawRect(car, paint);
		paint.setColor(Color.DKGRAY);
		canvas.drawRect(glass, paint);
		canvas.restore();
	}

}
