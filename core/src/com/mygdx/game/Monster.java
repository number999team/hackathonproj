package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dimamir999 on 02.12.15.
 */
public class Monster{

    public static final float WIDTH = 4;
    public static final float HEIGHT = 6;
    public static final float MAX_SPEED = 15;

    public Vector2 position = new Vector2();
    private Vector2 target = new Vector2();
    private TextureRegion view;

    public Monster(TextureRegion view) {
        this.view = view;
    }

    public Monster() {}

    public void render (Batch batch){
        batch.begin();
        batch.draw(view, position.x, position.y, WIDTH, HEIGHT);
        batch.end();
    }

    private Vector2 direction = new Vector2();
    public void update(float delta){
        direction.set(target.x, target.y);
        direction.sub(position);
        direction.setLength(MAX_SPEED);
        position.add(direction.scl(delta));
        direction.scl(1 / delta);
    }

    public void setTarget(Vector2 target) {
        this.target = target;
    }

    public void setView(TextureRegion view) {
        this.view = view;
    }
}
