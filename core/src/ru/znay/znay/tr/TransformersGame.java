package ru.znay.znay.tr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import ru.znay.znay.tr.entity.Entity;
import ru.znay.znay.tr.gfx.Art;

import java.util.ArrayList;
import java.util.List;

public class TransformersGame extends Game {
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public OrthographicCamera camera;
    private Stage stage;

    public SpriteBatch batch;

    private int tickTime = 0;
    private float angle = 0f;

    private List<Entity> entities = new ArrayList<Entity>();

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true);

        stage = new Stage(new FitViewport(WIDTH, HEIGHT, camera));
        batch = new SpriteBatch();
        for (int i = 0; i < Art.t.length; i++) {
            entities.add(new Entity(Art.t[i]));
        }
    }

    @Override
    public void render() {
        tickTime++;
        if (tickTime % 2 == 0) {
            angle += 1.0;
            angle %= 360;
        }
        camera.update();
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int i = 0; i < Art.t.length; i++) {
            Entity e = entities.get(i);
            e.rotate(0.5f);
            e.setPosition(i % 10 * 20*3, i / 10 * 30*3);
            e.draw(batch);
        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, false);
    }


    @Override
    public void dispose() {
        super.dispose();
        Art.dispose();
        batch.dispose();
        stage.dispose();
    }
}
