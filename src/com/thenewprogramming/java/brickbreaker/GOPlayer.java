package com.thenewprogramming.java.brickbreaker;

public class GOPlayer extends GameObject{
	
	public static final float SPEED = 4.5f;
	
	private int BallID;
	
	public GOPlayer(float posx, float posy, int BallID){
		this.width = 100;
		this.height = 15;
		this.shape = SHAPE_RECTANGLE;
		
		this.color = new float[]{0,0,1};
		
		this.posx = posx;
		this.posy = posy;
		this.BallID = BallID;
	}
	@Override
	void update() {
		if(Physics.doCollide(this, ActivityGame.GetGameObjectByID(BallID))){
			((GOBall) ActivityGame.GetGameObjectByID(BallID)).InvertVelocityY();
		}
	}
	public void move(int x) {
		if(posx+SPEED*x >= 0 && posx+SPEED*x <= 500){
			posx += SPEED * x;
		}
	}
	
}
