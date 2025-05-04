package mindustry.world.draw;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.util.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.world.*;

import static mindustry.Vars.*;

public class DrawPower extends DrawBlock{
    public TextureRegion emptyRegion, fullRegion;
    public String suffix = "-power";

    public boolean drawPlan = true;
    /** If false, fades between emptyRegion and fullRegion instead of mixcol between empty and full colors. */
    public boolean mixcol = true;
    public Color emptyLightColor = Color.valueOf("f8c266");
    public Color fullLightColor = Color.valueOf("fb9567");

    /** Any number <=0 disables layer changes. */
    public float layer = -1;

    public DrawPower(){
    }

    public DrawPower(String suffix){
        this.suffix = suffix;
    }

    @Override
    public void rescale(Boolean isRescaled, float randomScale){
        emptyRegion.scale = isRescaled ? randomScale : 1f;
        fullRegion.scale = isRescaled ? randomScale : 1f;
    }

    @Override
    public void reloadTextures(Block block) {
        if (fullRegion != null && emptyRegion != null) {
            block.textureRegions.put("powerFull", new TextureRegion(fullRegion));
            block.textureRegions.put("powerEmpty", new TextureRegion(emptyRegion));
        } else {
            block.failedTexturesReload = true;
        }
    }

    @Override
    public void draw(Building build){
        float z = Draw.z();
        if(layer > 0) Draw.z(layer);
        if(randomizer.worldState.options.getRandomizeBlocksSize() && build.block.isRedrawned){
            drawRandom(build);
        } else {
            if (mixcol) {
                Draw.color(emptyLightColor, fullLightColor, build.power.status);
                if (emptyRegion.found()) {
                    Draw.rect(emptyRegion, build.x, build.y);
                } else {
                    Fill.square(build.x, build.y, (tilesize * build.block.size / 2f - 1) * Draw.xscl);
                }
            } else {
                Draw.rect(emptyRegion, build.x, build.y);
                Draw.alpha(build.power.status);
                Draw.rect(fullRegion, build.x, build.y);
            }
        }
        Draw.color();
        Draw.z(z);
    }

    private void drawRandom(Building build){
        if(build.block.failedTexturesReload) {
            reloadTextures(build.block);
        }
        if(mixcol){
            Draw.color(emptyLightColor, fullLightColor, build.power.status);
            if(build.block.textureRegions.get("powerEmpty").found()){
                Draw.rect(build.block.textureRegions.get("powerEmpty"), build.x, build.y);
            }else{
                Fill.square(build.x, build.y, (tilesize * build.block.size / 2f - 1) * Draw.xscl);
            }
        }else{
            Draw.rect(build.block.textureRegions.get("powerEmpty"), build.x, build.y);
            Draw.alpha(build.power.status);
            Draw.rect(build.block.textureRegions.get("powerFull"), build.x, build.y);
        }
    }

    @Override
    public void drawPlan(Block block, BuildPlan plan, Eachable<BuildPlan> list){
        if(!drawPlan || mixcol || !emptyRegion.found()) return;
        if(randomizer.worldState.options.getRandomizeBlocksSize() && block.isRedrawned) {
            if(block.failedTexturesReload) {
                reloadTextures(block);
            }
            Draw.rect(block.textureRegions.get("powerEmpty"), plan.drawx(), plan.drawy());
        } else {
            Draw.rect(emptyRegion, plan.drawx(), plan.drawy());
        }
    }

    @Override
    public TextureRegion[] icons(Block block){
        return !mixcol && emptyRegion.found() ? new TextureRegion[]{emptyRegion} : new TextureRegion[]{};
    }

    @Override
    public void load(Block block){
        if(mixcol){
            emptyRegion = Core.atlas.find(block.name + suffix);
        }else{
            emptyRegion = Core.atlas.find(block.name + suffix + "-empty");
            fullRegion = Core.atlas.find(block.name + suffix + "-full");
        }
        super.load(block);
    }
}
