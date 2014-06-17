package fr.alex.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Level {
	public Level(){
		Game.world = new World(new Vector2(0, -10), true);
		
		createWall(0, 0, Gdx.graphics.getWidth() * 10f * GameScreen.WORLD_TO_BOX, 5 * GameScreen.WORLD_TO_BOX);
		
		
		/*BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(300 * GameScreen.WORLD_TO_BOX, 100 * GameScreen.WORLD_TO_BOX);
		Body body = Game.world.createBody(bodyDef);
		
		
		CircleShape dynamicCircle = new CircleShape();
		dynamicCircle.setRadius(10 * GameScreen.WORLD_TO_BOX);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicCircle;
		fixtureDef.density = 1f;
		fixtureDef.friction = .5f;
		fixtureDef.restitution = .5f;
		
		body.createFixture(fixtureDef);*/
		
		createPyramid(300, 100);
		
		float x = 600;
		for(int i =0; i< 30; ++i){
			x += MathUtils.random(200, 400);
			float width = MathUtils.random(5, 20);
			float height = MathUtils.random(40, 100);
			float y = height * .5f;
			createObstacle(x, y, width, height);
		}
	}
	
	private void createObstacle(float x, float y, float width, float height){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x * GameScreen.WORLD_TO_BOX, y * GameScreen.WORLD_TO_BOX);
		Body body = Game.world.createBody(bodyDef);
		
		
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(width * GameScreen.WORLD_TO_BOX, height * GameScreen.WORLD_TO_BOX);		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = polygonShape;
		fixtureDef.density = 1f;
		fixtureDef.friction = .5f;
		fixtureDef.restitution = .5f;
		
		body.createFixture(fixtureDef);
	}
	
	private void createPyramid(float x, float baseWidth){
		float curY = 5, curWidth = baseWidth, height = 10;
		while(curWidth > baseWidth * .2f){			
			createObstacle(x, curY, curWidth, height);
			
			curY += 40;
			curWidth -= baseWidth * .2f;
		}
	}
	
	
	private void createWall(float x, float y, float width, float height){
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(x, y));
		Body groundBody = Game.world.createBody(groundBodyDef);
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(width, height);
		groundBody.createFixture(groundBox, 0.0f);
	}
}
