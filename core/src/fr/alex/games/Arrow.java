package fr.alex.games;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.alex.games.entity.RectangleEntity;

public class Arrow extends RectangleEntity{
	
	float timeBeforeDeath = -1;
	
	public Arrow(float x, float y, float angle, TextureRegion textureRegion){
		super(x, y, 20, 4, angle, 1, .5f, .1f, textureRegion);
		fixture.setUserData(this);
	}
	
	public void update(float delta){
		if(timeBeforeDeath > -1){
			timeBeforeDeath -= delta;
			if(timeBeforeDeath < 0){
				setDead(true);
			}
		}
	}

	public void setTimeBeforeDeath(float timeBeforeDeath) {
		if(this.timeBeforeDeath == -1){
			this.timeBeforeDeath = timeBeforeDeath;
		}
	}
	
}
