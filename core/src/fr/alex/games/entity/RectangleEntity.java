package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import fr.alex.games.BowGame;
import fr.alex.games.Utils;
import fr.alex.games.json.JsonRectangleEntity;

public abstract class RectangleEntity extends PhysicEntity {

	protected float width, height, angle;
	protected Fixture fixture;

	public RectangleEntity(float x, float y, float width, float height, float angle, float density, float restitution, float friction, TextureRegion textureRegion) {
		super(x, y, textureRegion);
		this.width = width;
		this.height = height;
		this.angle = angle;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(getBoxWidth(), getBoxHeight());

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = density;
		fixtureDef.restitution = restitution;
		fixtureDef.friction = friction;
		fixture = body.createFixture(fixtureDef);
		body.setTransform(getBoxX() - getBoxWidth() * .5f, getBoxY() - getBoxHeight() * .5f, angle);
	}

	public abstract void update(float delta);

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(textureRegion, getWorldX() - getWorldWidth() * .5f, getWorldY() - getWorldHeight() * .5f, getWorldWidth() * .5f, getWorldHeight() * .5f, getWorldWidth(), getWorldHeight(), 1f, 1f, MathUtils.radiansToDegrees * body.getAngle());
	}

	public void debug(ShapeRenderer debug) {

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

	public JsonRectangleEntity toJsonEntity() {
		JsonRectangleEntity e = new JsonRectangleEntity();
		e.x = Utils.toWorld(body.getPosition().x);
		e.y = Utils.toWorld(body.getPosition().y);				
		e.density = fixture.getDensity();
		e.friction = fixture.getFriction();
		e.resitution = fixture.getRestitution();
		e.width = width;
		e.height = height;
		return e;
	}

}
