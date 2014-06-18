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

	public Vector2 getOrigin() {
		return origin;
	}

	public void setOrigin(Vector2 origin) {
		this.origin = origin;
	}
	
}
