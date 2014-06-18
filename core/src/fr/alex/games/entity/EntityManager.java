package fr.alex.games.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityManager<T extends Entity> {
	
	protected List<T> entities;
	
	private List<T> entitiesToRemove;

	public EntityManager() {
		entities = new ArrayList<T>();
		entitiesToRemove = new ArrayList<T>();
	}
	
	public void update(float delta){
		for(int i= 0; i < entities.size(); ++i){
			T e = entities.get(i);
			updateEntity(delta, e);
			if(e.isDead()){
				entitiesToRemove.add(e);
			}
		}
		for(int i= 0; i < entitiesToRemove.size(); ++i){
			T e = entitiesToRemove.get(i);
			entities.remove(e);
			removedEntity(e);
		}
		entitiesToRemove.clear();
	}
	
	protected abstract void updateEntity(float delta, Entity e);
	
	protected abstract void removedEntity(Entity e);
	
}
