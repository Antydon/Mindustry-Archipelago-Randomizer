package mindustry.world.draw;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.blocks.heat.*;

import static mindustry.Vars.randomizer;

public class DrawHeatOutput extends DrawBlock {
    public TextureRegion heat, glow, top1, top2;

    public Color heatColor = new Color(1f, 0.22f, 0.22f, 0.8f);
    public float heatPulse = 0.3f, heatPulseScl = 10f, glowMult = 1.2f;

    public int rotOffset = 0;
    public boolean drawGlow = true;

    public DrawHeatOutput() {
    }

    public DrawHeatOutput(int rotOffset, boolean drawGlow) {
        this.rotOffset = rotOffset;
        this.drawGlow = drawGlow;
    }

    @Override
    public void rescale(Boolean isRescaled, float randomScale) {
        heat.scale = isRescaled ? randomScale : 1f;
        glow.scale = isRescaled ? randomScale : 1f;
        top1.scale = isRescaled ? randomScale : 1f;
        top2.scale = isRescaled ? randomScale : 1f;
    }

    @Override
    public void reloadTextures(Block block) {
        block.textureRegions.put("heatOutput", new TextureRegion(heat));
        block.textureRegions.put("glowOutput", new TextureRegion(glow));
        block.textureRegions.put("top1Output", new TextureRegion(top1));
        block.textureRegions.put("top2Output", new TextureRegion(top2));
    }

    @Override
    public void draw(Building build) {
        if(randomizer.worldState.options.getRandomizeBlocksSize() && build.block.isRedrawned) {
           drawRandomizer(build);
        } else {
            float rotdeg = (build.rotation + rotOffset) * 90;
            Draw.rect(Mathf.mod((build.rotation + rotOffset), 4) > 1 ? top2 : top1, build.x, build.y, rotdeg);

            if (build instanceof HeatBlock heater && heater.heat() > 0) {
                Draw.z(Layer.blockAdditive);
                Draw.blend(Blending.additive);
                Draw.color(heatColor, heater.heatFrac() * (heatColor.a * (1f - heatPulse + Mathf.absin(heatPulseScl, heatPulse))));
                if (heat.found()) {
                    Draw.rect(heat, build.x, build.y, rotdeg);
                }
                Draw.color(Draw.getColor().mul(glowMult));
                if (drawGlow && glow.found()) {
                    Draw.rect(glow, build.x, build.y);
                }
                Draw.blend();
                Draw.color();
            }
        }
    }

    private void drawRandomizer(Building build) {
        float rotdeg = (build.rotation + rotOffset) * 90;
        Draw.rect(Mathf.mod((build.rotation + rotOffset), 4) > 1 ? build.block.textureRegions.get("top2Output")
                : build.block.textureRegions.get("top1Output"), build.x, build.y, rotdeg);

        if (build instanceof HeatBlock heater && heater.heat() > 0) {
            Draw.z(Layer.blockAdditive);
            Draw.blend(Blending.additive);
            Draw.color(heatColor, heater.heatFrac() * (heatColor.a * (1f - heatPulse + Mathf.absin(heatPulseScl, heatPulse))));
            if (build.block.textureRegions.get("heatOutput").found()) {
                Draw.rect(build.block.textureRegions.get("heatOutput"), build.x, build.y, rotdeg);
            }
            Draw.color(Draw.getColor().mul(glowMult));
            if (drawGlow && build.block.textureRegions.get("glowOutput").found()) {
                Draw.rect(build.block.textureRegions.get("glowOutput"), build.x, build.y);
            }
            Draw.blend();
            Draw.color();
        }
    }

    @Override
    public void drawPlan(Block block, BuildPlan plan, Eachable<BuildPlan> list) {
        Draw.rect(Mathf.mod((plan.rotation + rotOffset), 4) > 1 ? top2 : top1, plan.drawx(),
                plan.drawy(), (plan.rotation + rotOffset) * 90);
    }

    @Override
    public void load(Block block) {
        heat = Core.atlas.find(block.name + "-heat");
        glow = Core.atlas.find(block.name + "-glow");
        top1 = Core.atlas.find(block.name + "-top1");
        top2 = Core.atlas.find(block.name + "-top2");
        super.load(block);
    }

    //TODO currently no icons due to concerns with rotation

}