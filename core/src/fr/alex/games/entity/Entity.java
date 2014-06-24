package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fr.alex.games.json.JsonEntity;

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
	
	public abstract JsonEntity toJsonEntity();
}
