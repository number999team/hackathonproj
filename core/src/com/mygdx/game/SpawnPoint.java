package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by dimamir999 on 02.12.15.
 */
public class SpawnPoint {

    private float period;
    private Vector2 position;
    private float time;
    private TextureRegion monsterView;
    private Hero hero;
    private Pool<Monster> monsterPool;
    private Monster monster;

    public SpawnPoint(float period, Vector2 position, TextureRegion monsterView, Hero hero, Pool<Monster> monsterPool) {
        this.period = period;
        this.position = position;
        this.monsterView = monsterView;
        this.hero = hero;
        this.monsterPool = monsterPool;
    }

    public boolean update(float delta, Array<Monster> monsters){
        if(time > period){
            time = 0f;
            monster = monsterPool.obtain();
            initMonster(monster);
            monsters.add(monster);
            return true;
        }
        else {
            time += delta;
            return false;
        }
    }

    private void initMonster(Monster monster){
        monster.setView(monsterView);
        monster.position.set(position.x, position.y);
        monster.setTarget(hero.position);
    }

}
