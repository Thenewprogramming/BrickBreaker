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
		glPushMatrix();
		{
			glTranslatef(x, y, 0);
			glRotatef(rot, 0, 0, 1);
			
			glBegin(GL_QUADS);
			{
				glVertex2f(0, 0);
				glVertex2f(0, height);
				glVertex2f(width, height);
				glVertex2f(width, 0);
			}
			glEnd();
		}
		glPopMatrix();
	}
	
	public static void rect(float x, float y, float width, float height, float rot, float[] color){
		glColor3f(color[0], color[1], color[2]);
		rect(x, y, width, height, rot);
		glColor3f(1, 1, 1);
	}
	
	public static void triangle(float x, float y, float width, float height, float rot){
		glPushMatrix();
		{
			glTranslatef(x, y, 0);
			glRotatef(rot, 0, 0, 1);
			
			glBegin(GL_TRIANGLES);
			{
				glVertex2f(width, height); //top right
				glVertex2f(width, 0); //bottom left
				glVertex2f(0, 0); //bottom left
				glVertex2f(0, height); //top left
				
				
			}
			glEnd();
		}
		glPopMatrix();
	}
	
	public static void triangle(float x, float y, float width, float height, float rot, float[] color){
		glColor3f(color[0], color[1], color[2]);
		triangle(x, y, width, height, rot);
		glColor3f(1, 1, 1);
	}
	 
	public static void circle(float posx, float posy, float radius){
		glPushMatrix();
		{
			glTranslatef(posx + radius, posy + radius, 0);
			
			glBegin(GL11.GL_LINE_LOOP);
			{
				for(int i =0; i <= 28800; i++){
					double angle = Math.PI * i / 14400;
					double x = Math.cos(angle) * radius;
					double y = Math.sin(angle)* radius;
					glVertex2d(x,y);
				}
			}
			
			glEnd();
		}
		
		glPopMatrix();
	}
	
	public static void circle(float posx, float posy, float radius, float[] color){
		glColor3f(color[0], color[1], color[2]);
		circle(posx, posy, radius);
		glColor3f(1, 1, 1);
	}
	
	public static void drawrect(int x, int y, int width, int height){
		Graphics g = bs.getDrawGraphics();
        
		g.fillRect(x, y, width, height);
		
		g.dispose();
        bs.show();
	}
}
