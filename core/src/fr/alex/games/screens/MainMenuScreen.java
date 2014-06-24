package fr.alex.games.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.alex.games.GM;

public class MainMenuScreen implements Screen{

	private Stage stage;
	
	public MainMenuScreen(){
		stage = new Stage();
		TextButton bt = new TextButton("play", GM.skin);
		bt.setPosition(200, 200);
		bt.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScreenManager.getInstance().show(fr.alex.games.screens.Screen.LOADING);
				super.clicked(event, x, y);
			}
			
		});
		stage.addActor(bt);
		
		bt = new TextButton("Editor", GM.skin);
		bt.setPosition(200, 160);
		bt.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScreenManager.getInstance().show(fr.alex.games.screens.Screen.EDITOR);
				super.clicked(event, x, y);
			}
			
		});
		stage.addActor(bt);
	}
	
	@Override
	public void render(float delta) {
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
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
