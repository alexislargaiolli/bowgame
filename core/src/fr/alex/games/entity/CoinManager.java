package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.alex.games.GM;

public class CoinManager extends EntityManager<Coin>{

	public Coin create(float x, float y, TextureRegion texture){
		Coin coin = new Coin(x, y, texture);
		entities.add(coin);
		return coin;
	}
	
	@Override
	protected void updateEntity(float delta, Coin e) {
		
	}

	@Override
	protected void removedEntity(Coin e) {
		GM.world.destroyBody(e.getBody());
		GM.score++;
	}

}
