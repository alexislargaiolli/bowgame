package fr.alex.games.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import fr.alex.games.BowGame;
import fr.alex.games.Utils;

public class BlockManager extends EntityManager<Block> {

	private float smallBockWidth = 10 * BowGame.WORLD_TO_BOX;
	private float smallBockHeight = 10 * BowGame.WORLD_TO_BOX;
	private Texture texture;
	
	public Block createSmallBlock(float x, float y){
		return createBlock(x, y, smallBockWidth, smallBockHeight);
	}
	
	public Block createBlock(float x, float y, float width, float height){
		Block block = new Block();
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x * BowGame.WORLD_TO_BOX, y * BowGame.WORLD_TO_BOX);
		Body body = BowGame.world.createBody(bodyDef);		
		
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(width, height);
		
		block.setWidth(Utils.toWorld(width));
		block.setHeight(Utils.toWorld(height));
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = polygonShape;
		fixtureDef.density = 1f;
		fixtureDef.friction = .5f;
		fixtureDef.restitution = .5f;		
		Fixture fixture = body.createFixture(fixtureDef);
		fixture.setUserData(block);
		
		block.setBody(body);
		entities.add(block);
		
		return block;
	}
	
	@Override
	protected void updateEntity(float delta, Entity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void removedEntity(Entity e) {
		// TODO Auto-generated method stub
		
	}
	
	public void draw(SpriteBatch batch){
		for(int i=0; i< entities.size(); ++i){
			Block e = entities.get(i);
			float x = Utils.toWorld(e.getBody().getPosition().x);
			float y = Utils.toWorld(e.getBody().getPosition().y);
			batch.draw(texture, x, y, e.getWidth(), e.getHeight(), 0, 0, texture.getWidth(), texture.getHeight(), false, false);
		}
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}
