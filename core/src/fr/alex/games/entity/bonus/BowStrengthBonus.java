package fr.alex.games.entity.bonus;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fr.alex.games.GM;

public class BowStrengthBonus extends Bonus {

	private float bonus;
	
	public BowStrengthBonus(float duration, float bonus) {
		super(duration);
		this.bonus = bonus;
	}

	@Override
	public void enableEffect() {
		float strength = GM.bowGame.getBow().getStrength();
		GM.bowGame.getBow().setStrength(strength + bonus);
	}

	@Override
	public void disableEffect() {
		float strength = GM.bowGame.getBow().getStrength();
		GM.bowGame.getBow().setStrength(strength - bonus);
	}

	@Override
	public void draw(SpriteBatch batch) {
		
	}

	
}
