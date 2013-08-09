package com.thenewprogramming.java.brickbreaker;

import java.awt.Rectangle;

public class Physics {
	
	public static boolean DoCollide(GameObject GO1, GameObject GO2){
		Rectangle rect1 = new Rectangle((int)GO1.getPosX(), (int)GO1.getPosY(), (int)GO1.getWidth(), (int)GO1.getHeight());
		Rectangle rect2 = new Rectangle((int)GO2.getPosX(), (int)GO2.getPosY(), (int)GO2.getWidth(), (int)GO2.getHeight());
		
		return rect1.intersects(rect2);
		
	}
}
