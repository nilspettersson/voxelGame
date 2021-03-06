#include lib/mesh.glsl;

uniforms{

}

fragment{
	vec4 normalMap = bump(textureId, 0.1); 
	vec4 diffuse = diffuse(mix(texture, color, 0), normalMap);
	vec4 glossy = glossy(vec4(1), 0.10, normalMap);
	vec4 output = mix(diffuse, glossy, 0.2);
	output.xyz = max(output.xyz, texture.xyz * 0.7);
	output.xyz *= color.x;
	return output;
}