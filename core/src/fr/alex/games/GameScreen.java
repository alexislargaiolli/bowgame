package fr.alex.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class GameScreen implements Screen, InputProcessor{	
	
	private OrthographicCamera camera; 
	private BowGame game;
	
	public GameScreen(){
		Level level = new Level();
		camera = new OrthographicCamera();
		game = new BowGame(level, camera);
	}
	
	@Override
	public void render(float delta) {
		game.update(delta);
		game.draw(delta);
	}
	
	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
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
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		game.onKeyUp(keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {		
		Vector3 p = camera.unproject(new Vector3(screenX, screenY, 0));
		game.onClick(p.x, p.y);
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
