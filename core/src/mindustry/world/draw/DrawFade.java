package mindustry.world.draw;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.world.*;

import static mindustry.Vars.randomizer;

public class DrawFade extends DrawBlock{
    public String suffix = "-top";
    public float alpha = 0.6f, scale = 3f;
    public TextureRegion region;

    @Override
    public void rescale(Boolean isRescaled, float randomScale){
        region.scale = isRescaled ? randomScale: 1f;
    }

    @Override
    public void reloadTextures(Block block) {
        block.textureRegions.put("fadeRegion", new TextureRegion(region));
    }

    @Override
    public void draw(Building build){
        Draw.alpha(Mathf.absin(build.totalProgress(), scale, alpha) * build.warmup());
        if(randomizer.worldState.options.getRandomizeBlocksSize() && build.block.isRedrawned) {
            Draw.rect(build.block.textureRegions.get("fadeRegion"), build.x, build.y);
        } else {
            Draw.rect(region, build.x, build.y);
        }
        Draw.reset();
    }

    @Override
    public void load(Block block){
        region = Core.atlas.find(block.name + suffix);
        super.load(block);
    }
}
