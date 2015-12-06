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

    Engine engine = new Engine();

    public EntityManager(OrthogonalTiledMapRenderer mapRenderer, OrthographicCamera camera){

        engine.addSystem(new MovementSystem());
        engine.addSystem(new RenderSystem(mapRenderer, camera));

        Entity hentity = new Entity();
        hentity.add(new PositionComponent(new Vector2(10f, 10f)));
        hentity.add(new MoveComponent(new Vector2(10f,10f), 20, new Vector2(10f, 10f)));
        hentity.add(new RenderComponent(new TextureRegion(new Texture(Gdx.files.internal("Boss.png")), 64, 64)));
        engine.addEntity(hentity);

        TextureRegion monsterView = new TextureRegion(new Texture(Gdx.files.internal("People.png")), 32, 32);

        Entity entity = new Entity();
        entity.add(new PositionComponent(new Vector2(0f, 0f)));
        entity.add(new MoveComponent(ComponentMappers.position.get(hentity).getPosition(), 20, new Vector2(0f, 0f)));
        entity.add(new RenderComponent(monsterView));
        engine.addEntity(entity);


        entity = new Entity();
        entity.add(new PositionComponent(new Vector2(40f, 40f)));
        entity.add(new MoveComponent(ComponentMappers.position.get(hentity).getPosition(), 20, new Vector2(40f, 40f)));
        entity.add(new RenderComponent(monsterView));
        engine.addEntity(entity);

        entity = new Entity();
        entity.add(new PositionComponent(new Vector2(0f, 40f)));
        entity.add(new MoveComponent(ComponentMappers.position.get(hentity).getPosition(), 20, new Vector2(0f, 40f)));
        entity.add(new RenderComponent(monsterView));
        engine.addEntity(entity);

        entity = new Entity();
        entity.add(new PositionComponent(new Vector2(80f, 0f)));
        entity.add(new MoveComponent(ComponentMappers.position.get(hentity).getPosition(), 20, new Vector2(80f, 0f)));
        entity.add(new RenderComponent(monsterView));
        engine.addEntity(entity);
    }

    public void update(float delta){
        engine.update(delta);
    }


}
