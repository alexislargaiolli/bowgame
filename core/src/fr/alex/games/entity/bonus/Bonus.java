package fr.alex.games.entity.bonus;

import fr.alex.games.entity.Entity;

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
}
