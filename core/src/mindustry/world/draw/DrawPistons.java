package mindustry.world.draw;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.world.*;

import static mindustry.Vars.randomizer;

public class DrawPistons extends DrawBlock {
    public float sinMag = 4f, sinScl = 6f, sinOffset = 50f, sideOffset = 0f, lenOffset = -1f,
            horiOffset = 0f, angleOffset = 0f;
    public int sides = 4;
    public String suffix = "-piston";
    public TextureRegion region1, region2, regiont, iconRegion;

    @Override
    public void drawPlan(Block block, BuildPlan plan, Eachable<BuildPlan> list) {
        if (iconRegion.found()) {
            Draw.rect(iconRegion, plan.drawx(), plan.drawy());
        }
    }

    @Override
    public void rescale(Boolean isRescaled, float randomScale) {
        regiont.scale = isRescaled ? randomScale : 1f;
        region1.scale = isRescaled ? randomScale : 1f;
        region2.scale = isRescaled ? randomScale : 1f;
    }

    @Override
    public void reloadTextures(Block block) {
        if(regiont != null && region1 != null && region2 != null) {
            block.textureRegions.put("pistonRegiont", new TextureRegion(regiont));
            block.textureRegions.put("pistonRegion1", new TextureRegion(region1));
            block.textureRegions.put("pistonRegion2", new TextureRegion(region2));
        } else
            block.failedTexturesReload = true;

    }

    @Override
    public void draw(Building build) {
        for (int i = 0; i < sides; i++) {
            float len = Mathf.absin(build.totalProgress() + sinOffset + sideOffset * sinScl * i, sinScl, sinMag) + lenOffset;
            float angle = angleOffset + i * 360f / sides;
            TextureRegion reg;
            if(randomizer.worldState.options.getRandomizeBlocksSize() && build.block.isRedrawned) {
                if (build.block.failedTexturesReload){
                    reloadTextures(build.block);
                }
                var texRegions = build.block.textureRegions;
                reg = texRegions.get("pistonRegiont").found() && (Mathf.equal(angle,
                                315) || Mathf.equal(angle,135)) ?
                        texRegions.get("pistonRegiont") : angle >= 135 && angle < 315 ?
                        texRegions.get("pistonRegion2") : texRegions.get("pistonRegion1");
            } else {
                reg = regiont.found() && (Mathf.equal(angle, 315) || Mathf.equal(angle, 135)) ? regiont : angle >= 135 && angle < 315 ? region2 : region1;
            }
            if (Mathf.equal(angle, 315)) {
                Draw.yscl = -1f;
            }

            Tmp.v1.trns(angle, len, -horiOffset);
            Draw.rect(reg, build.x + Tmp.v1.x, build.y + Tmp.v1.y, angle);

            Draw.yscl = 1f;
        }

    }

    @Override
    public void load(Block block) {
        super.load(block);

        region1 = Core.atlas.find(block.name + suffix + "0", block.name + suffix);
        region2 = Core.atlas.find(block.name + suffix + "1", block.name + suffix);
        regiont = Core.atlas.find(block.name + suffix + "-t");
        iconRegion = Core.atlas.find(block.name + suffix + "-icon");
        super.load(block);
    }

    @Override
    public TextureRegion[] icons(Block block) {
        return new TextureRegion[]{iconRegion};
    }
}
