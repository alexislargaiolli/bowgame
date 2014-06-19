package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import fr.alex.games.BowGame;
import fr.alex.games.Utils;

public abstract class Entity {
	protected Body body;
	protected boolean dead;
	protected TextureRegion textureRegion;
	
	public Entity(float x, float y, TextureRegion textureRegion){
		this.textureRegion = textureRegion;
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(Utils.toBox(x), Utils.toBox(y));
		body = BowGame.world.createBody(bodyDef);
	}

	public abstract void update(float delta);
	
	public abstract void draw(SpriteBatch batch);
	
	public float getWorldX(){
		return Utils.toWorld(body.getPosition().x);
	}
	
	public float getBoxX(){
		return body.getPosition().x;
	}
	
	public float getWorldY(){
		return Utils.toWorld(body.getPosition().y);				
	}
	
	public float getBoxY(){
		return body.getPosition().y;
	}
	
	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

}
