package ru.znay.znay.tr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import ru.znay.znay.tr.entity.Card;
import ru.znay.znay.tr.gfx.Art;
import ru.znay.znay.tr.level.Level;

public class TransformersGame extends Game {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public OrthographicCamera camera;
    private Stage stage;

    public SpriteBatch batch;
    public ShapeRenderer shape;
    public BitmapFont font;
    private Level level;
    private int selectTime = 0;
    private Card selected = null;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true);

        stage = new Stage(new FitViewport(WIDTH, HEIGHT, camera));
        batch = new SpriteBatch();

        font = new BitmapFont(Gdx.files.internal("font.fnt"), true);
        font.setColor(1.0f, 0.0f, 0.0f, 1.0f);
        font.getData().setScale(0.7f);

        shape = new ShapeRenderer();
        newGame();
    }

    public void newGame() {
        level = new Level(4, 3);
    }

    @Override
    public void render() {
        if (selectTime > 0) selectTime--;
        level.tick();

        camera.update();
        Gdx.gl.glClearColor(0.3f, 0.7f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (selected != null) {
            shape.setProjectionMatrix(camera.combined);
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.setColor(0.3f, 0.4f, 0.7f, 0.4f);
            Rectangle r = level.getRect(selected);
            shape.rect(r.x, r.y, r.width, r.height);
            shape.end();
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        level.render(batch, font);
        batch.end();

        if (Gdx.input.isTouched() && selectTime == 0) {
            Vector3 p = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            p = camera.unproject(p);
            Card c = level.getEntityAt(p.x, p.y);
            if (selected != null && c != null) {
                if (c == selected) {
                    selected = null;
                } else if (c.id == selected.id) {
                    c.removed = true;
                    selected.removed = true;
                    selected = null;
                } else {
                    selected = c;
                }
            } else if (selected == null && c != null) {
                selected = c;
            }
            selectTime = 10;
            if (level.end()) {
                newGame();
            }
        }

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, false);
    }


    @Override
    public void dispose() {
        super.dispose();
        Art.i.dispose();
        batch.dispose();
        font.dispose();
        shape.dispose();
        stage.dispose();
    }
}
