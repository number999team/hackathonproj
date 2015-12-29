package com.mygdx.game.ashley.core;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ashley.components.MoveComponent;
import com.mygdx.game.ashley.components.PositionComponent;
import com.mygdx.game.ashley.components.RenderComponent;
import com.mygdx.game.ashley.systems.MovementSystem;
import com.mygdx.game.ashley.systems.RenderSystem;

/**
 * Created by dimamir999 on 06.12.15.
 */
public class EntityManager {

    public static final float HERO_HEIGHT = 4;
    public static final float HERO_WIDTH = 6;

    Engine engine = new Engine();

    public EntityManager(OrthogonalTiledMapRenderer mapRenderer, OrthographicCamera camera){

        Entity heroEntity = new Entity();
        heroEntity.add(new PositionComponent(new Vector2(10f, 10f)));
        heroEntity.add(new MoveComponent(new Vector2(10f, 10f), 20, new Vector2(10f, 10f)));
        heroEntity.add(new RenderComponent(new TextureRegion(new Texture(Gdx.files.internal("Boss1.png")), 64, 64)));
        engine.addEntity(heroEntity);
        HeroInputListener heroController =  new HeroInputListener(heroEntity, camera);
        Gdx.input.setInputProcessor(heroController);

        TextureRegion monsterView = new TextureRegion(new Texture(Gdx.files.internal("Boss2.png")), 64, 64);

        Entity entity = new Entity();
        entity.add(new PositionComponent(new Vector2(0f, 0f)));
        entity.add(new MoveComponent(/*ComponentMappers.position.get(heroEntity).getPosition()*/new Vector2(10, 10), 20, new Vector2(0f, 0f)));
        entity.add(new RenderComponent(monsterView));
        engine.addEntity(entity);


        entity = new Entity();
        entity.add(new PositionComponent(new Vector2(40f, 40f)));
        entity.add(new MoveComponent(ComponentMappers.position.get(heroEntity).getPosition(), 20, new Vector2(40f, 40f)));
        entity.add(new RenderComponent(monsterView));
        engine.addEntity(entity);

        entity = new Entity();
        entity.add(new PositionComponent(new Vector2(0f, 40f)));
        entity.add(new MoveComponent(ComponentMappers.position.get(heroEntity).getPosition(), 20, new Vector2(0f, 40f)));
        entity.add(new RenderComponent(monsterView));
        engine.addEntity(entity);

        entity = new Entity();
        entity.add(new PositionComponent(new Vector2(80f, 0f)));
        entity.add(new MoveComponent(ComponentMappers.position.get(heroEntity).getPosition(), 20, new Vector2(80f, 0f)));
        entity.add(new RenderComponent(monsterView));
        engine.addEntity(entity);

        engine.addSystem(new MovementSystem(heroEntity, camera, mapRenderer));
        engine.addSystem(new RenderSystem(mapRenderer, camera));
    }

    public void update(float delta){
        engine.update(delta);
    }


}
