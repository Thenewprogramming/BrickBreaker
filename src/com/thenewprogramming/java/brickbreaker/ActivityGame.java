package com.thenewprogramming.java.brickbreaker;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;



@SuppressWarnings("unused")
public class ActivityGame implements Activity{
	
	private static boolean isGameRunning = false;
	private static boolean hasGameStarted = false;
	
	private static ArrayList<GameObject> GameObjects;
	
	private static GOPlayer player;
	private static GOBall ball;
	
	private static int Points = 0;

	private int currentLevel = 1;
	
	private ArrayList<KeyEvent> UnprocessedKeyEvents;
	
	private float WindowWidth;
	private float WindowHeight;
	
		
	public ActivityGame(float windowWidth, float windowHeight){
		UnprocessedKeyEvents = new ArrayList<KeyEvent>();
		
		loadLevel(1);
		
		WindowWidth = windowWidth;
		WindowHeight = windowHeight;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		//UnprocessedKeyEvents.add(e);
		
		if(e.getKeyCode() == KeyEvent.VK_W){
			//GO FORWARD
			System.out.println("W pressed!");
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			//GO BACK
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			//GO LEFT
			player.move(-1);
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			//GO RIGHT
			player.move(1);
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			Main.closeProgram();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void processInput(){
		//Not used at the moment....
	}
	
	@Override
	public void update(){
			
		for(GameObject object : GameObjects){
			object.update();
		}
		
	}
	
	@Override
	public void render(){
		
		
		for(GameObject object : GameObjects){
			object.render();
		}

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
		
		player = new GOPlayer(WindowWidth / 2 - 50, 30, 1);
		GameObjects.add(player);
		
		ball = new GOBall(WindowWidth / 2 - 20, 45, 0f, -0.1f, true);
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
