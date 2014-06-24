package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {
	protected boolean dead;	
	
	public abstract void update(float delta);
	
	public abstract void draw(SpriteBatch batch);

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
}
