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
				
				/*Texture texture = new Texture("res/atlas.png");
				texture.setSpriteWidth(16);
				texture.setSpriteHeight(16);
				
				Geometry mesh = new Geometry(36);
				mesh.createFaceBack(0, 0, 0, texture, 0, 1);
				mesh.createFaceFront(0, 0, 0, texture, 0, 0);
				mesh.createFaceLeft(0, 0, 0, texture, 0, 0);
				mesh.createFaceRight(0, 0, 0, texture, 0, 0);
				mesh.createFaceUp(0, 0, 0, texture, 0, 0);
				mesh.createFaceDown(0, 0, 0, texture, 0, 0);
				mesh.updateVertices();
				mesh.updateIndices();*/
				
				ChunkManager chunks = new ChunkManager(16, 1);
				chunks.addChunk(0, 0);
				
				Entity entity = new Entity(0, shader);
				entity.setGeometry(chunks.getChunks().get(0).getMesh());
				
				addEntityToScene(entity);
				
				addLight(new Vector3f(40, 80, 40), new Vector3f(0.6f, 0.6f, 1), 400);
				
				getCamera().getPosition().add(new Vector3f(0, 10, 10));
			}
			
			@Override
			public void update() {
				simpleCameraRotation(1f);
				simpleCameraMovement(0.1f);
				
			}
			
			
		});
		
	}
}
