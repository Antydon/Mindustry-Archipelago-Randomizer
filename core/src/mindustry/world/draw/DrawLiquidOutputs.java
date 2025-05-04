package mindustry.world.draw;

import arc.*;
import arc.graphics.g2d.*;
import arc.util.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.world.*;
import mindustry.world.blocks.production.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static mindustry.Vars.randomizer;

/**
 * This must be used in conjunction with another DrawBlock; it only draws outputs.
 */
public class DrawLiquidOutputs extends DrawBlock {
    public TextureRegion[][] liquidOutputRegions;
    public List<List<TextureRegion>> lstLiquidOutputs = new ArrayList<>();

    @Override
    public void rescale(Boolean isRescaled, float randomScale) {
        for (int i = 0; i < liquidOutputRegions.length; i++) {
            for (int j = 0; j < liquidOutputRegions[i].length; j++) {
                liquidOutputRegions[i][i].scale = isRescaled ? randomScale : 1f;
            }
        }
    }

    @Override
    public void reloadTextures(Block block) {
        for (int i = 0; i < liquidOutputRegions.length; i++) {
            TextureRegion[] liquidOutputRegion = liquidOutputRegions[i];
            for (int j = 0; j < liquidOutputRegion.length; j++) {
                block.textureRegions.put("liquidOutput" + i + "-" + j,
                        new TextureRegion(liquidOutputRegions[i][j]));
            }
        }
    }

    @Override
    public void draw(Building build) {
        GenericCrafter crafter = (GenericCrafter) build.block;
        if (crafter.outputLiquids == null) {
            return;
        }
        if(randomizer.worldState.options.getRandomizeBlocksSize() && build.block.isRedrawned) {
            if(lstLiquidOutputs.isEmpty()) {
                Set<String> setTextureRegion = crafter.textureRegions.keySet();
                for (String key : setTextureRegion) {
                    int i = Integer.parseInt(key.split("-")[0].substring(12));
                    if (lstLiquidOutputs.size() > i) {
                        lstLiquidOutputs.get(i).add(crafter.textureRegions.get(key));
                    } else {
                        var list = new ArrayList<TextureRegion>();
                        list.add(crafter.textureRegions.get(key));
                        lstLiquidOutputs.add(list);
                    }
                }
            }
            for(int i = 0; i < lstLiquidOutputs.size(); i++) {
                int side = i < crafter.liquidOutputDirections.length ? crafter.liquidOutputDirections[i] : -1;
                if (side != -1) {
                    int realRot = (side + build.rotation) % 4;
                    Draw.rect(lstLiquidOutputs.get(realRot > 1 ? 1 : 0).get(i), build.x, build.y,
                            realRot * 90);
                }
            }
        } else {
            for (int i = 0; i < crafter.outputLiquids.length; i++) {
                int side = i < crafter.liquidOutputDirections.length ? crafter.liquidOutputDirections[i] : -1;
                if (side != -1) {
                    int realRot = (side + build.rotation) % 4;
                    Draw.rect(liquidOutputRegions[realRot > 1 ? 1 : 0][i], build.x, build.y, realRot * 90);
                }
            }
        }
    }

    @Override
    public void drawPlan(Block block, BuildPlan plan, Eachable<BuildPlan> list) {
        GenericCrafter crafter = (GenericCrafter) block;
        if (crafter.outputLiquids == null) {
            return;
        }

        for (int i = 0; i < crafter.outputLiquids.length; i++) {
            int side = i < crafter.liquidOutputDirections.length ?
                    crafter.liquidOutputDirections[i] : -1;
            if (side != -1) {
                int realRot = (side + plan.rotation) % 4;
                Draw.rect(liquidOutputRegions[realRot > 1 ? 1 : 0][i], plan.drawx(), plan.drawy()
                        , realRot * 90);
            }
        }
    }

    @Override
    public void load(Block block) {
        var crafter = expectCrafter(block);

        if (crafter.outputLiquids == null) {
            return;
        }

        liquidOutputRegions = new TextureRegion[2][crafter.outputLiquids.length];
        for (int i = 0; i < crafter.outputLiquids.length; i++) {
            for (int j = 1; j <= 2; j++) {
                liquidOutputRegions[j - 1][i] =
                        Core.atlas.find(block.name + "-" + crafter.outputLiquids[i].liquid.name + "-output" + j);
            }
        }
        super.load(block);
    }

    //can't display these properly
    @Override
    public TextureRegion[] icons(Block block) {
        return new TextureRegion[]{};
    }
}
