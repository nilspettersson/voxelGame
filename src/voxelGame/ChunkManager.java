package voxelGame;

import java.util.ArrayList;
import org.joml.Vector3f;
import niles.lwjgl.util.Texture;

public class ChunkManager {
	
	private Texture texture = new Texture("res/atlas.png");
	
	private ArrayList<Chunk> chunks;
	private int chunkWidth;
	private int chunkHeight;
	
	public ChunkManager(int chunkWidth, int chunkHeight) {
		chunks = new ArrayList<Chunk>();
		
		texture.setSpriteWidth(16);
		texture.setSpriteHeight(16);
		
		this.chunkHeight = chunkHeight;
		this.chunkWidth = chunkWidth;
	}
	
	
	
	
	public void addChunk(int column, int row) {
		chunks.add(new Chunk(chunkWidth, chunkHeight, column, row));
		chunks.get(chunks.size() - 1).generateMesh(texture);
		chunks.get(chunks.size() - 1).getEntity().addTexture(getTexture());
		chunks.get(chunks.size() - 1).getEntity().getTransform().setPosition(new Vector3f(column * chunkWidth * 2, 0, row * chunkHeight * 2));
	}

	
	
	public ArrayList<Chunk> getChunks() {
		return chunks;
	}

	public void setChunks(ArrayList<Chunk> chunks) {
		this.chunks = chunks;
	}

	public int getChunkWidth() {
		return chunkWidth;
	}

	public void setChunkWidth(int chunkWidth) {
		this.chunkWidth = chunkWidth;
	}

	public int getChunkHeight() {
		return chunkHeight;
	}

	public void setChunkHeight(int chunkHeight) {
		this.chunkHeight = chunkHeight;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	
}
