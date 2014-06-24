package fr.alex.games.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import fr.alex.games.BowGame;
import fr.alex.games.GM;
import fr.alex.games.Level;
import fr.alex.games.entity.ArrowManager;
import fr.alex.games.entity.BlockManager;
import fr.alex.games.entity.BonusEntityManager;
import fr.alex.games.entity.CoinManager;
import fr.alex.games.entity.bonus.BonusManager;

public class Main extends Game {
	GameScreen gameScreen;

	@Override
	public void create() {
		GM.assetManager = new AssetManager();
		GM.arrowManager = new ArrowManager();
		GM.blockManager = new BlockManager();
		GM.bonusEntityManager = new BonusEntityManager();
		GM.bonusManager = new BonusManager();
		GM.coinManager = new CoinManager();
		GM.skin = new Skin(Gdx.files.internal("uiskin.json"));
		GM.currentLevel = new Level();
		
		GM.bowGame = new BowGame();
		GM.bowGame.loadResources();
		GM.currentLevel.loadRessources();
		GM.bowGame.loadResources();
		
		
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().show(Screens.INTRO);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(.3f, .3f, .3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}
}
