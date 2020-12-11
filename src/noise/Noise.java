package noise;

import simplexNoise.OpenSimplexNoise;

public class Noise {
	
	OpenSimplexNoise noise;
	
	public Noise(long seed) {
		noise = new OpenSimplexNoise(seed);
	}
	
	public float getHeightAt(float x, float y, float size) {
		x *= size;
		y *= size;
		float value = (float) noise.eval(x / 300, y / 300) * 1.2f;
		value += (float) noise.eval((x + 200) / 50, (y + 200) / 50) * 0.4f;
		value += (float) noise.eval((x + 10000) / 20, (y + 10000) / 20) * 0.1;
		value += (float) noise.eval((x + 30000) / 4, (y + 30000) / 4) * 0.03;
		
		float rivers = ((float) noise.eval(x / 20, y / 20) * 0.2f);
		rivers = (float) Math.pow(rivers, 1);
		rivers = Math.abs(rivers);
		rivers = Math.min(rivers, 0.3f);
		
		value += rivers;
		
		
		value = Math.abs(value);
		value *= 0.5;
		value += 1;
		value = (float) Math.pow(value, 5);
		return Math.max(value, 0);
	}
	
	public float getCaveAt(float x, float y, float z, float size) {
		x *= size;
		y *= size;
		float value = (float) noise.eval(x / 20, y / 20, z / 20);
		//value += (float) noise.eval(x / 10, y / 10, z / 10) * 0.16;
		
		
		value = Math.abs(value);
		/*value *= 0.5;
		value += 1;*/
		value = (float) Math.pow(value, 3);
		return Math.max(value, 0);
	}
	
	public float getBiomeAt(float x, float y, float size) {
		x *= size;
		y *= size;
		float value = (float) noise.eval(x / 100, y / 100);
		
		value *= 0.5;
		value += 0.5;
		value = (float) Math.pow(value, 3);
		return Math.max(value, 0);
	}
	
	public float treeNoise(float x, float y, float treeAmount) {
		float value = (float) noise.eval(x / 0.1, y / 0.1);
		
		value *= 0.5;
		value += 0.5;
		value = (float) Math.pow(value, 1);
		
		if(value > 1 - treeAmount) {
			value = 1;
		}
		else {
			value = 0;
		}
		
		return Math.max(value, 0);
	}
	

}
