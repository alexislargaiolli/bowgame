package fr.alex.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen implements Screen, InputProcessor{

	static final float BOX_STEP = 1 / 60f;
	static final int BOX_VELOCITY_ITERATIONS = 6;
	static final int BOX_POSITION_ITERATIONS = 2;
	public static final float WORLD_TO_BOX = 0.01f;
	public static final float BOX_WORLD_TO = 100f;
	
	private OrthographicCamera camera;
	private Box2DDebugRenderer debugRenderer;
	
	private Level level;
	private Bow bow;
	
	public GameScreen(){
		camera = new OrthographicCamera();
		camera.viewportHeight = Gdx.graphics.getHeight() * WORLD_TO_BOX;
		camera.viewportWidth = Gdx.graphics.getWidth() * WORLD_TO_BOX;
		camera.position.set(Gdx.graphics.getWidth() * WORLD_TO_BOX * 0.5f, Gdx.graphics.getHeight() * WORLD_TO_BOX * .5f, 0f);
		camera.update();
		Game.world = new World(new Vector2(0, -10), true);
		level = new Level();
		debugRenderer = new Box2DDebugRenderer();
		bow = new Bow(new Vector2(60 * WORLD_TO_BOX, 20 * WORLD_TO_BOX));
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		draw();
	}
	
	private void update(float delta){
		Game.world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
	}
	
	private void draw(){
		debugRenderer.render(Game.world, camera.combined);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
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
		bow.fire(new Vector2(screenX  * GameScreen.WORLD_TO_BOX , Gdx.graphics.getHeight()  * GameScreen.WORLD_TO_BOX - screenY  * GameScreen.WORLD_TO_BOX));
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
