package voxelGame;

public class Biome {
	
	public static final float GRASS = 0;
	public static final float ROCK = 1;
	
	public static float getBiome(float noise) {
		if(Math.abs(GRASS - noise) < Math.abs(ROCK - noise)) {
			return GRASS;
		}
		else {
			return ROCK;
		}
	}
	
}
