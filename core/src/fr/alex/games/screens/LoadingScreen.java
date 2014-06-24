package fr.alex.games.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fr.alex.games.GM;

public class LoadingScreen implements Screen{

	private SpriteBatch batch;
	private BitmapFont font;
	private String message = "Clickez pour continuer";
	
	public LoadingScreen(){
		batch = new SpriteBatch();
		font = new BitmapFont();
	}
	
	@Override
	public void render(float delta) {
		if(GM.assetManager.update()){
			batch.begin();
			font.draw(batch, message, 200, 200);
			batch.end();
			if(Gdx.input.isTouched()){
				GM.bowGame.init();
				ScreenManager.getInstance().show(fr.alex.games.screens.Screen.GAME);
			}
		}
		else{
			batch.begin();
			font.draw(batch, GM.assetManager.getProgress() + "%", 200, 200);
			batch.end();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
	
}
