uniforms{
	vec3 value;
}

fragment{
	return new vec4(value, 1);
}