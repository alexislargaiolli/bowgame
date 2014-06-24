package fr.alex.games.screens;

import fr.alex.games.editor.Editor;
import fr.alex.games.editor.EditorLoadLevel;
import fr.alex.games.editor.EditorMainMenu;
import fr.alex.games.editor.EditorNewLevel;

public enum Screens {

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
	
	EDITOR_MENU {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new EditorMainMenu();
		}
	},
	
	EDITOR_NEW_LEVEL {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new EditorNewLevel();
		}
	},
	
	EDITOR_LOAD_LEVEL {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance() {
			return new EditorLoadLevel();
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