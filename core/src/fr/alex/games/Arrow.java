package fr.alex.games;

import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.alex.games.entity.RectangleEntity;

public class Arrow extends RectangleEntity{
	
	float timeBeforeDeath = -1;
	PooledEffect effect;
	
	public Arrow(float x, float y, float angle, TextureRegion textureRegion, PooledEffect effect){
		super(x, y, 30, 12, angle, 2f, .8f, .5f, textureRegion);
		fixture.setUserData(this);
		this.effect = effect;
	}
	
	public void update(float delta){
		if(timeBeforeDeath > -1){
			timeBeforeDeath -= delta;
			if(timeBeforeDeath < 0){
				setDead(true);
			}
		}
		if(effect != null){
			effect.setPosition(getWorldX(), getWorldY());
		}
	}

	public PooledEffect getEffect() {
		return effect;
	}

	public void setEffect(PooledEffect effect) {
		this.effect = effect;
	}

	public void setTimeBeforeDeath(float timeBeforeDeath) {
		if(this.timeBeforeDeath == -1){
			this.timeBeforeDeath = timeBeforeDeath;
		}
	}
	
}
