package ru.znay.znay.tr.entity;

import ru.znay.znay.tr.gfx.Art;

/**
 * Created by admin on 13.06.2016.
 */
public class Card extends Entity {

    public Card(float x, float y, int id, int texture) {
        super(x, y, id, Art.i.t[texture % Art.i.t.length]);
    }
}
