package fr.alex.games;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Player {
	private Body body;
	private PolygonShape shape;
	private Vector2 jumpVector;
	private Bow bow;
	
	public Player(float x, float y) {
		super();
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(new Vector2(x, y));
		body = BowGame.world.createBody(bodyDef);
		
		shape = new PolygonShape();
		shape.setAsBox(10 * BowGame.WORLD_TO_BOX, 40 * BowGame.WORLD_TO_BOX);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = .5f;
		fixtureDef.restitution = .2f;
		fixtureDef.friction = .1f;
		Fixture fixture = body.createFixture(fixtureDef);
		fixture.setUserData(this);
		body.setFixedRotation(true);
		jumpVector = new Vector2(0, 15);
		
	}
	
	public void update(float delta){
		bow.setOrigin(body.getWorldCenter());
		body.setLinearVelocity(1, body.getLinearVelocity().y);
		//body.applyLinearImpulse(new Vector2(.01f, 0), body.getWorldCenter(), true);
	}
	
	public void jump(){		
		body.applyForceToCenter(jumpVector, true);
	}
	
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}
	public PolygonShape getShape() {
		return shape;
	}
	public void setShape(PolygonShape shape) {
		this.shape = shape;
	}

	public Bow getBow() {
		return bow;
	}

	public void setBow(Bow bow) {
		this.bow = bow;
	}	
	
	
}
