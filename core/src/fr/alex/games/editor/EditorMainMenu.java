package fr.alex.games.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.alex.games.GM;
import fr.alex.games.screens.ScreenManager;
import fr.alex.games.screens.Screens;

public class EditorMainMenu implements Screen {
	
	Stage stage;

	public EditorMainMenu(){
		stage = new Stage();
		TextButton bt = new TextButton("New level", GM.skin);
		bt.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScreenManager.getInstance().show(Screens.EDITOR_NEW_LEVEL);
				super.clicked(event, x, y);
			}
			
		});
		bt.setPosition(200, 300);
		stage.addActor(bt);
		
		bt = new TextButton("Load level", GM.skin);
		bt.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				ScreenManager.getInstance().show(Screens.EDITOR_LOAD_LEVEL);
				super.clicked(event, x, y);
			}
			
		});
		bt.setPosition(200, 200);
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
