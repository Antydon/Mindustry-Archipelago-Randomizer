package mindustry.world.draw;

import arc.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.blocks.production.Pump.*;

import static mindustry.Vars.randomizer;

public class DrawPumpLiquid extends DrawBlock{
    public TextureRegion liquid;

    @Override
    public void rescale(Boolean isRescaled, float randomScale) {
        liquid = isRescaled ? new TextureRegion(liquid) : null;
    }

    @Override
    public void reloadTextures(Block block) {
        block.textureRegions.put("pumpLiquid", new TextureRegion(liquid));
    }

    @Override
    public void draw(Building build){
        if(!(build instanceof PumpBuild pump) || pump.liquidDrop == null) return;

        if(randomizer.worldState.options.getRandomizeBlocksSize() && build.block.isRedrawned) {
            Drawf.liquid(build.block.textureRegions.get("pumpLiquid"), build.x, build.y,
                    build.liquids.get(pump.liquidDrop) / build.block.liquidCapacity, pump.liquidDrop.color);
        } else {
            Drawf.liquid(liquid, build.x, build.y, build.liquids.get(pump.liquidDrop) / build.block.liquidCapacity, pump.liquidDrop.color);
        }
    }

    @Override
    public void load(Block block){
        liquid = Core.atlas.find(block.name + "-liquid");
        super.load(block);
    }
}
