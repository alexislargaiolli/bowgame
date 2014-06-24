package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Coin extends CircleEntity{

	public Coin(float x, float y, TextureRegion textureRegion) {
		super(x, y, 20, 0, 1f, .2f, .2f, false, textureRegion);
		fixture.setUserData(this);
	}

	@Override
	public void update(float delta) {
		
	}

}
