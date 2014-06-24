package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import fr.alex.games.Arrow;
import fr.alex.games.GM;

public class ArrowManager extends EntityManager<Arrow> {
	
	public ArrowManager(){
		
	}
	
	public Arrow create(Vector2 origin, Vector2 direction, TextureRegion texture, PooledEffect effect) {
		Arrow arrow = new Arrow(origin.x, origin.y, direction.angleRad(), texture, effect);
		entities.add(arrow);
		return arrow;
	}

	@Override
	protected void updateEntity(float delta, Arrow e) {
		e.update(delta);
		
	}

	@Override
	protected void removedEntity(Arrow e) {
		GM.world.destroyBody(e.getBody());
	}

}
