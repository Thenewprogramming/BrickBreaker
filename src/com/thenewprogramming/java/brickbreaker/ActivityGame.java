package com.thenewprogramming.java.brickbreaker;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class ActivityGame implements Activity{
	
	private static ArrayList<GameObject> GameObjects;
	
	private static GOPlayer player;
	private static GOBall ball;
	
	public ActivityGame(){
		GameObjects = new ArrayList<GameObject>();
		
		player = new GOPlayer(Display.getWidth() / 2 - 50, 30, 1);
		GameObjects.add(player);
		
		ball = new GOBall(Display.getWidth() / 2 - 20, 45, 0f, -0.1f, true);
		GameObjects.add(ball);
		
		
	}
	
	public void processInput(){
		if(!Keyboard.isCreated()){	try{Keyboard.create();} catch (LWJGLException e) {e.printStackTrace();}	}
		
		while(Keyboard.next()){
			if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE){
				Display.destroy();
				System.exit(0);
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_SPACE){
				ball.setFreezed(false);
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			player.move(-1);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			player.move(1);
		}
	}
	
	public void update(){
		for(GameObject object : GameObjects){
			object.update();
		}
	}
	
	public void render(){
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		for(GameObject object : GameObjects){
			object.render();
		}
		Display.update();
	}
	
	public static GameObject GetGameObjectByID(int ObjectID){
		return GameObjects.get(ObjectID);
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}
}
