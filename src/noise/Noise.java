package noise;

import simplexNoise.OpenSimplexNoise;

public class Noise {
	
	OpenSimplexNoise noise;
	
	public Noise() {
		noise = new OpenSimplexNoise(0);
	}
	
	public float getNoiseAt(float x, float y) {
		
		float value = (float) noise.eval(x / 10, y / 10);
		value *= 0.5;
		value += 0.5;
		//System.out.println(value);
		return value;
	}

}
