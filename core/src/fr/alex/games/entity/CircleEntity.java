package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Json;

import fr.alex.games.Utils;

public abstract class CircleEntity extends PhysicEntity {

	protected float radius, angle;
	protected Fixture fixture;	

	public CircleEntity(float x, float y, float radius, float angle, float density, float restitution, float friction, boolean sensor, TextureRegion textureRegion) {
		super(x, y, textureRegion);
		this.radius = radius;
		this.angle = angle;
		CircleShape shape = new CircleShape();
		shape.setRadius(getBoxRadius());

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = density;
		fixtureDef.restitution = restitution;
		fixtureDef.friction = friction;
		fixtureDef.isSensor = sensor;
		fixture = body.createFixture(fixtureDef);
		body.setTransform(getBoxX(), getBoxY(), angle);
		
		Json json = new Json();
		System.out.println(json.toJson(body.getPosition()));
	}

	public abstract void update(float delta);

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(textureRegion, getWorldX() - getWorldRadius(), getWorldY() - getWorldRadius(), getWorldRadius(), getWorldRadius(), getWorldRadius() * 2f, getWorldRadius() * 2f, 1f, 1f, MathUtils.radiansToDegrees * body.getAngle());
	}
	
	public void debug(ShapeRenderer debug){
		
	}

	public float getWorldRadius(){
		return radius;
	}
	
	public float getBoxRadius(){
		return Utils.toBox(radius);
	}
	
}
