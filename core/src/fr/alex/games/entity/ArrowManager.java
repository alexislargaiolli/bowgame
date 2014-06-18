package fr.alex.games.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import fr.alex.games.Arrow;
import fr.alex.games.BowGame;

public class ArrowManager extends EntityManager<Arrow> {
	
	public Arrow create(Vector2 origin, Vector2 direction) {
		Arrow arrow = new Arrow();
		

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(origin);
		Body body = BowGame.world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(10 * BowGame.WORLD_TO_BOX, 2 * BowGame.WORLD_TO_BOX, new Vector2(5 * BowGame.WORLD_TO_BOX, 1 * BowGame.WORLD_TO_BOX), direction.angleRad());
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.restitution = .5f;
		fixtureDef.friction = .5f;
		Fixture fixture = body.createFixture(fixtureDef);
		fixture.setUserData(arrow);		
		arrow.setBody(body);
		
		entities.add(arrow);
		return arrow;
	}

	@Override
	protected void updateEntity(float delta, Entity e) {
		e.update(delta);
	}

	@Override
	protected void removedEntity(Entity arrow) {
		BowGame.world.destroyBody(arrow.getBody());
	}

}
