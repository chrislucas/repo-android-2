package com.br.agsl.shaders

/*
    https://www.linkedin.com/posts/ardakazanci_jetpackcompose-androidprogramming-kotlin-ugcPost-7435372874934177793-P8yc/?utm_source=share&utm_medium=member_android&rcm=ACoAAAucV48BgdbCBoMmXrArsYNH-OL_jFGhzfk
    https://gist.github.com/ardakazanci/0bd693b9a8f5907966a507f5868f100b
 */
private const val LiquidGlassShaderSource = """
const float PI = 3.1415926;
const float FORCE = 0.08;
const float THICKNESS = 0.075;
const float FEATHERING = 0.1;
const float ABERRATION_OFFSET = 0.006;
const float FLASH_INTENSITY = 3.0;
const float REFRACTION_STRENGTH = 0.03;
const float REFRACTION_CHROMA = 0.010;
const float SPECULAR_INTENSITY = 0.55;
const float FRESNEL_INTENSITY = 0.22;
uniform shader iImage1;
uniform float2 iResolution;
uniform float2 iImageResolution;
uniform float2 iCenter;
uniform float iTime;
float2 centerScaleUV(float2 uv, float2 factor) {
    return (uv - 0.5) * factor + 0.5;
}
float easeOutSine(float t) {
    return sin((t * PI) * 0.5);
}
half4 sampleImage(float2 uv) {
    float2 imgRes = max(iImageResolution.xy, float2(1.0));
    float2 px = clamp(uv, 0.0, 1.0) * imgRes;
    return iImage1.eval(px);
}
half4 main(float2 fragCoord) {
    if (iImageResolution.x < 2.0 || iImageResolution.y < 2.0) {
        return half4(1.0, 0.0, 1.0, 1.0);
    }
    float2 screenUV = fragCoord / iResolution.xy;
    if (iTime < 0.0) {
        return sampleImage(screenUV);
    }
    float progress = easeOutSine(clamp(iTime, 0.0, 1.0));
    float aspectRatio = iResolution.x / iResolution.y;
    float2 scalingFactor = float2(aspectRatio, 1.0);
    scalingFactor *= 2.0;
    scalingFactor *= 1.0 - THICKNESS - FEATHERING;
    scalingFactor /= sqrt(aspectRatio * aspectRatio + 1.0);
    float2 scaledUV = centerScaleUV(screenUV, scalingFactor);
    float2 corner0 = centerScaleUV(float2(0.0, 0.0), scalingFactor);
    float2 corner1 = centerScaleUV(float2(1.0, 0.0), scalingFactor);
    float2 corner2 = centerScaleUV(float2(0.0, 1.0), scalingFactor);
    float2 corner3 = centerScaleUV(float2(1.0, 1.0), scalingFactor);
    float maxDist = max(
        max(length(corner0 - iCenter), length(corner1 - iCenter)),
        max(length(corner2 - iCenter), length(corner3 - iCenter))
    );
    float pos = progress * maxDist * 1.06;
    float2 delta = scaledUV - iCenter;
    float distToCenter = length(delta);
    float2 dir = (distToCenter > 1e-5) ? (delta / distToCenter) : float2(0.0);
    float innerBound = smoothstep(pos - THICKNESS - FEATHERING, pos - THICKNESS, distToCenter);
    float outerBound = smoothstep(pos - FEATHERING, pos, distToCenter);
    float shapeMask = innerBound - outerBound;
    float2 displacement = dir * FORCE;
    float ringCenter = pos - THICKNESS * 0.5;
    float ringHalf = max(THICKNESS * 0.5, 1e-4);
    float ringDist = abs(distToCenter - ringCenter);
    float ringProfile = 1.0 - smoothstep(0.0, ringHalf + FEATHERING, ringDist);
    float side = sign(distToCenter - ringCenter);
    float slope = side * ringProfile * 2.0;
    float3 N = normalize(float3(dir * slope, 1.0));
    float3 V = float3(0.0, 0.0, 1.0);
    float3 L = normalize(float3(-0.5, -0.35, 1.0));
    float2 refractOffset = dir * (REFRACTION_STRENGTH * slope) * shapeMask;
    float r = sampleImage(screenUV - (displacement + refractOffset - (ABERRATION_OFFSET + REFRACTION_CHROMA) * dir) * shapeMask).r;
    float g = sampleImage(screenUV - (displacement + refractOffset) * shapeMask).g;
    float b = sampleImage(screenUV - (displacement + refractOffset + (ABERRATION_OFFSET + REFRACTION_CHROMA) * dir) * shapeMask).b;
    float3 color = float3(r, g, b);
    float3 R = reflect(-L, N);
    float spec = pow(max(dot(R, V), 0.0), 64.0) * ringProfile * SPECULAR_INTENSITY;
    float fresnel = pow(1.0 - max(dot(N, V), 0.0), 3.0) * FRESNEL_INTENSITY * shapeMask;
    color += spec + fresnel;
    float flashOpacity = pow(1.0 - pos, 4.0);
    float flashMask = 1.0 - innerBound;
    color += FLASH_INTENSITY * color * flashMask * flashOpacity;
    return half4(half3(color), 1.0);
}
"""