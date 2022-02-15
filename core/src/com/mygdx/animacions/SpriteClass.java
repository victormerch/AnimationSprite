package com.mygdx.animacions;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//public class SpriteClass implements ApplicationListener {
//
//    // Constant rows and columns of the sprite sheet
//    private static final int FRAME_COLS = 9, FRAME_ROWS = 4;
//
//    // Objects used
//    Animation<TextureRegion> walkAnimation, AbAnimation, IzAnimation, DeAnimation, ArAnimation; // Must declare frame type (TextureRegion)
//    Texture walkSheet, background, bala;
//    TextureRegion currentFrame;
//    SpriteBatch spriteBatch;
//    TextureRegion bgRegion;
//    int fonsx, fonsy, x, y, carx, cary, test, balax, balay, timerb, db;
//    boolean bullet = false;
//
//    // A variable for tracking elapsed time for the animation
//    float stateTime;
//
//    @Override
//    public void create() {
//
//        // Load the sprite sheet as a Texture
//        walkSheet = new Texture(Gdx.files.internal("skeleton_sprite.png"));
//
//        // Use the split utility method to create a 2D array of TextureRegions. This is
//        // possible because this sprite sheet contains frames of equal size and they are
//        // all aligned.
//        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
//                walkSheet.getWidth() / FRAME_COLS,
//                walkSheet.getHeight() / FRAME_ROWS);
//
//        // Place the regions into a 1D array in the correct order, starting from the top
//        // left, going across first. The Animation constructor requires a 1D array.
//        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
//        int index = 0;
//        for (int i = 0; i < FRAME_ROWS; i++) {
//            for (int j = 0; j < FRAME_COLS; j++) {
//                walkFrames[index++] = tmp[i][j];
//            }
//        }
//
//        TextureRegion[] abajo = new TextureRegion[1];
//        for (int i = 0; i < FRAME_COLS; i++) {
//            abajo[i] = tmp[0][i];
//        }
//        TextureRegion[] izquierda = new TextureRegion[2];
//        for (int i = 0; i < FRAME_COLS; i++) {
//            izquierda[i] = tmp[1][i];
//        }
//        TextureRegion[] derecha = new TextureRegion[3];
//        for (int i = 0; i < FRAME_COLS; i++) {
//            derecha[i] = tmp[2][i];
//        }
//        TextureRegion[] arriba = new TextureRegion[4];
//        for (int i = 0; i < FRAME_COLS; i++) {
//            arriba[i] = tmp[3][i];
//        }
//
//
//
//        // Initialize the Animation with the frame interval and array of frames
//        walkAnimation = new Animation<TextureRegion>(0.1f, walkFrames);
//        AbAnimation = new Animation<TextureRegion>(0.1f, abajo);
//        IzAnimation = new Animation<TextureRegion>(0.1f, izquierda);
//        DeAnimation = new Animation<TextureRegion>(0.1f, derecha);
//        ArAnimation = new Animation<TextureRegion>(0.1f, arriba);
//        // Instantiate a SpriteBatch for drawing and reset the elapsed animation
//        // time to 0
//        spriteBatch = new SpriteBatch();
//        stateTime = 0f;
//
//        bala = new Texture(Gdx.files.internal("DVD.png"));
//
//        // bg
//        background = new Texture(Gdx.files.internal("background.jpg"));
//        background.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
//        bgRegion = new TextureRegion(background, 0, 0);
//        fonsx = 5000;
//        fonsy = 5000;
//        x = 0;
//        y = 0;
//        carx = 300;
//        cary = 250;
//        timerb = 0;
//        bullet = false;
//    }
//
//    @Override
//    public void resize(int width, int height) {
//
//    }
//
//    @Override
//    public void render() {
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
//        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
//        bgRegion.setRegion(fonsx, fonsy, fonsx, fonsy);
//        currentFrame = AbAnimation.getKeyFrame(1, true);
//        // PINTAR
//        // Get current frame of animation for the current stateTime
//        //currentFrame = walkAnimation.getKeyFrame(stateTime, true);
//
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            test++;
//            currentFrame = DeAnimation.getKeyFrame(stateTime, true);
//            x = x - 5;
//        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//            test++;
//            currentFrame = IzAnimation.getKeyFrame(stateTime, true);
//            x = x + 5;
//        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//            test++;
//            currentFrame = ArAnimation.getKeyFrame(stateTime, true);
//            y = y - 5;
//        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//            test++;
//            currentFrame = AbAnimation.getKeyFrame(stateTime, true);
//            y = y + 5;
//        }
//
//        spriteBatch.begin();
//        spriteBatch.draw(bgRegion, x, y);
//        spriteBatch.draw(currentFrame, carx, cary); // Draw current frame at (50, 50)
//        if (!bullet) {
//            balax = carx + 25;
//            balay = cary + 20;
//            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//                spriteBatch.draw(bala, balax, balay);
//                bullet = true;
//                if (currentFrame == DeAnimation.getKeyFrame(stateTime, true)) {
//                    db = 1;
//                } else if (currentFrame == IzAnimation.getKeyFrame(stateTime, true)) {
//                    db = 2;
//                } else if (currentFrame == ArAnimation.getKeyFrame(stateTime, true)) {
//                    db = 3;
//                } else if (currentFrame == AbAnimation.getKeyFrame(stateTime, true)) {
//                    db = 4;
//                }
//            }
//        }
//        if (bullet) {
//            if (db == 1) {
//                balax = balax + 10;
//            } else if (db == 2) {
//                balax = balax - 10;
//            } else if (db == 3) {
//                balay = balay + 10;
//            } else if (db == 4) {
//                balay = balay - 10;
//            }
//            if (timerb < 20) {
//                timerb++;
//                spriteBatch.draw(bala, balax, balay);
//            } else if (timerb == 20) {
//                bullet = false;
//                timerb = 0;
//            }
//        }
//        spriteBatch.end();
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void dispose() { // SpriteBatches and Textures must always be disposed
//        spriteBatch.dispose();
//        walkSheet.dispose();
//    }
//}
public class SpriteClass implements ApplicationListener {

    // Constant rows and columns of the sprite sheet
    private static final int FRAME_COLS = 4, FRAME_ROWS = 4;

    // Objects used
    Animation<TextureRegion> walkAnimation, AbAnimation, IzAnimation, DeAnimation, ArAnimation; // Must declare frame type (TextureRegion)
    Texture walkSheet, background, bala;
    TextureRegion currentFrame;
    SpriteBatch spriteBatch;
    TextureRegion bgRegion;
    int fonsx, fonsy, x, y, carx, cary, test, balax, balay, timerb, db;
    boolean bullet = false;

    // A variable for tracking elapsed time for the animation
    float stateTime;

    @Override
    public void create() {

        // Load the sprite sheet as a Texture
        walkSheet = new Texture(Gdx.files.internal("skeleton_sprite.png"));

        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        TextureRegion[] abajo = new TextureRegion[4];
        for (int i = 0; i < FRAME_COLS; i++){
            abajo[i] = tmp[0][i];
        }
        TextureRegion[] izquierda = new TextureRegion[4];
        for (int i = 0; i < FRAME_COLS; i++){
            izquierda[i] = tmp[1][i];
        }
        TextureRegion[] derecha = new TextureRegion[4];
        for (int i = 0; i < FRAME_COLS; i++){
            derecha[i] = tmp[2][i];
        }
        TextureRegion[] arriba = new TextureRegion[4];
        for (int i = 0; i < FRAME_COLS; i++){
            arriba[i] = tmp[3][i];
        }

        // Initialize the Animation with the frame interval and array of frames
        walkAnimation = new Animation<TextureRegion>(0.1f, walkFrames);
        AbAnimation = new Animation<TextureRegion>(0.1f,abajo);
        IzAnimation = new Animation<TextureRegion>(0.1f,izquierda);
        DeAnimation = new Animation<TextureRegion>(0.1f,derecha);
        ArAnimation = new Animation<TextureRegion>(0.1f,arriba);
        // Instantiate a SpriteBatch for drawing and reset the elapsed animation
        // time to 0
        spriteBatch = new SpriteBatch();
        stateTime = 0f;

        bala = new Texture(Gdx.files.internal("DVD.png"));

        // bg
        background = new Texture(Gdx.files.internal("background.jpg"));
        background.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        bgRegion = new TextureRegion(background,0,0);
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
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        bgRegion.setRegion(fonsx,fonsy,fonsx,fonsy);
        currentFrame = AbAnimation.getKeyFrame(1, true);
        // PINTAR
        // Get current frame of animation for the current stateTime
        //currentFrame = walkAnimation.getKeyFrame(stateTime, true);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            test++;
            currentFrame = DeAnimation.getKeyFrame(stateTime, true);
            x=x-5;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            test++;
            currentFrame = IzAnimation.getKeyFrame(stateTime, true);
            x=x+5;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            test++;
            currentFrame = ArAnimation.getKeyFrame(stateTime, true);
            y=y-5;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            test++;
            currentFrame = AbAnimation.getKeyFrame(stateTime, true);
            y=y+5;
        }

        spriteBatch.begin();
        spriteBatch.draw(bgRegion,x,y);
        spriteBatch.draw(currentFrame, carx, cary); // Draw current frame at (50, 50)
        if (!bullet) {
            balax = carx+25;
            balay = cary+20;
            if (Gdx.input.isKeyPressed(Input.Keys.A)){
                spriteBatch.draw(bala, balax, balay);
                bullet = true;
                if (currentFrame == DeAnimation.getKeyFrame(stateTime, true)) {
                    db=1;
                }
                else if (currentFrame == IzAnimation.getKeyFrame(stateTime, true)) {
                    db=2;
                }
                else if (currentFrame == ArAnimation.getKeyFrame(stateTime, true)) {
                    db=3;
                }
                else if (currentFrame == AbAnimation.getKeyFrame(stateTime, true)) {
                    db=4;
                }
            }
        }
        if (bullet) {
            if (db == 1) {
                balax = balax+10;
            } else if (db == 2) {
                balax = balax-10;
            } else if (db == 3) {
                balay = balay+10;
            } else if (db == 4) {
                balay = balay-10;
            }
            if (timerb < 20){
                timerb++;
                spriteBatch.draw(bala,balax,balay);
            } else if (timerb == 20){
                bullet = false;
                timerb = 0;
            }
        }
        spriteBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() { // SpriteBatches and Textures must always be disposed
        spriteBatch.dispose();
        walkSheet.dispose();
    }
}
