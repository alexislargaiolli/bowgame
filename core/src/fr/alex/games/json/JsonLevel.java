package fr.alex.games.json;

import java.util.ArrayList;
import java.util.List;

public class JsonLevel {
	public float width;
	public String name;
	public boolean justCreated;
	public List<JsonEntity> entities;

	public JsonLevel() {
		entities = new ArrayList<JsonEntity>();
		
	}
	
	
}
