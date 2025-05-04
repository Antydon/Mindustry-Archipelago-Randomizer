package mindustry.world.draw;

import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.Block;

public class DrawShape extends DrawBlock{
    public Color color = Pal.accent.cpy();
    public int sides = 4;
    public float radius = 2f, timeScl = 1f, layer = -1f, x, y;
    public boolean useWarmupRadius = false;

    float originalRadius = 0f;

    @Override
    public void rescale(Boolean isRescaled, float randomScale) {
        if(originalRadius == 0f) originalRadius = radius;
        radius = isRescaled ? randomScale * originalRadius : originalRadius;
    }

    @Override
    public void reloadTextures(Block block) { }

    @Override
    public void draw(Building build){
        float pz = Draw.z();
        if(layer > 0) Draw.z(layer);

        Draw.color(color);
        Fill.poly(build.x + x, build.y + y, sides, useWarmupRadius ? radius * build.warmup() : radius, build.totalProgress() * timeScl);

        Draw.reset();
        Draw.z(pz);
    }
}
