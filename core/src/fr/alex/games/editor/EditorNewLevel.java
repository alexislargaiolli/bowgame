package fr.alex.games.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.alex.games.GM;
import fr.alex.games.json.JsonLevel;
import fr.alex.games.screens.ScreenManager;
import fr.alex.games.screens.Screens;

public class EditorNewLevel implements Screen {

	Stage stage;
	TextField inputLevelName;	
	TextField inputLevelWidth;
	
	public EditorNewLevel(){
		stage = new Stage();
		inputLevelName = new TextField("", GM.skin);
		inputLevelWidth = new TextField("", GM.skin);
		Table table = new Table(GM.skin);
		table.setPosition(200, 300);
		table.add("Level name: ");
		table.add(inputLevelName);
		table.row();
		table.add("Level width");
		table.add(inputLevelWidth);
		table.row().colspan(2);
		TextButton bt = new TextButton("Create", GM.skin);
		bt.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {				
				GM.currentEditedLevel = new JsonLevel();
				GM.currentEditedLevel.name = inputLevelName.getText();
				GM.currentEditedLevel.width = Integer.parseInt(inputLevelWidth.getText());
				GM.currentEditedLevel.justCreated = true;
				ScreenManager.getInstance().show(Screens.EDITOR);
				super.clicked(event, x, y);
			}
			
		});
		table.add(bt);
		stage.addActor(table);
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
