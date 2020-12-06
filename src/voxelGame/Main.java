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
		//super(720, 480, false);
	}

	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void init() {
		setBackgroundColor(new Vector4f(0.6f, 0.7f, 1, 1));
		
		addScene(new Scene(getWindow()) {
			
			
			@Override
			public void onload() {
				Shader shader = new MeshShader("block.glsl");
				
				ChunkManager chunks = new ChunkManager(16, 256);
				chunks.addChunk(0, 0);
				chunks.addChunk(1, 0);
				chunks.addChunk(1, 1);
				chunks.addChunk(0, 1);
				chunks.addChunk(-1, 0);
				chunks.addChunk(-1, 1);
				chunks.addChunk(0, 0+1);
				chunks.addChunk(1, 0+1);
				chunks.addChunk(1, 1+1);
				chunks.addChunk(0, 1+1);
				chunks.addChunk(-1, 0+1);
				chunks.addChunk(-1, 1+1);
				
				
				for(int i = 0; i < chunks.getChunks().size(); i++) {
					addEntityToScene(chunks.getChunks().get(i).getEntity());
				}
				
				addLight(new Vector3f(40, 100, 40), new Vector3f(0.6f, 0.6f, 1), 600);
				
				getCamera().getPosition().add(new Vector3f(0, 40, 26));
			}
			
			@Override
			public void update() {
				simpleCameraRotation(1f);
				simpleCameraMovement(0.1f);
				
			}
			
			
		});
		
	}
}
