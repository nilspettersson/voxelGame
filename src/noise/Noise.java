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
		float value = (float) noise.eval(x / 100, y / 100);
		value += (float) noise.eval((x + 10000) / 20, (y + 10000) / 20) * 0.3;
		value += (float) noise.eval((x + 30000) / 4, (y + 30000) / 4) * 0.02;
		
		
		value = Math.abs(value);
		value *= 0.5;
		value += 1;
		value = (float) Math.pow(value, 5);
		return Math.max(value, 0);
	}
	
	public float getBiomeAt(float x, float y, float size) {
		x *= size;
		y *= size;
		float value = (float) noise.eval(x / 100, y / 100);
		
		value *= 0.5;
		value += 0.5;
		value = (float) Math.pow(value, 4);
		return Math.max(value, 0);
	}

}
