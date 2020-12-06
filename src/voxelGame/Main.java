package voxelGame;

import org.joml.Vector3f;
import org.joml.Vector4f;

import niles.lwjgl.entity.Entity;
import niles.lwjgl.entity.Geometry;
import niles.lwjgl.loop.Game;
import niles.lwjgl.loop.Scene;
import niles.lwjgl.npsl.MeshShader;
import niles.lwjgl.npsl.Shader;
import niles.lwjgl.util.Texture;

public class Main extends Game{
	
	public Main() {
		super(720, 480, false);
	}

	public static void main(String[] args) {
		new Main();
	}
	
	ChunkManager chunks;

	@Override
	public void init() {
		setBackgroundColor(new Vector4f(0.6f, 0.7f, 1, 1));
		
		addScene(new Scene(getWindow()) {
			
			
			@Override
			public void onload() {
				Shader shader = new MeshShader("block.glsl");
				
				chunks = new ChunkManager(16, 256);
				
				
				for(int i = 0; i < chunks.getChunks().size(); i++) {
					addEntityToScene(chunks.getChunks().get(i).getEntity());
				}
				
				addLight(new Vector3f(40, 1000, 800), new Vector3f(0.6f, 0.6f, 1), 180000);
				
				getCamera().getPosition().add(new Vector3f(0, 40, 0));
			}
			
			@Override
			public void update() {
				simpleCameraRotation(1.5f);
				simpleCameraMovement(2f);
				
				int playerX = (int) Math.floor(getCamera().getPosition().x / (2 * 16));
				int playerZ = (int) Math.floor(getCamera().getPosition().z / (2 * 16));
				
				int renderDistance = 4;
				for(int x = -renderDistance / 2; x < renderDistance / 2; x++) {
					for(int z = -renderDistance / 2; z < renderDistance / 2; z++) {
						if(!chunks.contains(playerX + x, playerZ + z)) {
							chunks.addChunk(playerX + x, playerZ + z);
							addEntityToScene(chunks.getChunks().get(chunks.getChunks().size() - 1).getEntity());
						}
					}
				}
				
			}
			
			
		});
		
	}
}
