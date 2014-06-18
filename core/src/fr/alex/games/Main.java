package fr.alex.games;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Main extends ApplicationAdapter {
	GameScreen gameScreen;
	
	@Override
	public void create () {
		gameScreen = new GameScreen();
		gameScreen.show();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.3f, .3f, .3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameScreen.render(Gdx.graphics.getDeltaTime());
	}
}
