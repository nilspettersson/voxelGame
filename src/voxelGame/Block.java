package voxelGame;

public class Block {
	
	public static final int Air = 0;
	public static final int GRASS = 1;
	public static final int DIRT = 2;
	
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
					0, 0,
					0, 0,
					0, 0,
					0, 0,
					0, 0,
					0, 0,
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
