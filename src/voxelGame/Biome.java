package voxelGame;

public class Biome {
	
	public static final int GRASS = 0;
	public static final int ROCK = 1;
	
	public static final int WATERLEVEL = 14;
	
	public static int getBiome(float noise) {
		if(Math.abs(GRASS - noise) < Math.abs(ROCK - noise)) {
			return GRASS;
		}
		else {
			return ROCK;
		}
	}
	
	
	public static void heightBlockRules (byte[][][] cells, int x, int y, int z, int biometype) {
		if(biometype == GRASS) {
			cells[x][y][z] = Block.GRASS;
			
			if(cells[x][y - 1][z] == Block.ROCK) {
				cells[x][y - 1][z] = Block.DIRT;
			}
			if(cells[x][y - 2][z] == Block.ROCK) {
				cells[x][y - 2][z] = Block.DIRT;
			}
			if(cells[x][y - 3][z] == Block.ROCK) {
				cells[x][y - 3][z] = Block.DIRT;
			}
			if(cells[x][y - 4][z] == Block.ROCK) {
				cells[x][y - 4][z] = Block.DIRT;
			}
		}
		else if(biometype == ROCK){
			cells[x][y][z] = Block.ROCK;
			
			if(cells[x][y - 1][z] == Block.ROCK) {
				cells[x][y - 1][z] = Block.ROCK;
			}
			if(cells[x][y - 2][z] == Block.ROCK) {
				cells[x][y - 2][z] = Block.ROCK;
			}
			if(cells[x][y - 3][z] == Block.ROCK) {
				cells[x][y - 3][z] = Block.ROCK;
			}
			if(cells[x][y - 4][z] == Block.ROCK) {
				cells[x][y - 4][z] = Block.ROCK;
			}
		}
		
		if(y <= WATERLEVEL + 1) {
			cells[x][y][z] = Block.SAND;
		}
		
		
		
	}
	
	
}
