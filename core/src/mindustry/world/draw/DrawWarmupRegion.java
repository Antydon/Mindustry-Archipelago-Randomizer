package mindustry.world.draw;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.world.*;

import static mindustry.Vars.randomizer;

public class DrawWarmupRegion extends DrawBlock{
    public float sinMag = 0.6f, sinScl = 8f;
    public Color color = Color.valueOf("ff9b59");
    public TextureRegion region;

    @Override
    public void rescale(Boolean isRescaled, float randomScale) {
        region.scale = isRescaled ? randomScale : 1f;
    }

    @Override
    public void reloadTextures(Block block) {
        block.textureRegions.put("warmUp", new TextureRegion(region));
    }

    @Override
    public void drawPlan(Block block, BuildPlan plan, Eachable<BuildPlan> list){

    }

    @Override
    public void draw(Building build){
        Draw.color(color);
        Draw.alpha(build.warmup() * (1f - sinMag) + Mathf.absin(Time.time, sinScl, sinMag) * build.warmup());
        if(randomizer.worldState.options.getRandomizeBlocksSize() && build.block.isRedrawned) {
            Draw.rect(build.block.textureRegions.get("warmUp"), build.x, build.y);
        } else {
            Draw.rect(region, build.x, build.y);
        }
        Draw.reset();
    }

    @Override
    public void load(Block block){
        region = Core.atlas.find(block.name + "-top");
        super.load(block);
    }
}
