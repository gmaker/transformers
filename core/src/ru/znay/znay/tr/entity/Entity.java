package ru.znay.znay.tr.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by admin on 13.06.2016.
 */
public class Entity extends Sprite {
    public Entity(TextureRegion region) {
        super(region);
        setFlip(false, true);
        setScale(4.0f);
    }
}
