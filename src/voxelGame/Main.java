package voxelGame;

import org.joml.Vector3f;
import org.joml.Vector4f;
import niles.lwjgl.loop.Game;
import niles.lwjgl.loop.Scene;
import niles.lwjgl.npsl.MeshShader;
import niles.lwjgl.npsl.Shader;

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
				
				addLight(new Vector3f(40, 19000, 800), new Vector3f(1, 1, 0.9f), 10000000);
				getCamera().getPosition().add(new Vector3f(0, 40, 0));
				
			}
			
			@Override
			public void update() {
				
				int playerX = (int) Math.floor(getCamera().getPosition().x / (2 * 16));
				int playerZ = (int) Math.floor(getCamera().getPosition().z / (2 * 16));
				
				GenerateChuncks(playerX, playerZ, 14);
				removeChuncks(playerX, playerZ, 18);
				
				
				
				
				//simpleCameraRotation(1.5f);
				simpleCameraMovement(1.6f);
			}
			
			public void GenerateChuncks(int playerX, int playerZ, int renderDistance) {
				for(int x = -renderDistance / 2; x < renderDistance / 2; x++) {
					for(int z = -renderDistance / 2; z < renderDistance / 2; z++) {
						if(!chunks.contains(playerX + x, playerZ + z)) {
							chunks.addChunk(playerX + x, playerZ + z);
							addEntityToScene(chunks.getChunks().get(chunks.getChunks().size() - 1).getEntity());
							addtransparentEntityToScene(chunks.getChunks().get(chunks.getChunks().size() - 1).getWater());
							//return;
						}
					}
				}
			}
			
			public void removeChuncks(int playerX, int playerZ, int removeDistance) {
				for(int i = 0; i < chunks.getChunks().size(); i++) {
					if((Math.abs(chunks.getChunks().get(i).getColumn() - playerX) + Math.abs(chunks.getChunks().get(i).getRow() - playerZ)) / 2 > removeDistance / 2) {
						delete(chunks.getChunks().get(i).getEntity());
						delete(chunks.getChunks().get(i).getWater());
						chunks.getChunks().remove(i);
					}
				}
			}
			
			
		});
		
	}
	
	
}
