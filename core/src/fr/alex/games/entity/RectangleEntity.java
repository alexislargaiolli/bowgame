package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import fr.alex.games.BowGame;

public abstract class RectangleEntity extends Entity {

	protected float width, height;
	protected Fixture fixture;

	public RectangleEntity(float x, float y, float width, float height, float angle, float density, float restitution, float friction, TextureRegion textureRegion) {
		super(x, y, textureRegion);
		this.width = width;
		this.height = height;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(getBoxWidth(), getBoxHeight(), new Vector2(getBoxWidth(), getBoxHeight()), angle);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = density;
		fixtureDef.restitution = restitution;
		fixtureDef.friction = friction;
		fixture = body.createFixture(fixtureDef);
	}

	public abstract void update(float delta);

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(textureRegion, getWorldX() , getWorldY(), 0, 0, getWorldWidth(), getWorldHeight(), 1f, 1f, MathUtils.radiansToDegrees * body.getAngle());
	}

	public float getWorldWidth() {
		return width;
	}

	public float getWorldHeight() {
		return height;
	}

	public float getBoxWidth() {
		return width * BowGame.WORLD_TO_BOX * .5f;
	}

	public float getBoxHeight() {
		return height * BowGame.WORLD_TO_BOX * .5f;
	}

}
