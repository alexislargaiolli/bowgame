package fr.alex.games.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import fr.alex.games.GM;
import fr.alex.games.entity.Block;
import fr.alex.games.entity.BonusEntity;
import fr.alex.games.entity.Coin;

public class Editor implements Screen, InputProcessor {

	static final float BOX_STEP = 1 / 60f;
	static final int BOX_VELOCITY_ITERATIONS = 6;
	static final int BOX_POSITION_ITERATIONS = 2;
	public static final float WORLD_TO_BOX = 0.01f;
	public static final float BOX_WORLD_TO = 100f;
	
	private List<Block> blocks;
	private List<Coin> coins;
	private List<BonusEntity> bonus;
	private OrthographicCamera camera;
	private Box2DDebugRenderer boxDebugRenderer;
	private SpriteBatch batch;
	private ShapeRenderer debugRenderer;
	private Matrix4 debugMatrix;
	private Map<String, TextureRegion> textures;
	
	
	public Editor(){
		blocks = new ArrayList<Block>();
		coins = new ArrayList<Coin>();
		bonus = new ArrayList<BonusEntity>();
		camera = new OrthographicCamera();
		camera.viewportHeight = Gdx.graphics.getHeight();
		camera.viewportWidth = Gdx.graphics.getWidth();
		
		textures = new HashMap<String, TextureRegion>();
		
		textures.put("block2.png", null);
		
		for(String texture : textures.keySet()){
			TextureRegion region = new TextureRegion(GM.assetManager.get(texture, Texture.class));
			textures.put(texture, region);
		}
		
		boxDebugRenderer = new Box2DDebugRenderer();
		batch = new SpriteBatch();
		debugRenderer = new ShapeRenderer();
		
		GM.world = new World(new Vector2(0, -10), true);
		while(!GM.assetManager.update());
	}
	
	@Override
	public void render(float delta) {		
		camera.update();
		debugMatrix = camera.combined.cpy();
		debugMatrix.scale(BOX_WORLD_TO, BOX_WORLD_TO, 1.0f);
		
		
		boxDebugRenderer.render(GM.world, debugMatrix);
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.LEFT){
			
		}
		else if(keycode == Keys.RIGHT){
			
		}
		else if(keycode == Keys.B){
			Block block = GM.blockManager.createSmallBlock(100, 100, textures.get("block2.png"));
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
