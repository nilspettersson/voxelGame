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
	
	Shader shader;
	
	

	@Override
	public void init() {
		shader = new MeshShader("block.glsl");
		
		addScene(new Scene(getWindow()) {
			
			
			@Override
			public void onload() {
				Texture texture = new Texture("res/atlas.png");
				texture.setSpriteWidth(16);
				texture.setSpriteHeight(16);
				
				Geometry mesh = new Geometry(2000);
				mesh.createFaceBack(0, 0, 0, texture, 0, 1);
				mesh.createFaceFront(0, 0, 0, texture, 0, 0);
				mesh.createFaceLeft(0, 0, 0, texture, 0, 0);
				mesh.createFaceRight(0, 0, 0, texture, 0, 0);
				mesh.createFaceUp(0, 0, 0, texture, 0, 0);
				mesh.createFaceDown(0, 0, 0, texture, 0, 0);
				mesh.updateVertices();
				mesh.updateIndices();
				
				Entity entity = new Entity(0, shader);
				entity.setGeometry(mesh);
				
				addEntityToScene(entity);
				
				addLight(new Vector3f(4, 8, 4), new Vector3f(0.6f, 0.6f, 1), 16);
			}
			
			@Override
			public void update() {
				simpleCameraRotation(1f);
				simpleCameraMovement(0.1f);
				
			}
			
			
		});
		
	}

}
