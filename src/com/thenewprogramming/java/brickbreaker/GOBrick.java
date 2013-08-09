package com.thenewprogramming.java.brickbreaker;

public class GOBrick extends GameObject {

	private int state;
	
	private int BallID;
	
	@Override
	void update() {
		if(Physics.DoCollide(this, ActivityGame.GetGameObjectByID(BallID))){
			if(state == 1){
				//TODO Make a score system and give points here.
				state = 0;
				ShouldBeRendered = false;
			}
			else if(state == 2){
				state = 1;
				this.color = new float[]{0,1,0};
			}
			else if(state == 3){
				state = 2;
				this.color = new float[]{0,0,1};
			}
		}
	}
	
	public GOBrick(float posx, float posy, int state, int BallID){
		this.width = 20;
		this.height = 10;
		this.shape = SHAPE_RECTANGLE;
		this.state = state;
		if(state == 1){
			this.color = new float[]{0,1,0};
		}
		else if(state == 2){
			this.color = new float[]{0,0,1};
		}
		else if(state == 3){
			this.color = new float[]{1,0,0};
		}
		
		this.posx = posx;
		this.posy = posy;
		this.BallID = BallID;
		
	}

}
