package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BlockManager extends EntityManager<Block> {

	private float smallBockWidth = 50;
	private float smallBockHeight = 40;
	
	
	public BlockManager(){

	}
	
	public Block createSmallBlock(float x, float y, TextureRegion texture){
		return createBlock(x, y, smallBockWidth, smallBockHeight, texture);
	}
	
	public Block createBlock(float x, float y, float width, float height, TextureRegion textureRegion){
		Block block = new Block(x, y, width, height, textureRegion);
		entities.add(block);
		
		return block;
	}

	@Override
	protected void updateEntity(float delta, Block e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void removedEntity(Block e) {
		// TODO Auto-generated method stub
		
	}
}
