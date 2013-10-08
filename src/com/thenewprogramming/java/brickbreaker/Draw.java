package com.thenewprogramming.java.brickbreaker;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2d;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import org.lwjgl.opengl.GL11;

public class Draw extends Canvas{
	
	private static final long serialVersionUID = 1L;
	private static BufferStrategy bs;
	
	
	public Draw(){
        createBufferStrategy(3);
        bs = getBufferStrategy();
	}
	
	public static void rect(float x, float y, float width, float height, float rot){
		
	}
	
	public static void rect(float x, float y, float width, float height, float rot, float[] color){
		
	}
	
	public static void triangle(float x, float y, float width, float height, float rot){
		
	}
	
	public static void triangle(float x, float y, float width, float height, float rot, float[] color){
		
	}
	 
	public static void circle(float posx, float posy, float radius){
		
	}
	
	public static void circle(float posx, float posy, float radius, float[] color){
		
	}
	
	public static void drawrect(int x, int y, int width, int height){
		Graphics g = bs.getDrawGraphics();
        
		g.fillRect(x, y, width, height);
		
		g.dispose();
        bs.show();
	}
}
