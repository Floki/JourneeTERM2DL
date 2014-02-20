package com.nadt.drawandrace.game.active;

import java.util.ArrayList;

import com.nadt.drawandrace.utils.Pair;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;

 

public class Sprite {

	private float pX;
	private float pY;
	private float pWidth;
	private float pHeight;
	private float pAngle;
	private ArrayList<Pair<Path, Integer>> spriteComponents;
	
	public Sprite(float pX, float pY, float pAngle) {
		this.pX = pX;
		this.pY = pY;
		this.pWidth = pWidth;
		this.pHeight = pHeight;
		this.pAngle = pAngle;
		this.spriteComponents = new ArrayList<Pair<Path, Integer>>();
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
	
	public float getAngle() {
		return this.pAngle;
	}
	
	public void addComponentToDraw(Path shape, int color) {
		spriteComponents.add(new Pair<Path, Integer>(shape, color));
	}
	
	public void draw(Canvas canvas, Paint paint) {
		canvas.save();
		canvas.rotate(pAngle, pX, pY);
		for(Pair shape: spriteComponents) {
			paint.setColor((Integer)shape.getSecond());
			canvas.drawPath((Path)shape.getFirst(), paint);
		}		
		canvas.restore();
	}

}
