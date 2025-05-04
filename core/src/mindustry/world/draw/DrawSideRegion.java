package mindustry.world.draw;

import arc.*;
import arc.graphics.g2d.*;
import arc.util.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.world.*;

import static mindustry.Vars.randomizer;

public class DrawSideRegion extends DrawBlock{
    public TextureRegion top1, top2;

    @Override
    public void rescale(Boolean isRescaled, float randomScale) {
        top1.scale = isRescaled ? randomScale : 1f;
        top2.scale = isRescaled ? randomScale : 1f;
    }

    @Override
    public void reloadTextures(Block block) {
        block.textureRegions.put("sideTop", new TextureRegion(top1));
        block.textureRegions.put("sideTop2", new TextureRegion(top2));
    }

    @Override
    public void draw(Building build){
        if(randomizer.worldState.options.getRandomizeBlocksSize() && build.block.isRedrawned) {
            Draw.rect(build.rotation > 1 ? build.block.textureRegions.get("siteTop2") :
                            build.block.textureRegions.get("siteTop1"),build.x, build.y,
                    build.rotdeg());
        } else {
            Draw.rect(build.rotation > 1 ? top2 : top1, build.x, build.y, build.rotdeg());
        }
    }

    @Override
    public void drawPlan(Block block, BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(plan.rotation > 1 ? top2 : top1, plan.drawx(), plan.drawy(), plan.rotation * 90);
    }

    @Override
    public void load(Block block){
        top1 = Core.atlas.find(block.name + "-top1");
        top2 = Core.atlas.find(block.name + "-top2");
        super.load(block);
    }

    @Override
    public TextureRegion[] icons(Block block){
        return new TextureRegion[]{top1};
    }

}
