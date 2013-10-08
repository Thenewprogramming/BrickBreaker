package com.thenewprogramming.java.brickbreaker;

import java.awt.event.KeyListener;

public interface Activity extends KeyListener{
	public void processInput();
	public void update();
	public void render();
	public void cleanUp();
}
