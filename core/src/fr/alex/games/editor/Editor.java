package fr.alex.games.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;

import fr.alex.games.GM;
import fr.alex.games.Utils;
import fr.alex.games.entity.Block;
import fr.alex.games.entity.BonusEntity;
import fr.alex.games.entity.CircleEntity;
import fr.alex.games.entity.Coin;
import fr.alex.games.entity.PhysicEntity;
import fr.alex.games.entity.RectangleEntity;
import fr.alex.games.entity.bonus.BonusType;

public class Editor implements Screen, InputProcessor {

	static final float BOX_STEP = 1 / 60f;
	static final int BOX_VELOCITY_ITERATIONS = 6;
	static final int BOX_POSITION_ITERATIONS = 2;
	public static final float WORLD_TO_BOX = 0.01f;
	public static final float BOX_WORLD_TO = 100f;
	float lastX;
	float lastY;

	private List<Block> blocks;
	private List<Coin> coins;
	private List<BonusEntity> bonus;
	private OrthographicCamera camera;
	private Box2DDebugRenderer boxDebugRenderer;
	private SpriteBatch batch;
	private ShapeRenderer debugRenderer;
	private Matrix4 debugMatrix;
	private Map<String, TextureRegion> textures;
	private PhysicEntity selected;
	Stage stage;

	public Editor() {
		blocks = new ArrayList<Block>();
		coins = new ArrayList<Coin>();
		bonus = new ArrayList<BonusEntity>();
		stage = new Stage();
		initUI();

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0, Gdx.graphics.getHeight() * .5f, 0f);
		camera.update();

		textures = new HashMap<String, TextureRegion>();

		textures.put("block2.png", null);
		textures.put("bonus.png", null);
		textures.put("coin.png", null);

		for (String texture : textures.keySet()) {
			GM.assetManager.load(texture, Texture.class);
		}

		GM.assetManager.finishLoading();

		for (String texture : textures.keySet()) {
			TextureRegion region = new TextureRegion(GM.assetManager.get(texture, Texture.class));
			textures.put(texture, region);
		}

		boxDebugRenderer = new Box2DDebugRenderer();
		batch = new SpriteBatch();
		debugRenderer = new ShapeRenderer();

		GM.world = new World(new Vector2(0, -10), true);

		if (GM.currentEditedLevel.justCreated) {
			fromScratch();
		} else {
			fromLoad();
		}
	}

	public void fromScratch() {
		this.createWall(Utils.toBox(GM.currentEditedLevel.width*.5f), Utils.toBox(50), Utils.toBox(GM.currentEditedLevel.width), Utils.toBox(5));
		this.createWall(Utils.toBox(GM.currentEditedLevel.width*.5f), Utils.toBox(Gdx.graphics.getHeight()), Utils.toBox(GM.currentEditedLevel.width), Utils.toBox(5));
	}

	public void fromLoad() {

	}

	private void initUI() {
		Table table = new Table(GM.skin);
		table.setPosition(Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() - 20);

		final Dialog dialog = new Dialog("Element", GM.skin);

		TextButton bt = new TextButton("ADD", GM.skin);
		bt.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				dialog.show(stage);
				super.clicked(event, x, y);
			}

		});
		table.add(bt);
		
		bt = new TextButton("SAVE", GM.skin);
		bt.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				GM.currentEditedLevel.entities.clear();
				for(Block block : blocks){
					GM.currentEditedLevel.entities.add(block.toJsonEntity());
				}
				for(BonusEntity b : bonus){
					GM.currentEditedLevel.entities.add(b.toJsonEntity());
				}
				for(Coin c : coins){
					GM.currentEditedLevel.entities.add(c.toJsonEntity());
				}
				FileHandle f = Gdx.files.external("editor/levels/"+GM.currentEditedLevel.name.trim());
				Json json = new Json();
				json.toJson(GM.currentEditedLevel, f);
				super.clicked(event, x, y);
			}

		});
		table.add(bt);

		bt = new TextButton("Block", GM.skin);
		bt.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Block block = GM.blockManager.createSmallBlock(100, 100, textures.get("block2.png"));
				blocks.add(block);
				dialog.hide();
				super.clicked(event, x, y);
			}

		});
		dialog.row().center();
		dialog.add(bt);
		
		bt = new TextButton("Bonus", GM.skin);
		bt.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				BonusEntity b = GM.bonusEntityManager.create(camera.position.x, camera.position.y, textures.get("bonus.png"), BonusType.BOW_STRENGTH);
				bonus.add(b);
				dialog.hide();
				super.clicked(event, x, y);
			}

		});
		dialog.row().center();
		dialog.add(bt);
		
		
		bt = new TextButton("Coin", GM.skin);
		bt.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Coin c = GM.coinManager.create(camera.position.x, camera.position.y, textures.get("coin.png"));
				coins.add(c);
				dialog.hide();
				super.clicked(event, x, y);
			}

		});
		dialog.row().center();
		dialog.add(bt);
		
		
		dialog.row().center();
		bt = new TextButton("Cancel", GM.skin);
		bt.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				dialog.hide();
				super.clicked(event, x, y);
			}

		});
		dialog.add(bt).pad(40);

		stage.addActor(table);
	}

	@Override
	public void render(float delta) {
		camera.update();
		debugMatrix = camera.combined.cpy();
		debugMatrix.scale(BOX_WORLD_TO, BOX_WORLD_TO, 1.0f);

		// GM.world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS,
		// BOX_POSITION_ITERATIONS);
		boxDebugRenderer.render(GM.world, debugMatrix);

		debugRenderer.setProjectionMatrix(camera.combined);
		debugRenderer.begin(ShapeType.Filled);
		if (selected != null) {
			debugRenderer.circle(selected.getWorldX(), selected.getWorldY(), 5);
		}
		debugRenderer.end();

		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		InputMultiplexer multi = new InputMultiplexer();
		multi.addProcessor(stage);
		multi.addProcessor(this);
		Gdx.input.setInputProcessor(multi);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.LEFT) {

		} else if (keycode == Keys.RIGHT) {

		} else if (keycode == Keys.B) {

		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 v = camera.unproject(new Vector3(screenX, screenY, 0));
		float xClick = v.x;
		float yClick = v.y;
		for (Block b : blocks) {
			if (isClicked(b, xClick, yClick)) {
				selected = b;
				return true;
			}
		}
		for (BonusEntity b : bonus) {
			if (isClicked(b, xClick, yClick)) {
				selected = b;
				return true;
			}
		}
		
		for (Coin c : coins) {
			if (isClicked(c, xClick, yClick)) {
				selected = c;
				return true;
			}
		}
		
		selected = null;
		lastX = screenX;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (selected != null) {
			Vector3 p = camera.unproject(new Vector3(screenX, screenY, 0));
			float x = Utils.toBox(p.x);
			float y = Utils.toBox(p.y);
			selected.getBody().setTransform(x, y, 0);
		} else {
			float deltaX = screenX - lastX;
			camera.translate(deltaX, 0);
		}
		lastX = screenX;
		lastY = Gdx.graphics.getHeight() - screenY;
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isClicked(RectangleEntity e, float xClick, float yClick) {
		if (xClick > e.getWorldX() - e.getWorldWidth() * .5f && xClick < e.getWorldX() + e.getWorldWidth() * .5f) {
			if (yClick > e.getWorldY() - e.getWorldHeight() * .5f && yClick < e.getWorldY() + e.getWorldHeight() * .5f) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isClicked(CircleEntity e, float xClick, float yClick) {
		if (xClick > e.getWorldX() - e.getWorldRadius() && xClick < e.getWorldX() + e.getWorldRadius()) {
			if (yClick > e.getWorldY() - e.getWorldRadius() && yClick < e.getWorldY() + e.getWorldRadius()) {
				return true;
			}
		}
		return false;
	}

	private void createWall(float x, float y, float width, float height) {
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(x, y));
		Body groundBody = GM.world.createBody(groundBodyDef);
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(width, height);
		groundBody.createFixture(groundBox, 0.0f);
	}

}
