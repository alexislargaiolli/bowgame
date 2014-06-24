package fr.alex.games.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.alex.games.GM;
import fr.alex.games.Level;

public class ScoreScreen implements Screen {

	Stage stage;

	public ScoreScreen() {
		stage = new Stage();

		TextButton bt = new TextButton("Recommencer", GM.skin);
		bt.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				GM.currentLevel = new Level();
				
				GM.bowGame.loadResources();
				GM.currentLevel.loadRessources();
				GM.bowGame.loadResources();
				GM.bowGame.init();
				ScreenManager.getInstance().show(fr.alex.games.screens.Screens.LOADING);				
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
