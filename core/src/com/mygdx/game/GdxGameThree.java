package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GdxGameThree extends ApplicationAdapter {

    static public final float EPSILON = 0.000001f;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Texture img;

    private BitmapFont font;
    private float stateTime;

    @Override
    public void create() {

        // создается камера и SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 2000, 2000);
        batch = new SpriteBatch();
        img = new Texture("data/items.png");

        font = new BitmapFont();
        font.getData().setScale(18);
    }

    @Override
    public void render() {
        float delta = Math.min(0.06f * 1000, Gdx.graphics.getDeltaTime());
        stateTime += delta;
        stateTime = 0f;
//        action.act(delta);


        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // сообщает камере, что нужно обновить матрицы
        camera.update();

        // сообщаем SpriteBatch о системе координат
        // визуализации указанной для камеры.
        batch.setProjectionMatrix(camera.combined);

        Gdx.graphics.setTitle("fps: " + Gdx.graphics.getFramesPerSecond());
        batch.begin();
        batch.draw(img, 300 , 300 );

        font.draw(batch, "Scoore: 0",500,2000);

        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        // обработка пользовательского ввода
        if (Gdx.input.isTouched()) {
        }


    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        font.dispose();

    }
}
