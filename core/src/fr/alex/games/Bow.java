package fr.alex.games;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.math.Vector2;

public class Bow {
	private Vector2 origin;
	private float strength;

	public Bow(Vector2 origin) {
		super();
		this.origin = origin;
		strength = 30;
	}

	public void fire(Vector2 direction, TextureRegion texture, PooledEffect effect) {
		Vector2 o = new Vector2(origin).add(new Vector2(direction).scl(30));
		Arrow arrow = GM.arrowManager.create(o, direction, texture, effect);
		arrow.getBody().applyForceToCenter(direction.scl(strength), true);
	}

	public void setOrigin(float x, float y) {
		this.origin.x = x;
		this.origin.y = y;
	}

	public float getWorldOriginX() {
		return this.origin.x;
	}

	public float getWorldOriginY() {
		return this.origin.y;
	}

	public float getBoxOriginX() {
		return Utils.toBox(this.origin.x);
	}

	public float getBoxOriginY() {
		return Utils.toBox(this.origin.y);
	}

	public float getStrength() {
		return strength;
	}

	public void setStrength(float strength) {
		this.strength = strength;
	}

}
