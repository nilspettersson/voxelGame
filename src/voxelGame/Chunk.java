package voxelGame;

import niles.lwjgl.entity.Geometry;

public class Chunk {
	
	private Geometry mesh;
	private byte[][][] cells;
	private int column;
	private int row;
	
	public Chunk(int width, int height, int column, int row) {
		mesh = new Geometry(width * width * height * 36);
		
		//[x][y][z]
		cells = new byte[width][height][width];
		
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y < cells[0].length; y++) {
				for(int z = 0; z < cells[0][0].length; z++) {
					cells[x][y][z] = 1;
				}
			}
		}
		
		this.column = column;
		this.row = row;
	}
	
	public void generateMesh(){
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y < cells[0].length; y++) {
				for(int z = 0; z < cells[0][0].length; z++) {
					if(cells[x][y][z] == 1) {
						mesh.createFaceBack(x, y, z, ChunkManager.texture, 0, 0);
						mesh.createFaceFront(x, y, z, ChunkManager.texture, 0, 0);
						mesh.createFaceLeft(x, y, z, ChunkManager.texture, 0, 0);
						mesh.createFaceRight(x, y, z, ChunkManager.texture, 0, 0);
						mesh.createFaceUp(x, y, z, ChunkManager.texture, 0, 0);
						mesh.createFaceDown(x, y, z, ChunkManager.texture, 0, 0);
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
