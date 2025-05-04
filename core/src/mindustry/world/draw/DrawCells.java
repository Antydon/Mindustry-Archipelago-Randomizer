package mindustry.world.draw;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;

import static mindustry.Vars.randomizer;

public class DrawCells extends DrawBlock{
    public TextureRegion middle;

    public Color color = Color.white.cpy(), particleColorFrom = Color.black.cpy(), particleColorTo = Color.black.cpy();
    public int particles = 12;
    public float range = 4f, recurrence = 2f, radius = 1.8f, lifetime = 60f * 3f;

    @Override
    public void rescale(Boolean isRescaled, float randomScale){
        middle.scale = isRescaled ? randomScale: 1f;
    }

    @Override
    public void reloadTextures(Block block) {
        block.textureRegions.put("middle", new TextureRegion(middle));
    }

    @Override
    public void draw(Building build){
        if(randomizer.worldState.options.getRandomizeBlocksSize() && build.block.isRedrawned) {
            Drawf.liquid(build.block.textureRegions.get("middle"), build.x, build.y,
                    build.warmup(), color);
        } else {
            Drawf.liquid(middle, build.x, build.y, build.warmup(), color);
        }

        if(build.warmup() > 0.001f){
            rand.setSeed(build.id);
            for(int i = 0; i < particles; i++){
                float offset = rand.nextFloat() * 999999f;
                float x = rand.range(range), y = rand.range(range);
                float fin = 1f - (((Time.time + offset) / lifetime) % recurrence);
                float ca = rand.random(0.1f, 1f);
                float fslope = Mathf.slope(fin);

                if(fin > 0){
                    Draw.color(particleColorFrom, particleColorTo, ca);
                    Draw.alpha(build.warmup());

                    Fill.circle(build.x + x, build.y + y, fslope * radius);
                }
            }
        }

        Draw.color();
    }

    @Override
    public void load(Block block){
        middle = Core.atlas.find(block.name + "-middle");
        super.load(block);
    }
}
