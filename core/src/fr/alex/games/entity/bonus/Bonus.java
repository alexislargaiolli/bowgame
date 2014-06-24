package fr.alex.games.entity.bonus;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fr.alex.games.entity.Entity;
import fr.alex.games.json.JsonEntity;

public abstract class Bonus extends Entity{
	protected float duration;
	protected float elaspedTime;
	
	public Bonus(float duration){
		this.duration = duration;
	}
	
	public abstract void enableEffect();
	
	public abstract void disableEffect();

	@Override
	public void update(float delta) {
		elaspedTime += delta;
		if(elaspedTime > this.duration ){
			dead = true;
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JsonEntity toJsonEntity() {
		// TODO Auto-generated method stub
		return null;
	}
}
