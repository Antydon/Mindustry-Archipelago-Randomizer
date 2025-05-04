package mindustry.world.draw;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.gen.*;
import mindustry.world.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static mindustry.Vars.randomizer;

public class DrawFrames extends DrawBlock{
    /** Number of frames to draw. */
    public int frames = 3;
    /** Ticks between frames. */
    public float interval = 5f;
    /** If true, frames wil alternate back and forth in a sine wave. */
    public boolean sine = true;
    public TextureRegion[] regions;

    @Override
    public void rescale(Boolean isRescaled, float randomScale){
        for (TextureRegion region : regions) {
            region.scale = isRescaled ? randomScale : 1f;
        }
    }

    @Override
    public void reloadTextures(Block block) {
        for (int i = 0; i < regions.length; i++) {
            block.textureRegions.put(("frameRegion" + i), new TextureRegion(regions[i]));
        }
    }

    @Override
    public void draw(Building build){
        if(randomizer.worldState.options.getRandomizeBlocksSize() && build.block.isRedrawned) {
            List<TextureRegion> lstRegionsRandom = new ArrayList<>();
            Set<String> setTextureRegion = build.block.textureRegions.keySet();
            for(String key : setTextureRegion) {
                if(key.startsWith("frameRegion")) {
                    lstRegionsRandom.add(build.block.textureRegions.get(key));
                }
            }
            TextureRegion[] frameRegions = lstRegionsRandom.toArray(new TextureRegion[0]);
            Draw.rect(sine ? frameRegions[(int) Mathf.absin(build.totalProgress(), interval,frames - 0.001f)]
                    : frameRegions[(int) ((build.totalProgress() / interval) % frames)], build.x, build.y);
        } else {
            Draw.rect(sine ? regions[(int) Mathf.absin(build.totalProgress(), interval,frames - 0.001f)] :
                    regions[(int) ((build.totalProgress() / interval) % frames)], build.x, build.y);
        }
    }

    @Override
    public TextureRegion[] icons(Block block){
        return new TextureRegion[]{regions[0]};
    }

    @Override
    public void load(Block block){
        regions = new TextureRegion[frames];
        for(int i = 0; i < frames; i++){
            regions[i] = Core.atlas.find(block.name + "-frame" + i);
        }
        super.load(block);
    }
}
