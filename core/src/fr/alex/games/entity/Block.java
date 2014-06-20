package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Block extends RectangleEntity{

	public Block(float x, float y, float width, float height, TextureRegion textureRegion) {
		super(x, y, width, height, 0, .8f, .5f, .5f, textureRegion);
		fixture.setUserData(this);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	
}
