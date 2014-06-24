package fr.alex.games;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Json;

import fr.alex.games.background.ParallaxBackground;
import fr.alex.games.background.ParallaxLayer;
import fr.alex.games.entity.bonus.BonusType;
import fr.alex.games.json.JsonLevel;
import fr.alex.games.json.JsonRectangleEntity;

public class Level {
	HashMap<String, TextureRegion> textures;
	ParallaxBackground background;
	private float width;
	
	public Level() {
		textures = new HashMap<String, TextureRegion>();
		textures.put("block2.png", null);
		textures.put("bonus.png", null);
		textures.put("coin.png", null);
		width = Gdx.graphics.getWidth() * 2f;
	}	

	public void loadRessources(){
		for(String texture : textures.keySet()){
			GM.assetManager.load(texture, Texture.class);
		}
		GM.assetManager.load("bg1.png", Texture.class);
		GM.assetManager.load("bg2.png", Texture.class);
		GM.assetManager.load("bg3.png", Texture.class);
		GM.assetManager.load("bg4.png", Texture.class);
	}
	
	public void dispose(){
		for(String texture : textures.keySet()){
			textures.get(texture).getTexture().dispose();
		}
	}
	
	public void init() {
		GM.world = new World(new Vector2(0, -10), true);
		GM.world.setContactListener(new GameCollisions());
		
		TextureRegion region1 = new TextureRegion(GM.assetManager.get("bg1.png", Texture.class));
		TextureRegion region2 = new TextureRegion(GM.assetManager.get("bg2.png", Texture.class));
		TextureRegion region3 = new TextureRegion(GM.assetManager.get("bg3.png", Texture.class));
		TextureRegion region4 = new TextureRegion(GM.assetManager.get("bg4.png", Texture.class));
		
		background = new ParallaxBackground(new ParallaxLayer[] { new ParallaxLayer(region1, new Vector2(), new Vector2()), new ParallaxLayer(region2, new Vector2(0.1f, 0f), new Vector2()), new ParallaxLayer(region3, new Vector2(0.5f, 0), new Vector2()), new ParallaxLayer(region4, new Vector2(1f, 0), new Vector2()) }, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new Vector2(150, 0));			
		
		for(String texture : textures.keySet()){
			TextureRegion region = new TextureRegion(GM.assetManager.get(texture, Texture.class));
			textures.put(texture, region);
		}
		
		createWall(0, 50 * BowGame.WORLD_TO_BOX, width * BowGame.WORLD_TO_BOX, 5 * BowGame.WORLD_TO_BOX);
		createWall(0, Gdx.graphics.getHeight() * BowGame.WORLD_TO_BOX, width * BowGame.WORLD_TO_BOX, 5 * BowGame.WORLD_TO_BOX);
		
		createPyramid(300, 100);
		
		GM.bonusEntityManager.create(500, 300, textures.get("bonus.png"), BonusType.BOW_STRENGTH);
		GM.coinManager.create(600, 300, textures.get("coin.png"));
		
		JsonLevel jsonLvl = new JsonLevel();
		jsonLvl.width = width;
		
		
		float x = 100;
		for (int i = 0; i < 30; ++i) {
			x += MathUtils.random(100, 400);
			float y = 100;
			int count = MathUtils.random(1, 5);
			for(int j=0; j<count; j++){
				GM.blockManager.createSmallBlock(x, y + i * 40, textures.get("block2.png"));
				JsonRectangleEntity jsonRect = new JsonRectangleEntity();
				jsonRect.x = x;
				jsonRect.y = y;
				jsonRect.type = "block";
				jsonRect.texture = "block2.png";
				jsonLvl.entities.add(jsonRect);
			}
		}
		Json json = new Json();
		System.out.println(json.prettyPrint(jsonLvl));
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
		Body groundBody = GM.world.createBody(groundBodyDef);
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(width, height);
		groundBody.createFixture(groundBox, 0.0f);
	}

	public HashMap<String, TextureRegion> getTextures() {
		return textures;
	}

	public void setTextures(HashMap<String, TextureRegion> textures) {
		this.textures = textures;
	}

	public ParallaxBackground getBackground() {
		return background;
	}

	public void setBackground(ParallaxBackground background) {
		this.background = background;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}
}
