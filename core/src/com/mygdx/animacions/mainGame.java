package com.mygdx.animacions;



import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class mainGame extends ApplicationAdapter implements Screen {

    Animator game;

    private static final int FRAME_COLS = 9, FRAME_ROWS = 4;

    OrthographicCamera camera;
    Animation<TextureRegion> walkLeftAnimation;
    Animation<TextureRegion> walkRightAnimation;
    Animation<TextureRegion> walkUpAnimation;
    Animation<TextureRegion> walkDownAnimation;



    private Texture walkSheet;
    private SpriteBatch spriteBatch;
    private Rectangle animator;

    int fonsx, fonsy, x, y, carx, cary, test, balax, balay, timerb, db;
    boolean bullet = false;

    private Texture fondo = new Texture(Gdx.files.internal("background.jpg"));
    private int nFondo = 1;

    float stateTime;

    public mainGame(Animator game) {

        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Animator.WIDTH, Animator.HEIGHT);

        spriteBatch = new SpriteBatch();
        walkSheet = new Texture(Gdx.files.internal("skeleton_sprite.png"));

        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] walkLeft = new TextureRegion[FRAME_COLS];
        TextureRegion[] walkRight = new TextureRegion[FRAME_COLS];
        TextureRegion[] walkUp = new TextureRegion[FRAME_COLS];
        TextureRegion[] walkDown = new TextureRegion[FRAME_COLS];

        int index = 0;

        for  (int i = 0; i < FRAME_ROWS; i++){
            index = 0;
            for(int j = 0; j < FRAME_COLS; j++){
                if (i == 0)
                    walkUp[index++] = tmp[i][j];
                if (i == 1)
                    walkLeft[index++] = tmp[i][j];
                if (i == 2)
                    walkDown[index++] = tmp[i][j];
                if (i == 3)
                    walkRight[index++] = tmp[i][j];
            }
        }

        walkLeftAnimation = new Animation<TextureRegion>(0.025f, walkLeft);
        walkRightAnimation = new Animation<TextureRegion>(0.025f, walkRight);
        walkUpAnimation = new Animation<TextureRegion>(0.025f, walkUp);
        walkDownAnimation = new Animation<TextureRegion>(0.025f, walkDown);

        animator = new Rectangle();
        animator.x = 100;
        animator.y = 100;
        animator.width = 120;
        animator.height = 120;

        stateTime = 0f;

        fonsx = 5000;
        fonsy = 5000;
        x=0;
        y=0;
        carx = 300;
        cary = 250;
        timerb = 0;
        bullet = false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        TextureRegion currentFrame = walkLeftAnimation.getKeyFrame(stateTime, true);
        if(nFondo == 1 && ((animator.x >= 715 ||animator.y >= 500))){
            fondo = new Texture(Gdx.files.internal("background.png"));

            animator.x = 100;
            nFondo = 2;

        }
        if(nFondo == 2 && ((animator.x >= 715 ||animator.y >= 500) || (animator.x <= 0 ||animator.y <= 0))){
            fondo = new Texture(Gdx.files.internal("background.jpg"));
            animator.x = 715;
            nFondo = 1;

        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();


        game.batch.draw(fondo, 0, 0);

        int posicion = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            currentFrame = walkLeftAnimation.getKeyFrame(stateTime, true);
            game.batch.draw(walkLeftAnimation.getKeyFrame(stateTime, true), animator.x, animator.y, animator.width, animator.height);
            animator.x -= 100 * Gdx.graphics.getDeltaTime();
            posicion = 1;
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            currentFrame = walkRightAnimation.getKeyFrame(stateTime, true);
            game.batch.draw(walkRightAnimation.getKeyFrame(stateTime, true), animator.x, animator.y, animator.width, animator.height);
            animator.x += 100 * Gdx.graphics.getDeltaTime();
            posicion = 2;
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            currentFrame = walkUpAnimation.getKeyFrame(stateTime, true);
            game.batch.draw(walkUpAnimation.getKeyFrame(stateTime, true), animator.x, animator.y, animator.width, animator.height);
            animator.y += 100 * Gdx.graphics.getDeltaTime();
            posicion = 3;
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            currentFrame = walkDownAnimation.getKeyFrame(stateTime, true);
            game.batch.draw(walkDownAnimation.getKeyFrame(stateTime, true), animator.x, animator.y, animator.width, animator.height);
            animator.y -= 100 * Gdx.graphics.getDeltaTime();
            posicion = 4;
        }
//        if (!bullet) {
//            balax = carx+25;
//            balay = cary+20;
//            if (Gdx.input.isKeyPressed(Input.Keys.A)){
//                game.batch.draw(new Texture("DVD.png"), balax, balay);
//                bullet = true;
//                if (currentFrame == walkRightAnimation.getKeyFrame(stateTime, true)) {
//                    db=1;
//                }
//                else if (currentFrame == walkLeftAnimation.getKeyFrame(stateTime, true)) {
//                    db=2;
//                }
//                else if (currentFrame == walkUpAnimation.getKeyFrame(stateTime, true)) {
//                    db=3;
//                }
//                else if (currentFrame == walkDownAnimation.getKeyFrame(stateTime, true)) {
//                    db=4;
//                }
//            }
//        }
//        if (bullet) {
//            if (db == 1) {
//                balax = balax+10;
//            } else if (db == 2) {
//                balax = balax-10;
//            } else if (db == 3) {
//                balay = balay+10;
//            } else if (db == 4) {
//                balay = balay-10;
//            }
//            if (timerb < 20){
//                timerb++;
//                game.batch.draw(new Texture("DVD.png"),balax,balay);
//            } else if (timerb == 20){
//                bullet = false;
//                timerb = 0;
//            }
//        }

        else{

            game.batch.draw(walkDownAnimation.getKeyFrames()[1], animator.x, animator.y, animator.width, animator.height);
        }

        game.batch.end();

    }
    public void createBala () {


    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
