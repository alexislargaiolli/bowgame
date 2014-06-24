package fr.alex.games.screens;

import fr.alex.games.editor.Editor;

public enum Screen {

	INTRO {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new IntroScreen();
		}
	},

	MAIN_MENU {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new MainMenuScreen();
		}
	},

	LOADING {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new LoadingScreen();
		}
	},
	
	GAME {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new GameScreen();
		}
	},


	SCORE {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new ScoreScreen();
		}
	},
	
	EDITOR {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new Editor();
		}
	},
	
	CREDITS {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			/*return new CreditsScreen();*/
			return null;
		}
	};

	protected abstract com.badlogic.gdx.Screen getScreenInstance();

}