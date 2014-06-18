package fr.alex.games.entity;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class Entity {
	private Body body;
	private float width;
	private float height;
	private boolean dead;

	public abstract void update(float delta);
	
	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

}
