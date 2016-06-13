package ru.znay.znay.tr.gfx;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by admin on 12.06.2016.
 */
public class Art {
    private final TextureAtlas atlas = new TextureAtlas("transformers.pack");
    public final TextureRegion[] t;
    public static Art i = new Art();

    public Art() {
        t = new TextureRegion[atlas.getRegions().size];
        for (int i = 0; i < t.length; i++) {
            t[i] = atlas.getRegions().get(i);
        }
    }


    public void dispose() {
        atlas.dispose();
    }
}
