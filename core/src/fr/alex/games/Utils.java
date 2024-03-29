package fr.alex.games;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Utils {
	
	public static float toWorld(float f){
		return f * BowGame.BOX_WORLD_TO;
	}
	
	public static Vector2 toWorld(Vector2 vector2){
		vector2.x *= BowGame.BOX_WORLD_TO;
		vector2.y *= BowGame.BOX_WORLD_TO;
		return vector2;
	}
	
	public static Vector3 toWorld(Vector3 vector3){
		vector3.x *= BowGame.BOX_WORLD_TO;
		vector3.y *= BowGame.BOX_WORLD_TO;
		return vector3;
	}
	
	public static float toBox(float f){
		return f * BowGame.WORLD_TO_BOX;
	}
	
	public static Vector2 toBox(Vector2 vector2){
		vector2.x *= BowGame.WORLD_TO_BOX;
		vector2.y *= BowGame.WORLD_TO_BOX;
		return vector2;
	}
	
	public static Vector3 toBox(Vector3 vector3){
		vector3.x *= BowGame.WORLD_TO_BOX;
		vector3.y *= BowGame.WORLD_TO_BOX;
		return vector3;
	}
}
