package fr.alex.games;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

import fr.alex.games.screens.ScreenManager;

public class BowGame {
	private enum State {
		STARTING, PLAYING, ENDING
	}

	static final float BOX_STEP = 1 / 60f;
	static final int BOX_VELOCITY_ITERATIONS = 6;
	static final int BOX_POSITION_ITERATIONS = 2;
	public static final float WORLD_TO_BOX = 0.01f;
	public static final float BOX_WORLD_TO = 100f;

	private OrthographicCamera camera;
	private Box2DDebugRenderer boxDebugRenderer;
	private SpriteBatch batch;
	private ShapeRenderer debugRenderer;
	private Stage stage;
	private Label lbInfo;
	private Label scoreLabel;
	private String scoreMessage = "Score: ";
	
	private State state;

	private Bow bow;
	private Player player;
	private Matrix4 debugMatrix;
	private boolean loose;
	TextureRegion arrow;
	private float counter;
	
	Array<PooledEffect> effects = new Array();

	public BowGame() {
		super();
		this.camera = new OrthographicCamera();
		stage = new Stage();
		lbInfo = new Label("", GM.skin);
		lbInfo.setPosition(200, 200);
		stage.addActor(lbInfo);
		
		scoreLabel = new Label(scoreMessage + GM.score, GM.skin);
		scoreLabel.setPosition(10, Gdx.graphics.getHeight() - 40);
		stage.addActor(scoreLabel);

		batch = new SpriteBatch();
		debugRenderer = new ShapeRenderer();
		camera.viewportHeight = Gdx.graphics.getHeight();
		camera.viewportWidth = Gdx.graphics.getWidth();

		boxDebugRenderer = new Box2DDebugRenderer();
	}

	public void loadResources() {
		ParticleEffect bombEffect = new ParticleEffect();
		bombEffect.load(Gdx.files.internal("fire.p"), Gdx.files.internal(""));
		GM.effectPool = new ParticleEffectPool(bombEffect, 1, 2);
		GM.assetManager.load("arrow2.png", Texture.class);
	}

	public void init() {
		GM.arrowManager.clear();
		GM.blockManager.clear();
		GM.bonusEntityManager.clear();
		GM.bonusManager.clear();
		GM.coinManager.clear();
		GM.currentLevel.init();
		GM.score = 0;

		camera.position.set(Gdx.graphics.getWidth() * 0.3f, Gdx.graphics.getHeight() * .5f, 0f);
		camera.update();

		arrow = new TextureRegion(GM.assetManager.get("arrow2.png", Texture.class));
		player = new Player(40, 200, new TextureRegion(GM.assetManager.get("block2.png", Texture.class)));
		bow = new Bow(new Vector2(player.getWorldX(), player.getWorldY()));
		player.setBow(bow);

		state = State.STARTING;
		counter = 0;
		loose = false;

	}

	public void draw(float delta) {
		GM.currentLevel.getBackground().render(state == State.PLAYING ? delta : 0);
		boxDebugRenderer.render(GM.world, debugMatrix);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		GM.blockManager.draw(batch);
		GM.arrowManager.draw(batch);
		GM.bonusEntityManager.draw(batch);
		GM.coinManager.draw(batch);
		
		for (int i = effects.size - 1; i >= 0; i--) {
		    PooledEffect effect = effects.get(i);
		    effect.draw(batch, delta);
		    if (effect.isComplete()) {
		        effect.free();
		        effects.removeIndex(i);
		    }
		}
		player.draw(batch);
		batch.end();

		debugRenderer.setProjectionMatrix(camera.combined);
		debugRenderer.begin(ShapeType.Line);
		player.debug(debugRenderer);
		debugRenderer.end();

		stage.draw();
	}

	public void update(float delta) {
		if (state == State.STARTING) {
			if (counter > 0) {
				counter -= delta;
				lbInfo.setText(Math.floor(counter) + "");
			} else if (counter <= 0) {
				state = State.PLAYING;
				lbInfo.addAction(alpha(0, 1));
			}
		} else if (state == State.PLAYING) {
			scoreLabel.setText(scoreMessage + GM.score);	
			camera.position.x += 95 * delta;
			checkGameState();
			GM.bonusEntityManager.update(delta);
			GM.bonusManager.update(delta);
			GM.coinManager.update(delta);
			player.update(delta);
		} else if (state == State.ENDING) {
			if (loose) {
				lbInfo.setText("You loose !");
				lbInfo.addAction(parallel(alpha(1, .5f), scaleTo(1.5f, 1.5f, 1, Interpolation.swing)));
			} else {
				lbInfo.setText("You win !");
				lbInfo.addAction(parallel(alpha(1, .5f), scaleTo(1.5f, 1.5f, 1, Interpolation.swing)));

			}
			counter += delta;
			if (counter > 2) {
				lbInfo.setText("Click to continue");
				if (Gdx.input.isTouched()) {
					ScreenManager.getInstance().show(fr.alex.games.screens.Screen.SCORE);
				}
			}
		}

		camera.update();
		debugMatrix = camera.combined.cpy();
		debugMatrix.scale(BOX_WORLD_TO, BOX_WORLD_TO, 1.0f);
		GM.world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
		GM.arrowManager.update(delta);
		stage.act();
	}

	private void checkGameState() {
		if (player.getWorldX() < camera.position.x - camera.viewportWidth * .5f) {
			state = State.ENDING;
			loose = true;
			GM.world.clearForces();
		} else if (player.getWorldX() > GM.currentLevel.getWidth() - camera.viewportWidth * .5f) {
			state = State.ENDING;
			GM.world.clearForces();
		}
	}

	public void onClick(float x, float y) {
		if (state == State.PLAYING) {
			Vector2 direction = new Vector2(x, y).sub(bow.getWorldOriginX(), bow.getWorldOriginY()).nor();
			PooledEffect effect = GM.effectPool.obtain();
			effects.add(effect);
			bow.fire(direction, arrow, effect);
			if (direction.y < 0 && direction.x > -.5f && direction.x < .5f) {
				player.jump();
			}
		}
	}

	public void onKeyUp(int key) {
		if (key == Keys.SPACE) {
			player.jump();
		}
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	public Bow getBow() {
		return bow;
	}

	public void setBow(Bow bow) {
		this.bow = bow;
	}
}
