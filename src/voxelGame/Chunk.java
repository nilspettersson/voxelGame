package voxelGame;

import niles.lwjgl.entity.Entity;
import niles.lwjgl.entity.Geometry;
import niles.lwjgl.npsl.MeshShader;
import niles.lwjgl.npsl.Shader;
import niles.lwjgl.util.Texture;

public class Chunk {
	
	private Entity entity;
	private Geometry mesh;
	private Shader shader = new MeshShader("block.glsl");
	
	private byte[][][] cells;
	private int column;
	private int row;
	
	private int width;
	private int height;
	
	public Chunk(int width, int height, int column, int row) {
		//mesh = new Geometry(width * width * height * 36);
		mesh = new Geometry(800);
		entity = new Entity(0, shader);
		
		//[x][y][z]
		cells = new byte[width][height][width];
		
		generateTerain();
		
		this.column = column;
		this.row = row;
		
		this.width = width;
		this.height = height;
	}
	
	public void generateTerain() {
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y < cells[0].length; y++) {
				for(int z = 0; z < cells[0][0].length; z++) {
					byte blockId = 0;
					if(y <= 3) {
						blockId = Block.GRASS;
					}
					cells[x][y][z] = blockId;
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
						//System.out.println(mesh.getVertices().limit());
						
						int[] sprite = Block.getBlockSprite(cells[x][y][z]);
						
						if(z == 0 || cells[x][y][z - 1] == Block.Air) {
							mesh.createFaceBack(newX, newY, newZ, texture, sprite[0],  sprite[1]);
						}
						if(z == width - 1 || cells[x][y][z + 1] == Block.Air) {
							mesh.createFaceFront(newX, newY, newZ, texture,  sprite[2],  sprite[3]);
						}
						if(x == 0 || cells[x - 1][y][z] == Block.Air) {
							mesh.createFaceLeft(newX, newY, newZ, texture,  sprite[4],  sprite[5]);
						}
						if(x == width - 1 || cells[x + 1][y][z] == Block.Air) {
							mesh.createFaceRight(newX, newY, newZ, texture,  sprite[6],  sprite[7]);
						}
						if(y == height - 1 || cells[x][y + 1][z] == Block.Air) {
							mesh.createFaceUp(newX, newY, newZ, texture,  sprite[8],  sprite[9]);
						}
						if(y == 0 || cells[x][y - 1][z] == Block.Air) {
							mesh.createFaceDown(newX, newY, newZ, texture,  sprite[10],  sprite[11]);
						}
						
					}
				}
			}
		}
		entity.setGeometry(mesh);
		entity.bindGeometry();
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
	
	

}
