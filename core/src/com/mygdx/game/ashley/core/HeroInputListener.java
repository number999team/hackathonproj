package com.mygdx.game.ashley.core;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by dimamir999 on 28.12.15.
 */
public class HeroInputListener implements InputProcessor {

    private Entity hero;
    private OrthographicCamera camera;

    public HeroInputListener(Entity hero, OrthographicCamera camera) {
        this.hero = hero;
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    Vector3 moveAdapter = new Vector3();
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 move = ComponentMappers.move.get(hero).getTarget();
        moveAdapter.set(screenX, screenY, 0);
        camera.unproject(moveAdapter);
        move.set(moveAdapter.x - EntityManager.HERO_HEIGHT / 2, moveAdapter.y - EntityManager.HERO_WIDTH / 2);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
