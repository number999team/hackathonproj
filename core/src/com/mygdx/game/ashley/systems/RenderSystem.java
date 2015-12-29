package com.mygdx.game.ashley.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ashley.components.PositionComponent;
import com.mygdx.game.ashley.components.RenderComponent;
import com.mygdx.game.ashley.core.ComponentMappers;
import com.mygdx.game.ashley.core.EntityManager;

import java.util.Comparator;

/**
 * Created by dimamir999 on 06.12.15.
 */

//add binary insertion of new entitites for this system
public class RenderSystem extends EntitySystem{

    private Array<Entity> entities = new Array<Entity>();
    private EntityPositionComparator comparator = new EntityPositionComparator();

    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    public RenderSystem(OrthogonalTiledMapRenderer mapRenderer, OrthographicCamera camera) {
        this.mapRenderer = mapRenderer;
        this.camera = camera;
    }

    @Override
    public void addedToEngine(Engine engine) {
        ImmutableArray<Entity> startEntitites = engine.getEntitiesFor(Family.all(PositionComponent.class, RenderComponent.class).get());
        for(Entity entity:startEntitites){
            entities.add(entity);
        }
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {

        PositionComponent position;
        RenderComponent render;
        Batch batch = mapRenderer.getBatch();

        //make there insertion sort
        entities.sort(comparator);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        mapRenderer.render(new int[]{0, 1});

        batch.begin();

        for(Entity entity: entities){
            position = ComponentMappers.position.get(entity);
            render = ComponentMappers.render.get(entity);
            batch.draw(render.getView(), position.getPosition().x, position.getPosition().y,
                    EntityManager.HERO_HEIGHT, EntityManager.HERO_WIDTH);
        }

        batch.end();

        mapRenderer.render(new int[]{2});
    }

    private class EntityPositionComparator implements Comparator<Entity>{

        @Override
        public int compare(Entity entity1, Entity entity2) {
            float value = ComponentMappers.position.get(entity1).getPosition().y -
                    ComponentMappers.position.get(entity2).getPosition().y;
            if(value > 0)
                return -1;
            else if(value == 0)
                return 0;
            else
                return 1;
        }
    }

}
