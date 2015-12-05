package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by dimamir999 on 01.12.15.
 */
public class HeroInputListener implements InputProcessor {

    private Hero hero;
    private OrthographicCamera camera;

    public HeroInputListener(Hero hero, OrthographicCamera camera) {
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

    private Vector3 tp = new Vector3();
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT) return false;
        tp.set(screenX, screenY, 0);
        camera.unproject(tp);
        hero.target.x = tp.x - Hero.WIDTH / 2;
        hero.target.y = tp.y - Hero.HEIGHT / 2;
        hero.direction.set(hero.target.x - hero.position.x, hero.target.y - hero.position.y);
        hero.setStartPoint(hero.position);
        hero.direction.setLength(Hero.MAX_SPEED);
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
