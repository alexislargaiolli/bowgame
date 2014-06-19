package fr.alex.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import fr.alex.games.background.ParallaxBackground;
import fr.alex.games.background.ParallaxLayer;
import fr.alex.games.entity.ArrowManager;
import fr.alex.games.entity.BlockManager;

public class BowGame {
	static final float BOX_STEP = 1 / 60f;
	static final int BOX_VELOCITY_ITERATIONS = 6;
	static final int BOX_POSITION_ITERATIONS = 2;
	public static final float WORLD_TO_BOX = 0.01f;
	public static final float BOX_WORLD_TO = 100f;
	public static World world;
	public static ArrowManager arrowManager;
	public static BlockManager blockManager;
	
	public static AssetManager assets;
	private OrthographicCamera camera;
	private Box2DDebugRenderer debugRenderer;
	private SpriteBatch batch;
	
	private Level level;
	private Bow bow;
	private Player player;
	private Matrix4 debugMatrix;

	public BowGame(Level level, OrthographicCamera cam) {
		super();
		this.level = level;
		this.camera = cam;
		assets = new AssetManager();
		batch = new SpriteBatch();
		camera.viewportHeight = Gdx.graphics.getHeight();
		camera.viewportWidth = Gdx.graphics.getWidth();
		camera.position.set(Gdx.graphics.getWidth() * 0.5f, Gdx.graphics.getHeight() * .5f, 0f);
		camera.update();				
			
		debugRenderer = new Box2DDebugRenderer();				
		
		loadResources();
		init();
	}
	
	TextureRegion region1;
	TextureRegion region2;
	TextureRegion region3;
	TextureRegion region4;
	ParallaxBackground background;
	
	public void loadResources(){
		assets.load("bg1.png", Texture.class);
		assets.load("bg2.png", Texture.class);
		assets.load("bg3.png", Texture.class);
		assets.load("bg4.png", Texture.class);
		assets.load(BlockManager.smallBlockTextureName, Texture.class);
		while(!assets.update()){}
		region1 = new TextureRegion(assets.get("bg1.png", Texture.class));
		region2 = new TextureRegion(assets.get("bg2.png", Texture.class));
		region3 = new TextureRegion(assets.get("bg3.png", Texture.class));
		region4 = new TextureRegion(assets.get("bg4.png", Texture.class));		
	}
	
	public void init(){
		arrowManager = new ArrowManager();
		blockManager = new BlockManager();
		background = new ParallaxBackground(new ParallaxLayer[]{
	            new ParallaxLayer(region1,new Vector2(),new Vector2()),
	            new ParallaxLayer(region2,new Vector2(0.1f,0f),new Vector2()),
	            new ParallaxLayer(region3,new Vector2(0.5f,0),new Vector2()),
	            new ParallaxLayer(region4,new Vector2(1f,0),new Vector2())
	      }, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),new Vector2(150,0));
		
		level.init();
	
		world.setContactListener(new GameCollisions());
		
		bow = new Bow(new Vector2(60, 20));
		player = new Player(40 , 200);
		player.setBow(bow);
	}
	
	public void draw(float delta){
		background.render(delta);
		debugRenderer.render(world, debugMatrix);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		blockManager.draw(batch);
		batch.end();
	}
	
	public void update(float delta){
		camera.position.set(Utils.toWorld(player.getBody().getPosition().x) + camera.viewportWidth * .3f, Gdx.graphics.getHeight() * .5f, 0f);
		camera.update();
		debugMatrix = camera.combined.cpy();
	    debugMatrix.scale(BOX_WORLD_TO, BOX_WORLD_TO, 1.0f);
	    
		world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
		player.update(delta);
		arrowManager.update(delta);
	}
	
	public void onClick(float x, float y){
		Vector2 direction = new Vector2(x, y).sub(bow.getWorldOriginX(), bow.getWorldOriginY()).nor();
		bow.fire(direction);
		if(direction.y < 0 && direction.x > -.5f && direction.x < .5f){
			player.jump();
		}
	}
	
	public void onKeyUp(int key){
		if(key == Keys.SPACE){
			player.jump();
		}
	}
}
