package mindustry.world.draw;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;

import static mindustry.Vars.randomizer;

public class DrawWeave extends DrawBlock{
    public TextureRegion weave;


    @Override
    public void rescale(Boolean isRescaled, float randomScale){
        weave.scale = isRescaled ? randomScale: 1f;
    }

    @Override
    public void reloadTextures(Block block) {
        block.textureRegions.put("weave", new TextureRegion(weave));
    }

    @Override
    public void draw(Building build){
        if(randomizer.worldState.options.getRandomizeBlocksSize() && build.block.isRedrawned) {
            Draw.rect(build.block.textureRegions.get("weave"), build.x, build.y);
        } else {
            Draw.rect(weave, build.x, build.y, build.totalProgress());
        }

        Draw.color(Pal.accent);
        Draw.alpha(build.warmup());

        Lines.lineAngleCenter(
        build.x + Mathf.sin(build.totalProgress(), 6f, Vars.tilesize / 3f * build.block.size),
        build.y,
        90,
        build.block.size * Vars.tilesize / 2f);

        Draw.reset();
    }

    @Override
    public TextureRegion[] icons(Block block){
        return new TextureRegion[]{weave};
    }

    @Override
    public void load(Block block){
        weave = Core.atlas.find(block.name + "-weave");
        super.load(block);
    }
}
