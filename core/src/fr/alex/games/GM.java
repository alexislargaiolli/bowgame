package fr.alex.games;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import fr.alex.games.entity.ArrowManager;
import fr.alex.games.entity.BlockManager;
import fr.alex.games.entity.BonusEntityManager;
import fr.alex.games.entity.CoinManager;
import fr.alex.games.entity.bonus.BonusManager;

public class GM {
	public static AssetManager assetManager;
	public static Level currentLevel;
	public static World world;
	public static BlockManager blockManager;
	public static ArrowManager arrowManager;
	public static BonusManager bonusManager;
	public static CoinManager coinManager;
	public static BonusEntityManager bonusEntityManager;
	public static final float WORLD_TO_BOX = 0.01f;
	public static final float BOX_WORLD_TO = 100f;
	public static Skin skin;
	public static BowGame bowGame;
	public static int score;
	public static ParticleEffectPool effectPool;
}
