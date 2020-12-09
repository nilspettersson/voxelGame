package noise;

import org.joml.Vector3f;

import niles.lwjgl.entity.Entity;
import niles.lwjgl.loop.Game;
import niles.lwjgl.loop.Scene;
import niles.lwjgl.npsl.MeshShader;
import niles.lwjgl.npsl.Shader;
import voxelGame.Biome;

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
				int size = 200;
				
				Noise noise = new Noise(0);
				
				for(int x = 0; x < size; x++) {
					for(int y = 0; y < size; y++) {
						Entity e = new Entity(0, shader);
						e.getGeometry().createFace(x * 2f, y * 2f, -280);
						e.bindGeometry();
						
						/*float value = noise.getBiomeAt(x, y, 2f);
						
						Vector3f color = new Vector3f();
						if(Biome.getBiome(value) == Biome.GRASS) {
							color.set(0, 1, 0);
						}
						else if(Biome.getBiome(value) == Biome.ROCK) {
							color.set(0.8f, 0.8f, 0.8f);
						}
						
						e.getMaterial().setProperty("value", new Vector3f(color));*/
						
						float value = noise.treeNoise(x, y, 0.01f);
						/*if(value > 0.95) {
							value = 1;
						}
						else {
							value = 0;
						}*/
						//value = noise.getHeightAt(x, y, value);
						
						e.getMaterial().setProperty("value", new Vector3f(value));
						
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
