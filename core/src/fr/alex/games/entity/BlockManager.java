package fr.alex.games.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.alex.games.BowGame;

public class BlockManager extends EntityManager<Block> {

	private float smallBockWidth = 50;
	private float smallBockHeight = 40;
	public static String smallBlockTextureName = "block2.png";
	private TextureRegion smallBlockTexture;
	
	
	public BlockManager(){
		smallBlockTexture = new TextureRegion(BowGame.assets.get(smallBlockTextureName, Texture.class));
	}
	
	public Block createSmallBlock(float x, float y){
		return createBlock(x, y, smallBockWidth, smallBockHeight, smallBlockTexture);
	}
	
	public Block createBlock(float x, float y, float width, float height, TextureRegion textureRegion){
		Block block = new Block(x, y, width, height, textureRegion);
		entities.add(block);
		
		return block;
	}
	
	@Override
	protected void updateEntity(float delta, Entity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void removedEntity(Entity e) {
		// TODO Auto-generated method stub
		
	}
}
