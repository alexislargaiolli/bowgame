package fr.alex.games;

import fr.alex.games.entity.Entity;

public class Arrow extends Entity{
	
	float timeBeforeDeath = -1;
	
	public Arrow(){
		
	}
	
	public void update(float delta){
		if(timeBeforeDeath > -1){
			timeBeforeDeath -= delta;
			if(timeBeforeDeath < 0){
				setDead(true);
			}
		}
	}

	public void setTimeBeforeDeath(float timeBeforeDeath) {
		if(this.timeBeforeDeath == -1){
			this.timeBeforeDeath = timeBeforeDeath;
		}
	}
	
}
