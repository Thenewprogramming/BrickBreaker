package com.thenewprogramming.java.brickbreaker;

public abstract class GameObject {
	protected float posx;
	protected float posy;
	
	protected float width;
	protected float height;
	
	protected int shape;
	public static final int SHAPE_RECTANGLE= 1;
	public static final int SHAPE_TRIANGLE = 2;
	public static final int SHAPE_CIRCEL = 3;
	
	protected float[] color = new float[]{1,1,1};
	
	protected boolean ShouldBeRendered = true;
	
	protected float rotation = 0;
	
	abstract void update();
	
	public void render(){
		if(shape == SHAPE_RECTANGLE){
			Draw.rect(posx, posy, width, height, rotation, color);
		}
		if(shape == SHAPE_TRIANGLE){
			Draw.triangle(posx, posy, width, height, rotation, color);
		}
		if(shape == SHAPE_CIRCEL){
			Draw.circle(posx, posy, width / 2, color);
		}
		
	}
	
	public float getPosX() {
		return posx;
	}

	public float getPosY() {
		return posy;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	public float getRotation(){
		return rotation;
	}

	
}
