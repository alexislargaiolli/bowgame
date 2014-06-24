package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.alex.games.json.JsonEntity;

public class SpriteEntity extends Entity {

	protected Sprite sprite;

	public SpriteEntity(float x, float y, TextureRegion textureRegion) {
		sprite = new Sprite(textureRegion);
	}

	@Override
	public void update(float delta) {

	}

	@Override
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	@Override
	public JsonEntity toJsonEntity() {
		// TODO Auto-generated method stub
		return null;
	}

}
