package com.thenewprogramming.java.brickbreaker;

import java.awt.Rectangle;

public class Physics {
	
	public static boolean doCollide(GameObject GO1, GameObject GO2){
		Rectangle rect1 = new Rectangle((int)GO1.getPosX(), (int)GO1.getPosY(), (int)GO1.getWidth(), (int)GO1.getHeight());
		Rectangle rect2 = new Rectangle((int)GO2.getPosX(), (int)GO2.getPosY(), (int)GO2.getWidth(), (int)GO2.getHeight());
		
		return rect1.intersects(rect2);
		
	}
	/**
	 * @param GO1
	 * @param GO2
	 * @return The number that indicates on which face the 2 objects collide. 0 means they don't collide, 1 is up, 2 is right, 3 is under, 4 is left.
	 */	
	
	public static int getCollisionFace(GameObject GO1, GameObject GO2){
		if(doCollide(GO1, GO2)){
			Rectangle rect1 = new Rectangle((int)GO1.getPosX(), (int)GO1.getPosY(), (int)GO1.getWidth(), (int)GO1.getHeight());
		}
		return 0;
	}
}
