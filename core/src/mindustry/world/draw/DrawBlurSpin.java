package mindustry.world.draw;

import arc.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;

public class DrawBlurSpin extends DrawBlock{
    public TextureRegion region, blurRegion;
    public String suffix = "";
    public float rotateSpeed = 1f, x, y, blurThresh = 0.7f;

    public DrawBlurSpin(String suffix, float speed){
        this.suffix = suffix;
        rotateSpeed = speed;
    }

    public DrawBlurSpin(){
    }

    @Override
    public void rescale(Boolean isRescaled, float randomScale){
        region.scale = isRescaled ? randomScale: 1f;
        blurRegion.scale = isRescaled ? randomScale: 1f;
    }

    @Override
    public void reloadTextures(Block block) {
        block.textureRegions.put("blurSpinRegion", new TextureRegion(region));
        block.textureRegions.put("blurRegion", new TextureRegion(blurRegion));
    }

    @Override
    public void draw(Building build){
        Drawf.spinSprite(build.warmup() > blurThresh ? blurRegion : region, build.x + x, build.y + y, build.totalProgress() * rotateSpeed);
    }

    @Override
    public TextureRegion[] icons(Block block){
        return new TextureRegion[]{region};
    }

    @Override
    public void load(Block block){
        region = Core.atlas.find(block.name + suffix);
        blurRegion = Core.atlas.find(block.name + suffix + "-blur");
        super.load(block);
    }
}
