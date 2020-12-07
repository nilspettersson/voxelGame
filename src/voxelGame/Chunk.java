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
	private Geometry mesh;
	private Shader shader = new MeshShader("block.glsl");
	
	private Entity water;
	
	private byte[][][] cells;
	private int column;
	private int row;
	
	private int width;
	private int height;
	
	private Noise noise;
	
	public Chunk(int width, int height, int column, int row, Noise noise) {
		this.noise = noise;
		this.column = column;
		this.row = row;
		
		this.width = width;
		this.height = height;
		
		mesh = new Geometry(80);
		entity = new Entity(0, shader);
		
		water = new Entity(0, shader);
		
		//[x][y][z]
		cells = new byte[width][height][width];
		
		generateTerain();
		addBlockTypes();
	}
	
	public void generateTerain() {
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y < cells[0].length; y++) {
				for(int z = 0; z < cells[0][0].length; z++) {
					byte blockId = 0;
					float myX = x + column * width;
					float myZ = z + row * width;
					
					float height = noise.getHeightAt(myX, myZ, 0.3f) * 10f + 0;
					
					if(height > this.height - 4) {
						height = this.height - 4;
					}
					
					
					if(y <= height) {
						blockId = Block.ROCK;
					}
					
					
					cells[x][y][z] = blockId;
				}
			}
		}
	}
	
	public void addBlockTypes() {
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y < cells[0].length; y++) {
				for(int z = 0; z < cells[0][0].length; z++) {
					byte blockId = 0;
					if(cells[x][y][z] == Block.ROCK && (cells[x][y + 1][z] == Block.Air || cells[x][y + 1][z] == Block.WATER)) {
						
						float myX = x + column * width;
						float myZ = z + row * width;
						float value = noise.getBiomeAt(myX, myZ, 12f) * 0.6f;
						
						Biome.heightBlockRules(cells, x, y, z, Biome.getBiome(value));

					}
					else if(y <= Biome.WATERLEVEL && cells[x][y][z] == Block.Air) {
						cells[x][y][z] = Block.WATER;
					}
					
					
				}
			}
		}
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
						
						if(z == 0 || cells[x][y][z - 1] == Block.Air || cells[x][y][z - 1] == Block.WATER) {
							entity.getGeometry().createFaceBack(newX, newY, newZ, texture, sprite[0],  sprite[1]);
						}
						if(z == width - 1 || cells[x][y][z + 1] == Block.Air || cells[x][y][z + 1] == Block.WATER) {
							entity.getGeometry().createFaceFront(newX, newY, newZ, texture,  sprite[2],  sprite[3]);
						}
						if(x == 0 || cells[x - 1][y][z] == Block.Air || cells[x - 1][y][z] == Block.WATER) {
							entity.getGeometry().createFaceLeft(newX, newY, newZ, texture,  sprite[4],  sprite[5]);
						}
						if(x == width - 1 || cells[x + 1][y][z] == Block.Air || cells[x + 1][y][z] == Block.WATER) {
							entity.getGeometry().createFaceRight(newX, newY, newZ, texture,  sprite[6],  sprite[7]);
						}
						if(y == height - 1 || cells[x][y + 1][z] == Block.Air || cells[x][y + 1][z] == Block.WATER) {
							entity.getGeometry().createFaceUp(newX, newY, newZ, texture,  sprite[8],  sprite[9]);
						}
						if(y == 0 || cells[x][y - 1][z] == Block.Air || cells[x][y - 1][z] == Block.WATER) {
							entity.getGeometry().createFaceDown(newX, newY, newZ, texture,  sprite[10],  sprite[11]);
						}
						
					}
				}
			}
		}
		
		
		
		//entity.setGeometry(mesh);
		entity.bindGeometry();
		
		water.bindGeometry();
	}
	
	

	public Geometry getMesh() {
		return mesh;
	}

	public void setMesh(Geometry mesh) {
		this.mesh = mesh;
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
