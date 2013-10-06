package com.thenewprogramming.java.brickbreaker;

public class GOBall extends GameObject {

	
	private boolean isFreezed = false;
	private float SPEED = 10;
	private float velx = 0;
	private float vely = 1;
	
	@Override
	void update() {
		if(ActivityGame.isGamePaused() && !isFreezed){
			isFreezed = true;
		}
		else if(!ActivityGame.isGamePaused() && isFreezed){
			isFreezed = false;
		}
		
		if(!isFreezed){
			posx += velx * SPEED;
			posy += vely * SPEED;
		}
				
		//TODO make walls as a GameObject.
	}
	
	public GOBall(float posx, float posy, float velx, float vely, boolean isFreezed){
		
		this.width = 40;
		this.height = 40;
		this.shape = SHAPE_CIRCEL;
		
		this.posx = posx;
		this.posy = posy;
		
		this.velx = velx;
		this.vely = vely;
		this.isFreezed = isFreezed;
	}

	public boolean isFreezed() {
		return isFreezed;
	}

	public void setFreezed(boolean isFreezed) {
		this.isFreezed = isFreezed;
	}
	
	public void InvertVelocityX(){
		velx = velx - velx - velx;
		
	}
	
	public void InvertVelocityY(){
		vely = vely - vely - vely;
	}

}
