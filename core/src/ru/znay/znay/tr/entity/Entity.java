package ru.znay.znay.tr.entity;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.znay.znay.tr.level.Level;


/**
 * Created by admin on 13.06.2016.
 */
public class Entity extends Sprite {
    public float x;
    public float y;
    public float scale = 4.0f;
    public Level level;
    public boolean removed = false;
    public int id;

    public Entity(float x, float y, int id, TextureRegion region) {
        super(region);
        this.x = x;
        this.y = y;
        this.id = id;
        setFlip(false, true);
    }

    public void init(Level level) {
        this.level = level;
        setScale(scale);
        Rectangle r = getBoundingRectangle();
        setOrigin(r.width / (2 * scale), r.height / (1 * scale));
    }

    public void tick() {
        //rotate(0.5f);
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        this.setPosition(x, y);
        this.draw(batch);

        font.draw(batch, "" + id, x, y + 20);
    }
}
