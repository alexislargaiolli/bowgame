package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import fr.alex.games.entity.bonus.BonusType;

public class BonusEntity extends CircleEntity {

	private BonusType bonusType;
	private Vector2 counterForce;

	public BonusEntity(float x, float y, TextureRegion textureRegion, BonusType bonus) {
		super(x, y, 20, 0, 1f, .2f, .2f, false, textureRegion);
		this.bonusType = bonus;
		fixture.setUserData(this);
		//counterForce = new Vector2(0, body.getMass() * 15 );
	}

	@Override
	public void update(float delta) {
		//body.setTransform(Utils.toBox(position.x), Utils.toBox(position.y), 0);
		//body.applyForce(counterForce, body.getWorldCenter(), false);
	}

	public BonusType getBonusType() {
		return bonusType;
	}

	public void setBonusType(BonusType bonusType) {
		this.bonusType = bonusType;
	}

}
