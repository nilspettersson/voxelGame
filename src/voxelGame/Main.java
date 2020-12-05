package voxelGame;

import org.joml.Vector3f;

import niles.lwjgl.entity.Entity;
import niles.lwjgl.entity.Geometry;
import niles.lwjgl.loop.Game;
import niles.lwjgl.loop.Scene;
import niles.lwjgl.npsl.MeshShader;
import niles.lwjgl.npsl.Shader;
import niles.lwjgl.util.Texture;

public class Main extends Game{

	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void init() {
		
		addScene(new Scene(getWindow()) {
			
			
			@Override
			public void onload() {
				Shader shader = new MeshShader("block.glsl");
				
				ChunkManager chunks = new ChunkManager(16, 16);
				chunks.addChunk(0, 0);
				
				Entity entity = new Entity(0, shader);
				entity.setGeometry(chunks.getChunks().get(0).getMesh());
				
				addEntityToScene(entity);
				
				addLight(new Vector3f(40, 100, 40), new Vector3f(0.6f, 0.6f, 1), 400);
				
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
