package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dimamir999 on 01.12.15.
 */
public class Hero {

    public static final float WIDTH = 6;
    public static final float HEIGHT = 8;
    public static final float MAX_SPEED = 20;

    /*public enum DirectionType{
        UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT;

        public boolean checkMoveEnd(Vector2 position, Vector2 target){
            switch (this){
                case UP_RIGHT:
                    break;
                case UP_LEFT:
                    break;
                case DOWN_LEFT:
                    break;
                case UP_RIGHT:
                    break;
            }
            return true;
        }

    }*/

    public Vector2 position;
    public Vector2 target;
    public Vector2 direction;
    private TextureRegion view;

    public Hero(TextureRegion heroView) {
        this.view = heroView;
        position = new Vector2(0, 0);
        target = new Vector2(0, 0);
        direction = new Vector2(0, 0);
    }

    public void render (Batch batch){
        batch.begin();
        batch.draw(view, position.x, position.y, WIDTH, HEIGHT);
        batch.end();
    }

    private Vector2 startPoint = new Vector2();
    private float borderLength;

    public void setStartPoint(Vector2 startPoint) {
        this.startPoint.set(startPoint.x, startPoint.y);
        this.borderLength = Vector2.dst(startPoint.x, startPoint.y, target.x, target.y);
    }

    public void update(float delta){
        if(borderLength > Vector2.dst(position.x ,position.y, startPoint.x, startPoint.y)){
            position.add(direction.scl(delta));
            direction.scl(1 / delta);
        }
        //checkCollisions();
    }


    private void checkCollisions() {
        Rectangle koalaRect = new Rectangle();
        koalaRect.set(position.x, position.y, WIDTH, HEIGHT);
        int startX, startY, endX, endY;

        /*if (velocity.x > 0) {
            startX = (int)(position.x + velocity.x);
            endX = (int)(position.x + WIDTH + velocity.x);
        } else {
            startX = (int)(position.x + velocity.x - WIDTH);
            endX = (int)(position.x + velocity.x);
        }
        if (velocity.y > 0) {
            startY = (int)(position.y + velocity.y);
            endY = (int)(position.y + WIDTH + velocity.y);
        } else {
            startY = (int)(position.y + velocity.y - WIDTH);
            endY = (int)(position.y + velocity.x);
        }*/

        /*if (velocity.y > 0) {
            startY = endY = (int)(position.y + HEIGHT + velocity.y);
        } else {
            startY = endY = (int)(position.y + velocity.y);
        }
        if (velocity.x > 0) {
            startX = endX = (int)(position.x + WIDTH + velocity.x);
        } else {
            startX = endX = (int)(position.x + velocity.x);
        }


        game.getBlockedTiles(startX, startY, endX, endY, game.tiles);
        koalaRect.x += velocity.x;
        koalaRect.y += velocity.y;
        for (Rectangle tile : game.tiles) {
            if (koalaRect.overlaps(tile)) {
                velocity.x = 0;
                velocity.y = 0;
                break;
            }
        }*/
    }
}
