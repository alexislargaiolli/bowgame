package fr.alex.games;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Arrow {
	
	private Body body;
	private PolygonShape shape;
	
	public Arrow(Vector2 origin, Vector2 dest, float speed){
		
		Vector2 direction = new Vector2(dest).sub(origin).nor();
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(origin);
		body = Game.world.createBody(bodyDef);
		
		shape = new PolygonShape();
		shape.setAsBox(10  * GameScreen.WORLD_TO_BOX, 2 * GameScreen.WORLD_TO_BOX, new Vector2(5 * GameScreen.WORLD_TO_BOX, 1 * GameScreen.WORLD_TO_BOX), direction.angleRad());
		/*float[] vertices = new float[]{0, 1, 0, 3, 8, 3, 10, 4, 10, 0, 8, 1};
		for(int i=0; i< vertices.length; ++i){
			vertices[i] *= GameScreen.WORLD_TO_BOX;					
		}
		shape.set(vertices);*/
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.restitution = .5f;
		fixtureDef.friction = .5f;
		Fixture fixture = body.createFixture(fixtureDef);
		fixture.setUserData(this);
		
		//body.setLinearVelocity(new Vector2(1000, 1000));
		
		//body.applyLinearImpulse(new Vector2(direction.x, direction.y).scl(10), body.getWorldCenter(), true);
		body.applyForceToCenter(new Vector2(direction.x, direction.y).scl(speed), true);
	}
	
}
