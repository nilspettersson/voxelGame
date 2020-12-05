package voxelGame;

import org.joml.Vector3f;

import niles.lwjgl.loop.Game;
import niles.lwjgl.loop.Scene;
import niles.lwjgl.npsl.MeshShader;
import niles.lwjgl.npsl.Shader;

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
				
				
				addLight(new Vector3f(0, 8, 0), new Vector3f(0.6f, 0.6f, 1), 6);
			}
			
			@Override
			public void update() {
				simpleCameraRotation(1f);
				simpleCameraMovement(0.04f);
				
			}
			
			
		});
		
	}

}
