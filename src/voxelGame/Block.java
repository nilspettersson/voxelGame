package voxelGame;

public class Block {
	
	public static final int Air = 0;
	public static final int GRASS = 1;
	public static final int DIRT = 2;
	public static final int ROCK = 3;
	public static final int WATER = 4;
	public static final int SAND = 5;
	
	public static int[] getBlockSprite(int blockId) {
		if(blockId == GRASS) {
			return new int[] {
					1, 0,
					1, 0,
					1, 0,
					1, 0,
					0, 0,
					0, 0,
					};
		}
		else if(blockId == DIRT) {
			return new int[] {
					0, 1,
					0, 1,
					0, 1,
					0, 1,
					0, 1,
					0, 1,
					};
		}
		else if(blockId == ROCK) {
			return new int[] {
					2, 0,
					2, 0,
					2, 0,
					2, 0,
					2, 0,
					2, 0,
					};
		}
		else if(blockId == WATER) {
			return new int[] {
					1, 2,
					1, 2,
					1, 2,
					1, 2,
					1, 2,
					1, 2,
					};
		}
		else if(blockId == SAND) {
			return new int[] {
					3, 0,
					3, 0,
					3, 0,
					3, 0,
					3, 0,
					3, 0,
					};
		}
		
		return new int[] {
				0, 0,
				0, 0,
				0, 0,
				0, 0,
				0, 0,
				0, 0,
				};
	}

}
