package fr.alex.games;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import fr.alex.games.entity.RectangleEntity;

public class Player extends RectangleEntity {
	private Vector2 jumpVector;
	private Bow bow;

	public Player(float x, float y, TextureRegion region) {
		super(x, y, 40, 40, 0, .5f, .2f, .1f, region);
		body.setUserData(this);
		body.setFixedRotation(true);
		jumpVector = new Vector2(0, 15);

	}

	public void update(float delta) {
		bow.setOrigin(getWorldX() + getWorldWidth(), getWorldY() + getWorldHeight() * .5f);
		body.setLinearVelocity(1, body.getLinearVelocity().y);
		// body.applyLinearImpulse(new Vector2(.01f, 0), body.getWorldCenter(),
		// true);
	}

	public void jump() {
		body.applyForceToCenter(jumpVector, true);	
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public Bow getBow() {
		return bow;
	}

	public void setBow(Bow bow) {
		this.bow = bow;
	}

}
