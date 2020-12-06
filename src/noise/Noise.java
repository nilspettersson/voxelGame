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
		value += (float) noise.eval(x / 40, y / 40) * 0.35;
		value += (float) noise.eval(x / 12, y / 12) * 0.1;
		
		value *= 0.5;
		value += 1;
		value = (float) Math.pow(value, 4);
		return Math.max(value, 0);
	}
	
	public float getBiomeAt(float x, float y, float size) {
		x *= size;
		y *= size;
		float value = (float) noise.eval(x / 100, y / 100);
		//value += (float) noise.eval(x / 40, y / 40) * 0.35;
		//value += (float) noise.eval(x / 12, y / 12) * 0.1;
		
		value *= 0.5;
		value += 0.5;
		value = (float) Math.pow(value, 4);
		return Math.max(value, 0);
	}

}
