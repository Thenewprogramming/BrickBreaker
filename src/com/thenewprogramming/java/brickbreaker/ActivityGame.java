package com.thenewprogramming.java.brickbreaker;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class ActivityGame implements Activity{
	
	private static boolean isGameRunning = false;
	private static boolean hasGameStarted = false;
	
	private static ArrayList<GameObject> GameObjects;
	
	private static GOPlayer player;
	private static GOBall ball;
	
	private static int Points = 0;
	
	private int currentLevel = 1;
	
	private Texture texture;
	
	public ActivityGame(){
		loadLevel(1);
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/Ball.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void processInput(){
		if(!Keyboard.isCreated()){	try{Keyboard.create();} catch (LWJGLException e) {e.printStackTrace();}	}
		
		while(Keyboard.next()){
			if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && isGameRunning && hasGameStarted){
				setGamePaused(true);
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && !isGameRunning && hasGameStarted){
				setGamePaused(false);
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_SPACE && Keyboard.getEventKeyState() == true && !hasGameStarted){
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
		
		
		glPushMatrix();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		texture.bind(); // or GL11.glBind(texture.getTextureID());
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(100,100);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(100+texture.getTextureWidth(),100);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(100+texture.getTextureWidth(),100+texture.getTextureHeight());
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(100,100+texture.getTextureHeight());
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		glPopMatrix();
		
		
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
		if(paused){
			isGameRunning = false;
		}
		else if(!paused){
			isGameRunning = true;
		}
	}
	
	public static GameObject GetGameObjectByID(int ObjectID){
		return GameObjects.get(ObjectID);
	}
	
	public static void givePoints(int howManyPoints){
		Points += howManyPoints;
	}

	public static boolean isGamePaused(){
		if(isGameRunning){
			return false;
		}
		else{
			return true;
		}
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
		try{
			
			//DataInputStream inputStream = new DataInputStream(getClass().getResourceAsStream("/levels/level_"+level+".level"));
			BufferedReader levelReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/levels/level_"+level+".level")));
			
			String currentLine;
			while((currentLine = levelReader.readLine()) != null){
				int numberOfCharsChecked = 0;
				int firstBorder = 100;
				int secondBorder = 100;
				
				while(numberOfCharsChecked < currentLine.length()){
					if(currentLine.charAt(numberOfCharsChecked) == ';' && secondBorder == 100){
						if(firstBorder == 100){
							firstBorder = numberOfCharsChecked;
						}
						else{
							secondBorder = numberOfCharsChecked;
						}
					}
					numberOfCharsChecked++;
				}
				
				if(firstBorder != 100 && secondBorder != 100){
					String firstPart = currentLine.substring(0, firstBorder);
					String secondPart = currentLine.substring(firstBorder+1, secondBorder);
					String thirdPart = currentLine.substring(secondBorder+1, currentLine.length());
					System.out.println(secondBorder);
					
					GOBrick tempBrick = new GOBrick(Float.parseFloat(firstPart), Float.parseFloat(secondPart), Integer.parseInt(thirdPart), 1);
					GameObjects.add(tempBrick);
					
				}
				else{
					//TODO when the screen is setup make the error message appear on the screen instead of in the console
					System.out.println("Error when reading level data at level: " + level + ". Syntax not correct.");
				}
				
				numberOfCharsChecked = 0;
				firstBorder = 100;
				secondBorder = 100;
				
			}
			
			
			levelReader.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
