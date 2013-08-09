package com.thenewprogramming.java.brickbreaker;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Main {
	
	private static Activity CurrentActivity;
	
	public static final int ACTIVITY_MENU = 0;
	public static final int ACTIVITY_GAME = 1;
	public static final int ACTIVITY_HIGHSCORES = 2;
	public static final int ACTIVITY_ABOUT = 3;
	
	private static boolean closeProgram = false;
	
	public static void main(String[] args) {
		initDisplay();
		initGL();
		
		initActivities();
		
		gameLoop();
		//cleanUp();
	}
	
	private static void initDisplay(){
		try {
			Display.setDisplayMode(new DisplayMode(600, 600));
			Display.create();
			//Display.setTitle("Moving Squares");
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private static void initGL(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
		
		glMatrixMode(GL_MODELVIEW);
		glClearColor(0,0,0,1);
		glDisable(GL_DEPTH_TEST);
	}
	
	private static void initActivities(){		
		CurrentActivity = new ActivityMenu();
	}
	
	private static void processInput(){
		CurrentActivity.processInput();
	}
	
	private static void update(){
		CurrentActivity.update();
	}
	
	private static void render(){
		CurrentActivity.render();
		Display.sync(60);
	}
	
	private static void gameLoop(){
		while(!closeProgram && !Display.isCloseRequested()){
			processInput();
			
			update();
			
			render();
		}
		cleanUp();
		
	}
	
	private static void cleanUp(){
		Display.destroy();
	}
	
	public static void closeProgram(){
		closeProgram = true;
	}
	
	public static void changeActivity(int activity){
		CurrentActivity.cleanUp();
		
		switch(activity){
			case ACTIVITY_MENU:
				CurrentActivity = new ActivityMenu();
			case ACTIVITY_GAME:
				CurrentActivity = new ActivityGame();
			case ACTIVITY_HIGHSCORES:
				CurrentActivity = new ActivityHighscores();
			case ACTIVITY_ABOUT:
				CurrentActivity = new ActivityAbout();
		}
	}
	
}
