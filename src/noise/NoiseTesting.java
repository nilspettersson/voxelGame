package noise;

import org.joml.Vector3f;

import niles.lwjgl.entity.Entity;
import niles.lwjgl.loop.Game;
import niles.lwjgl.loop.Scene;
import niles.lwjgl.npsl.MeshShader;
import niles.lwjgl.npsl.Shader;

public class NoiseTesting extends Game {

	public static void main(String[] args) {
		new NoiseTesting();
	}

	@Override
	public void init() {
		addScene(new Scene(getWindow()) {
			
			Shader shader = new MeshShader("noise.glsl");
			
			
			@Override
			protected void onload() {
				int size = 128;
				
				Noise noise = new Noise();
				
				for(int x = 0; x < size; x++) {
					for(int y = 0; y < size; y++) {
						Entity e = new Entity(0, shader);
						e.getGeometry().createFace(x * 2f, y * 2f, -180);
						e.bindGeometry();
						
						float value = noise.getNoiseAt(x, y);
						
						e.getMaterial().setProperty("value", new Vector3f(value, value, value));
						
						addEntityToScene(e);
					}
				}
				
				
				getCamera().getPosition().x += size;
				getCamera().getPosition().y += size;
			}
			
			@Override
			protected void update() {
				simpleCameraMovement(0.3f);
				simpleCameraRotation(1);
				
			}
			
			
		});
	}

}
