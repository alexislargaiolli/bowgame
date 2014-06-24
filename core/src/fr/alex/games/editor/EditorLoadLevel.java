package fr.alex.games.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;

import fr.alex.games.GM;
import fr.alex.games.json.JsonLevel;
import fr.alex.games.screens.ScreenManager;
import fr.alex.games.screens.Screens;

public class EditorLoadLevel implements Screen {

	Stage stage;
	
	public EditorLoadLevel(){
		stage = new Stage();
		final List<FileHandle> list = new List<FileHandle>(GM.skin);
		list.setItems(Gdx.files.external("editor/levels").list());
		stage.addActor(list);
		
		TextButton bt = new TextButton("Load", GM.skin);
		bt.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				FileHandle levelFile = list.getSelected();
				if(levelFile != null){
					Json json = new Json();
					GM.currentEditedLevel = json.fromJson(JsonLevel.class, levelFile);
					ScreenManager.getInstance().show(Screens.EDITOR);
				}
				super.clicked(event, x, y);
			}
			
		});
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
