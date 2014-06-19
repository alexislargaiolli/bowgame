package fr.alex.games.entity;

import com.badlogic.gdx.math.Vector2;

import fr.alex.games.Arrow;
import fr.alex.games.BowGame;

public class ArrowManager extends EntityManager<Arrow> {
	
	public Arrow create(Vector2 origin, Vector2 direction) {
		Arrow arrow = new Arrow(origin.x, origin.y, direction.angleRad(), null);
		entities.add(arrow);
		return arrow;
	}

	@Override
	protected void updateEntity(float delta, Entity e) {
		e.update(delta);
	}

	@Override
	protected void removedEntity(Entity arrow) {
		BowGame.world.destroyBody(arrow.getBody());
	}

}
