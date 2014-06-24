package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.alex.games.json.JsonRectangleEntity;

public class Block extends RectangleEntity{

	public Block(float x, float y, float width, float height, TextureRegion textureRegion) {
		super(x, y, width, height, 0, .8f, .5f, .5f, textureRegion);
		fixture.setUserData(this);		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JsonRectangleEntity toJsonEntity() {
		JsonRectangleEntity e = super.toJsonEntity();
		e.type = "block";
		return e;
	}
	
}
