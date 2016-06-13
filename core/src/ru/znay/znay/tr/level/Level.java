package ru.znay.znay.tr.level;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import ru.znay.znay.tr.TransformersGame;
import ru.znay.znay.tr.entity.Card;
import ru.znay.znay.tr.entity.Entity;
import ru.znay.znay.tr.gfx.Art;

import java.util.Random;


/**
 * Created by admin on 13.06.2016.
 */
public class Level {
    private static final Random random = new Random();
    private Card[] cards;
    public final int w;
    public final int h;
    public final int cellW;
    public final int cellH;

    public Level(int w, int h) {
        this.w = w;
        this.h = h;
        int cc = w * h;
        if ((cc & 1) == 1) throw new IllegalStateException("Failed to create level.");
        cards = new Card[w * h];
        cellW = TransformersGame.WIDTH / (w);
        cellH = TransformersGame.HEIGHT / (h);

        int tOffs = random.nextInt(Art.i.t.length);
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int id;
                while (true) {
                    id = random.nextInt(cc / 2) + 1;
                    int count = 0;
                    for (int i = 0; i < w * h; i++) {
                        if (cards[i] != null && cards[i].id == id) count++;
                    }
                    if (count < 2) break;
                }
                addEntity(new Card(x * cellW + cellW / 2, y * cellH + cellH / 2 + 32, id, id + tOffs), x, y);
            }
        }
    }

    public Card getEntityAt(float x, float y) {
        int xx = (int) (x / cellW);
        int yy = (int) (y / cellH);
        if (xx < 0 || xx >= w || yy < 0 || yy >= h) return null;
        return cards[xx + yy * w];
    }

    public void addEntity(Card c, int x, int y) {
        cards[x + y * w] = c;
        c.init(this);
    }

    public void removeEntity(Card c, int x, int y) {
        cards[x + y * w] = null;
    }

    public void tick() {
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Card c = cards[x + y * w];
                if (c == null) continue;
                if (!c.removed) c.tick();
                if (c.removed) {
                    removeEntity(c, x, y);
                }
            }
        }
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Card c = cards[x + y * w];
                if (c == null) continue;
                c.render(batch, font);
            }
        }
    }

    public Rectangle getRect(Card selected) {
        Rectangle r = new Rectangle();
        int szOffs = 16;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (cards[x + y * w] == selected) {
                    r.set(x * cellW + 16, y * cellH + 16, cellW - szOffs, cellW - szOffs);
                    break;
                }
            }
        }
        return r;
    }

    public boolean end() {
        boolean result = true;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) continue;
            if (cards[i].removed) continue;
            result = false;
            break;
        }
        return result;
    }
}
