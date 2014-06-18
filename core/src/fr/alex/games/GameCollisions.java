package fr.alex.games;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import fr.alex.games.entity.Block;

public class GameCollisions implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Object o1 = contact.getFixtureA().getUserData();
		Object o2 = contact.getFixtureB().getUserData();
		if (o1 != null && o2 != null) {
			if (o1 instanceof Arrow) {
				arrowContact((Arrow) o1, o2);
			} else if (o2 instanceof Arrow) {
				arrowContact((Arrow) o2, o1);
			}
		}
	}

	private void arrowContact(Arrow arrow, Object other) {
		arrow.setTimeBeforeDeath(3);
		if (other instanceof Arrow) {
			arrowContactArrow(arrow, (Arrow) other);
		}
		else if(other instanceof Block){
			arrowContactBlock(arrow, (Block) other);
		}
	}

	private void arrowContactArrow(Arrow arrow1, Arrow arrow2) {

	}
	
	private void arrowContactBlock(Arrow arrow1, Block block) {
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
