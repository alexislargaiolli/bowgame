package fr.alex.games;

import com.badlogic.gdx.math.Vector2;

public class Bow {
	private Vector2 origin;

	public Bow(Vector2 origin) {
		super();
		this.origin = origin;
	}
	
	public void fire(Vector2 direction){
		float speed = 5;		
		Arrow arrow = BowGame.arrowManager.create(origin, direction);
		arrow.getBody().applyForceToCenter(new Vector2(direction.x, direction.y).scl(speed), true);
	}

	public void setOrigin(float x, float y) {
		this.origin.x = x;
		this.origin.y = y;
	}
	
	public float getWorldOriginX(){
		return this.origin.x;
	}
	
	public float getWorldOriginY(){
		return this.origin.y;
	}
	
	public float getBoxOriginX(){
		return Utils.toBox(this.origin.x);
	}
	
	public float getBoxOriginY(){
		return Utils.toBox(this.origin.y);
	}
	
}
