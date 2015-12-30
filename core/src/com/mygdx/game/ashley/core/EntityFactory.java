package com.mygdx.game.ashley.core;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ashley.components.MoveComponent;
import com.mygdx.game.ashley.components.PositionComponent;
import com.mygdx.game.ashley.components.RenderComponent;

/**
 * Created by dimamir999 on 30.12.15.
 */
public class EntityFactory {

    TextureRegion heroView = new TextureRegion(new Texture(Gdx.files.internal("Boss1.png")), 64, 64);
    TextureRegion monsterView = new TextureRegion(new Texture(Gdx.files.internal("Boss2.png")), 64, 64);

    public Entity createHero(Vector2 startPosition){
        Entity heroEntity = new Entity();
        heroEntity.add(new PositionComponent(startPosition));
        heroEntity.add(new MoveComponent(startPosition.cpy(), 20, startPosition.cpy()));
        heroEntity.add(new RenderComponent(heroView));
        return heroEntity;
    }

    public Entity createMonser(Vector2 startPosition, Vector2 target){
        Entity monsterEntity = new Entity();
        monsterEntity.add(new PositionComponent(startPosition));
        monsterEntity.add(new MoveComponent(target, 20, startPosition.cpy()));
        monsterEntity.add(new RenderComponent(monsterView));
        return monsterEntity;
    }
}
