package voxelGame;

import org.joml.Vector3f;

import niles.lwjgl.entity.Entity;
import niles.lwjgl.entity.Geometry;
import niles.lwjgl.npsl.MeshShader;
import niles.lwjgl.npsl.Shader;
import niles.lwjgl.util.Texture;
import noise.Noise;

public class Chunk {
	
	private Entity entity;
	private Shader shader = new MeshShader("block.glsl");
	private Entity water;
	
	private byte[][][] cells;
	private int column;
	private int row;
	
	private byte[][][] frontCells;
	private byte[][][] backCells;
	private byte[][][] leftCells;
	private byte[][][] rightCells;
	
	
	
	//how many blocks one chunk contains.
	private int width;
	private int height;
	
	private Noise noise;
	
	public Chunk(int width, int height, int column, int row, Noise noise) {
		this.noise = noise;
		this.column = column;
		this.row = row;
		
		this.width = width;
		this.height = height;
		
		entity = new Entity(0, shader);
		
		water = new Entity(0, shader);
		
		//[x][y][z]
		cells = new byte[width][height][width];
		
		frontCells = new byte[width][height][1];
		backCells = new byte[width][height][1];
		leftCells = new byte[1][height][width];
		rightCells = new byte[1][height][width];
		
		generateTerain();
		addBlockTypes();
		generateCaves();
		
	}
	
	public void generateTerain() {
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y < cells[0].length; y++) {
				for(int z = 0; z < cells[0][0].length; z++) {
					byte blockId = Block.Air;
					float myX = x + column * width;
					float myZ = z + row * width;
					
					float height = noise.getHeightAt(myX, myZ, 0.3f) * 10f + 0;
					if(height > this.height - 4) {
						height = this.height - 4;
					}
					if(y <= height) {
						blockId = Block.ROCK;
					}
					else if(y <= Biome.WATERLEVEL) {
						blockId = Block.WATER;
					}
					
					cells[x][y][z] = blockId;
					
					
					//adding neighboring cells
					if(x == 0) {
						blockId = Block.Air;
						height = noise.getHeightAt(myX - 1, myZ, 0.3f) * 10f + 0;
						if(height > this.height - 4) {
							height = this.height - 4;
						}
						if(y <= height) {
							blockId = Block.ROCK;
						}
						else if(y <= Biome.WATERLEVEL) {
							blockId = Block.WATER;
						}
						leftCells[0][y][z] = blockId;
					}
					if(x == width - 1) {
						
						blockId = Block.Air;
						height = noise.getHeightAt(myX + 1, myZ, 0.3f) * 10f + 0;
						if(height > this.height - 4) {
							height = this.height - 4;
						}
						
						if(y <= height) {
							blockId = Block.ROCK;
						}
						else if(y <= Biome.WATERLEVEL) {
							blockId = Block.WATER;
						}
						rightCells[0][y][z] = blockId;
					}
					
					
					if(z == 0) {
						blockId = Block.Air;
						height = noise.getHeightAt(myX, myZ - 1, 0.3f) * 10f + 0;
						if(height > this.height - 4) {
							height = this.height - 4;
						}
						if(y <= height) {
							blockId = Block.ROCK;
						}
						else if(y <= Biome.WATERLEVEL) {
							blockId = Block.WATER;
						}
						backCells[x][y][0] = blockId;
					}
					
					if(z == width - 1) {
						blockId = Block.Air;
						height = noise.getHeightAt(myX, myZ + 1, 0.3f) * 10f + 0;
						if(height > this.height - 4) {
							height = this.height - 4;
						}
						if(y <= height) {
							blockId = Block.ROCK;
						}
						else if(y <= Biome.WATERLEVEL) {
							blockId = Block.WATER;
						}
						frontCells[x][y][0] = blockId;
					}
					
					
				}
			}
		}
	}
	
	public void addBlockTypes() {
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y < cells[0].length; y++) {
				for(int z = 0; z < cells[0][0].length; z++) {
					byte blockId = 0;
					if(cells[x][y][z] == Block.ROCK && (cells[x][y + 1][z] == Block.Air)) {
						
						float myX = x + column * width;
						float myZ = z + row * width;
						float value = noise.getBiomeAt(myX, myZ, 12f) * 0.6f;
						
						if(y > 4) {
							Biome.heightBlockRules(cells, x, y, z, Biome.getBiome(value));
						}

					}
					
				}
			}
		}
	}
	
	public void generateCaves() {
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y < cells[0].length; y++) {
				for(int z = 0; z < cells[0][0].length; z++) {
					
					if(!shouldGenerateCave(x, y, z)) {
						continue;
					}
					
					
					float myX = x + column * width;
					float myZ = z + row * width;
					
					float caveMax = 0.3f;
					
					float cave = noise.getCaveAt(myX, myZ, y, 1f) * 1f + 0;
					if(cave > caveMax) {
						cells[x][y][z] = Block.Air;
					}
					
					//adding neighboring cells
					if(x == 0) {
						cave = noise.getCaveAt(myX - 1, myZ, y, 1f) * 1f + 0;
						if(cave > caveMax) {
							leftCells[0][y][z] = Block.Air;
						}
					}
					if(x == width - 1) {
						cave = noise.getCaveAt(myX + 1, myZ, y, 1f) * 1f + 0;
						if(cave > caveMax) {
							rightCells[0][y][z] = Block.Air;
						}
					}
					
					
					if(z == 0) {
						cave = noise.getCaveAt(myX, myZ - 1, y, 1f) * 1f + 0;
						if(cave > caveMax) {
							backCells[x][y][0] = Block.Air;
						}
					}
					
					if(z == width - 1) {
						cave = noise.getCaveAt(myX, myZ + 1, y, 1f) * 1f + 0;
						if(cave > caveMax) {
							frontCells[x][y][0] = Block.Air;
						}
					}
					
					
				}
			}
		}
	}
	
	public boolean shouldGenerateCave(int x, int y, int z) {
		//if cell is air, water or cell is at the bottom of the world
		if(cells[x][y][z] == Block.WATER || cells[x][y][z] == Block.Air || y == 0) {
			return false;
		}
		else if(cells[x][y + 1][z] == Block.WATER) {
			return false;
		}
		
		if(x > 0) {
			if(cells[x - 1][y][z] == Block.WATER) {
				return false;
			}
		}
		else {
			if(leftCells[0][y][z] == Block.WATER) {
				return false;
			}
		}
		
		if(x < width - 1) {
			if(cells[x + 1][y][z] == Block.WATER) {
				return false;
			}
		}
		else {
			System.out.println(rightCells[0][y][z]);
			if(rightCells[0][y][z] == Block.WATER) {
				return false;
			}
		}
		
		if(z > 0) {
			if(cells[x][y][z - 1] == Block.WATER) {
				return false;
			}
		}
		else {
			if(backCells[x][y][0] == Block.WATER) {
				return false;
			}
		}
		
		if(z < width - 1) {
			if(cells[x][y][z + 1] == Block.WATER) {
				return false;
			}
		}
		else {
			if(frontCells[x][y][0] == Block.WATER) {
				return false;
			}
		}
		
		return true;
			
	}
	
	
	public void generateMesh(Texture texture){
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y < cells[0].length; y++) {
				for(int z = 0; z < cells[0][0].length; z++) {
					
					
					float newX = x * 2;
					float newY = y * 2;
					float newZ = z * 2;
					if(cells[x][y][z] != Block.Air) {
						int[] sprite = Block.getBlockSprite(cells[x][y][z]);
						
						if(cells[x][y][z] == Block.WATER) {
							if(cells[x][y + 1][z] != Block.WATER) {
								water.getGeometry().createFaceUp(newX, newY, newZ, texture,  sprite[8],  sprite[9]);
							}
							continue;
						}
						
						//back
						if(z != 0) {
							if(z == 0 || cells[x][y][z - 1] == Block.Air || cells[x][y][z - 1] == Block.WATER) {
								entity.getGeometry().createFaceBack(newX, newY, newZ, texture, sprite[0],  sprite[1]);
							}
						}
						else {
							if(backCells[x][y][0] == Block.Air || backCells[x][y][0] == Block.WATER) {
								entity.getGeometry().createFaceBack(newX, newY, newZ, texture, sprite[0],  sprite[1]);
							}
						}
						
						//front
						if(z != width - 1)  {
							if(z == width - 1 || cells[x][y][z + 1] == Block.Air || cells[x][y][z + 1] == Block.WATER) {
								entity.getGeometry().createFaceFront(newX, newY, newZ, texture,  sprite[2],  sprite[3]);
							}
						}
						else {
							if(frontCells[x][y][0] == Block.Air || frontCells[x][y][0] == Block.WATER) {
								entity.getGeometry().createFaceFront(newX, newY, newZ, texture, sprite[0],  sprite[1]);
							}
						}
						
						//left
						if(x != 0) {
							if(x == 0 || cells[x - 1][y][z] == Block.Air || cells[x - 1][y][z] == Block.WATER) {
								entity.getGeometry().createFaceLeft(newX, newY, newZ, texture,  sprite[4],  sprite[5]);
							}
						}
						else {
							if(leftCells[0][y][z] == Block.Air || leftCells[0][y][z] == Block.WATER) {
								entity.getGeometry().createFaceLeft(newX, newY, newZ, texture,  sprite[4],  sprite[5]);
							}
						}
						
						//right
						if(x != width - 1) {
							if(x == width - 1 || cells[x + 1][y][z] == Block.Air || cells[x + 1][y][z] == Block.WATER) {
								entity.getGeometry().createFaceRight(newX, newY, newZ, texture,  sprite[6],  sprite[7]);
							}
						}
						else {
							if(rightCells[0][y][z] == Block.Air || rightCells[0][y][z] == Block.WATER) {
								entity.getGeometry().createFaceRight(newX, newY, newZ, texture,  sprite[6],  sprite[7]);
							}
						}
						
						if(y == height - 1 || cells[x][y + 1][z] == Block.Air || cells[x][y + 1][z] == Block.WATER) {
							entity.getGeometry().createFaceUp(newX, newY, newZ, texture,  sprite[8],  sprite[9]);
						}
						
						if(y > 0 && (cells[x][y - 1][z] == Block.Air || cells[x][y - 1][z] == Block.WATER)) {
							entity.getGeometry().createFaceDown(newX, newY, newZ, texture,  sprite[10],  sprite[11]);
						}
						
					}
				}
			}
		}
		
		entity.bindGeometry();
		water.bindGeometry();
	}
	


	public byte[][][] getCells() {
		return cells;
	}

	public void setCells(byte[][][] cells) {
		this.cells = cells;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Entity getWater() {
		return water;
	}

	public void setWater(Entity water) {
		this.water = water;
	}
	
	

}
