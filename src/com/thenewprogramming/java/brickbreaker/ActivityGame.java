package com.thenewprogramming.java.brickbreaker;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

import java.io.File;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class ActivityGame implements Activity{
	
	private static boolean isGameRunning = false;
	private boolean hasGameStarted = false;
	
	private static ArrayList<GameObject> GameObjects;
	
	private static GOPlayer player;
	private static GOBall ball;
	
	private static int Points = 0;
	
	private int currentLevel = 1;
	
	public ActivityGame(){
		loadLevel(1);
		
	}
	
	@Override
	public void processInput(){
		if(!Keyboard.isCreated()){	try{Keyboard.create();} catch (LWJGLException e) {e.printStackTrace();}	}
		
		while(Keyboard.next()){
			if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && isGameRunning){
				setGamePaused(true);
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && !isGameRunning && hasGameStarted){
				setGamePaused(false);
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_SPACE){
				startGame();
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) && isGameRunning){
			player.move(-1);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && isGameRunning){
			player.move(1);
		}
	}
	
	@Override
	public void update(){
		for(GameObject object : GameObjects){
			object.update();
		}
		
	}
	
	@Override
	public void render(){
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		for(GameObject object : GameObjects){
			object.render();
		}
		Display.update();
	}
	
	@Override
	public void cleanUp() {
		// TODO Make all the variables go back to their initialization values.
		
	}
	
	private void startGame(){
		hasGameStarted = true;
		isGameRunning = true;
	}
	
	private void setGamePaused(boolean paused){
		
	}
	
	public static GameObject GetGameObjectByID(int ObjectID){
		return GameObjects.get(ObjectID);
	}
	
	public static void givePoints(int howManyPoints){
		Points += howManyPoints;
	}

	public static boolean isGamePaused() {
		return isGameRunning;
	}
	
	public static int getPoints(){
		return Points;
	}

	private void loadLevel(int level){
		GameObjects = new ArrayList<GameObject>();
		
		player = new GOPlayer(Display.getWidth() / 2 - 50, 30, 1);
		GameObjects.add(player);
		
		ball = new GOBall(Display.getWidth() / 2 - 20, 45, 0f, -0.1f, true);
		GameObjects.add(ball);
		
		//TODO make a standard for level files and read the correct one here
		File levelFile = new File("");
		
		String test = "dwda";
		int numberOfCharsChecked = 0;
		while(numberOfCharsChecked < test.length()){
			test.charAt(numberOfCharsChecked);
		}
		
		
	}
}
