package voxelGame;

import niles.lwjgl.entity.Geometry;

public class Chunk {
	
	private Geometry mesh;
	private byte[][][] cells;
	private int column;
	private int row;
	
	private int width;
	private int height;
	
	public Chunk(int width, int height, int column, int row) {
		mesh = new Geometry(width * width * height * 36);
		
		//[x][y][z]
		cells = new byte[width][height][width];
		
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y < cells[0].length; y++) {
				for(int z = 0; z < cells[0][0].length; z++) {
					//cells[x][y][z] = 1;
					cells[x][y][z] = (byte)(Math.random() * 2);
				}
			}
		}
		
		this.column = column;
		this.row = row;
		
		this.width = width;
		this.height = height;
	}
	
	public void generateMesh(){
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y < cells[0].length; y++) {
				for(int z = 0; z < cells[0][0].length; z++) {
					float newX = x * 2;
					float newY = y * 2;
					float newZ = z * 2;
					if(cells[x][y][z] == 1) {
						if(z == 0 || cells[x][y][z - 1] == 0) {
							mesh.createFaceBack(newX, newY, newZ, ChunkManager.texture, 0, 0);
						}
						if(z == width - 1 || cells[x][y][z + 1] == 0) {
							mesh.createFaceFront(newX, newY, newZ, ChunkManager.texture, 0, 0);
						}
						if(x == 0 || cells[x - 1][y][z] == 0) {
							mesh.createFaceLeft(newX, newY, newZ, ChunkManager.texture, 0, 0);
						}
						if(x == width - 1 || cells[x + 1][y][z] == 0) {
							mesh.createFaceRight(newX, newY, newZ, ChunkManager.texture, 0, 0);
						}
						if(y == height - 1 || cells[x][y + 1][z] == 0) {
							mesh.createFaceUp(newX, newY, newZ, ChunkManager.texture, 0, 0);
						}
						if(y == 0 || cells[x][y - 1][z] == 0) {
							mesh.createFaceDown(newX, newY, newZ, ChunkManager.texture, 0, 0);
						}
						
					}
				}
			}
		}
		mesh.updateVertices();
		mesh.updateIndices();
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

}
