package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class GdxGamePathfinder extends ApplicationAdapter {

    static public final float EPSILON = 0.000001f;
    final Random random = new Random();
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture img;
    private BitmapFont font;
    private float stateTime;
    private Texture[] heroesTexture;
    private Rectangle[] heroesRectangls;
    private int heroesSelectedIndex = -1;

    @Override
    public void create() {
        heroesTexture = new Texture[3];


        heroesTexture[0] = new Texture("data/Xarsk.JPG");
        heroesTexture[1] = new Texture("data/Kajra.JPG");
        heroesTexture[2] = new Texture("data/Mersiel.JPG");


        heroesRectangls = new Rectangle[3];

        heroesRectangls[0] = new Rectangle(0, 500, 3500, 3024);
        heroesRectangls[1] = new Rectangle(3500, 500, 3500, 3024);
        heroesRectangls[2] = new Rectangle(7000, 500, 3500, 3024);

        // создается камера и SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280 * 8, 720 * 8);


        batch = new SpriteBatch();


        font = new BitmapFont();
        font.getData().setScale(18);

        //random.nextInt(3)
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

        //Gdx.graphics.setTitle("fps: " + Gdx.graphics.getFramesPerSecond());
        batch.begin();
        if (heroesSelectedIndex ==-1) {
            for (int i = 0; i < heroesRectangls.length; i++) {

                batch.draw(heroesTexture[i], heroesRectangls[i].x, heroesRectangls[i].y);

            }
        }else{
            batch.draw(heroesTexture[heroesSelectedIndex], heroesRectangls[heroesSelectedIndex].x, heroesRectangls[heroesSelectedIndex].y);
        }

        font.draw(batch, "Scoore: 0", 500, 2000);

        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        // обработка пользовательского ввода
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            String str = "display(" + touchPos.x + " " + touchPos.y + " " + touchPos.z + ") ";
            camera.unproject(touchPos);
            str += "world:(" + touchPos.x + " " + touchPos.y + " " + touchPos.z + ")";

            Gdx.graphics.setTitle(str);

            for (int i = 0; i < heroesRectangls.length; i++) {
                if (heroesRectangls[i].contains(touchPos.x, touchPos.y)) {
                    Gdx.graphics.setTitle("you touch" + i);
                    heroesSelectedIndex = i;
                    break;
                }

            }
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        for (int i = 0; i < heroesTexture.length; i++) {
            heroesTexture[i].dispose();
        }

    }
}
