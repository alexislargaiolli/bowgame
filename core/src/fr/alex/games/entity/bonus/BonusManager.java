package fr.alex.games.entity.bonus;

import fr.alex.games.entity.EntityManager;

public class BonusManager extends EntityManager<Bonus>{

	public void create(BonusType type){
		Bonus bonus = null;
		if(type == BonusType.BOW_STRENGTH){
			bonus = new BowStrengthBonus(5, 20);
		}
		entities.add(bonus);
		bonus.enableEffect();
	}
	
	@Override
	protected void updateEntity(float delta, Bonus e) {
		e.update(delta);
	}

	@Override
	protected void removedEntity(Bonus e) {
		e.disableEffect();
	}

}
