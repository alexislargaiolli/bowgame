package fr.alex.games;

import com.badlogic.gdx.math.Vector2;

public class Bow {
	private Vector2 origin;

	public Bow(Vector2 origin) {
		super();
		this.origin = origin;
	}
	
	public void fire(Vector2 dest){
		Arrow a = new Arrow(origin, dest, 5);
	}
	
}
