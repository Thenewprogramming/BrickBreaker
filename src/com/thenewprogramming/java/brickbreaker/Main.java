package com.thenewprogramming.java.brickbreaker;

import javax.swing.JFrame;



public class Main {
	
	private static Activity CurrentActivity;
	
	public static final int ACTIVITY_MENU = 0;
	public static final int ACTIVITY_GAME = 1;
	public static final int ACTIVITY_HIGHSCORES = 2;
	public static final int ACTIVITY_ABOUT = 3;
	
	private static boolean closeProgram = false;
	
	private static JFrame Window;
	
	public static void main(String[] args) {
		initDisplay();
		
		initActivities();
		
		gameLoop();
		
		cleanUp();
	}
	
	private static void initDisplay(){
		Window = new JFrame("Brick Breaker BABY!");
		
		Window.setSize(600, 600);
		Window.setVisible(true);
	}
	
	
	private static void initActivities(){		
		CurrentActivity = new ActivityGame(Window.getWidth(), Window.getWidth());
		Window.addKeyListener(CurrentActivity);
	}
	
	private static void processInput(){
		CurrentActivity.processInput();
	}
	
	private static void update(){
		CurrentActivity.update();
	}
	
	private static void render(){
		CurrentActivity.render();
	}
	
	private static void gameLoop(){
		while(!closeProgram){
			processInput();
			
			update();
			
			render();
		}
		
		
	}
	
	private static void cleanUp(){
		CurrentActivity.cleanUp();
		System.exit(0);
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
				CurrentActivity = new ActivityGame(Window.getWidth(), Window.getWidth());
			case ACTIVITY_HIGHSCORES:
				CurrentActivity = new ActivityHighscores();
			case ACTIVITY_ABOUT:
				CurrentActivity = new ActivityAbout();
		}
	}
	
}
