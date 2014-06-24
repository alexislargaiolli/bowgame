package fr.alex.games.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import fr.alex.games.Utils;
import fr.alex.games.entity.bonus.BonusType;
import fr.alex.games.json.JsonBonusEntity;

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
	
	public JsonBonusEntity toJsonEntity() {
		JsonBonusEntity e = new JsonBonusEntity();
		e.x = Utils.toWorld(body.getPosition().x);
		e.y = Utils.toWorld(body.getPosition().y);				
		e.density = fixture.getDensity();
		e.friction = fixture.getFriction();
		e.resitution = fixture.getRestitution();
		e.radius = radius;
		e.bonus = bonusType;
		e.type = "bonus";
		return e;
	}

}
