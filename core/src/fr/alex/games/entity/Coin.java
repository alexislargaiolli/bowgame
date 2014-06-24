package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.alex.games.json.JsonCircleEntity;

public class Coin extends CircleEntity{

	public Coin(float x, float y, TextureRegion textureRegion) {
		super(x, y, 20, 0, 1f, .2f, .2f, false, textureRegion);
		fixture.setUserData(this);
	}

	@Override
	public void update(float delta) {
		
	}

	public JsonCircleEntity toJsonEntity() {
		JsonCircleEntity e = super.toJsonEntity();
		e.type = "coin";
		return e;
	}
	
}
