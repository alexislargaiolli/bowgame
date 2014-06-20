package fr.alex.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Level {
	public Level() {

	}

	public void init() {
		BowGame.world = new World(new Vector2(0, -10), true);

		createWall(0, 50 * BowGame.WORLD_TO_BOX, Gdx.graphics.getWidth() * 20f * BowGame.WORLD_TO_BOX, 5 * BowGame.WORLD_TO_BOX);
		createWall(0, Gdx.graphics.getHeight() * BowGame.WORLD_TO_BOX, Gdx.graphics.getWidth() * 20f * BowGame.WORLD_TO_BOX, 5 * BowGame.WORLD_TO_BOX);
		
		createPyramid(300, 100);

		float x = 100;
		for (int i = 0; i < 30; ++i) {
			x += MathUtils.random(100, 400);
			float y = 100;
			BowGame.blockManager.createSmallBlock(x, y);
			BowGame.blockManager.createSmallBlock(x, y + 40);
			BowGame.blockManager.createSmallBlock(x, y + 80);
			BowGame.blockManager.createSmallBlock(x, y + 120);
		}
	}

	private void createPyramid(float x, float baseWidth) {
		/*
		 * float curY = 5, curWidth = baseWidth, height = 10; while (curWidth >
		 * baseWidth * .2f) { BowGame.blockManager.createBlock(x, curY, curWidth
		 * * BowGame.WORLD_TO_BOX, height * BowGame.WORLD_TO_BOX);
		 * 
		 * curY += 40; curWidth -= baseWidth * .2f; }
		 */
	}

	private void createWall(float x, float y, float width, float height) {
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(x, y));
		Body groundBody = BowGame.world.createBody(groundBodyDef);
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(width, height);
		groundBody.createFixture(groundBox, 0.0f);
	}
}
