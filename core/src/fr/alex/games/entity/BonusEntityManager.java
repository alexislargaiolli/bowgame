package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.alex.games.GM;
import fr.alex.games.entity.bonus.BonusType;

public class BonusEntityManager extends EntityManager<BonusEntity>{

	public BonusEntity create(float x, float y, TextureRegion texture, BonusType type){
		BonusEntity bonus = new BonusEntity(x, y, texture, type);		
		entities.add(bonus);
		return bonus;
	}
	
	@Override
	protected void updateEntity(float delta, BonusEntity e) {
		e.update(delta);
	}

	@Override
	protected void removedEntity(BonusEntity e) {
		GM.world.destroyBody(e.getBody());
	}
	
}
