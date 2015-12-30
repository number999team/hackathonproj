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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.ashley.components.CollisionableComponent;
import com.mygdx.game.ashley.components.PositionComponent;
import com.mygdx.game.ashley.components.RenderComponent;
import com.mygdx.game.ashley.core.ComponentMappers;
import com.mygdx.game.ashley.core.EntityManager;

/**
 * Created by dimamir999 on 29.12.15.
 */
public class CollisionSystem extends EntitySystem{

    private Array<Entity> entities = new Array<Entity>();
    private Pool<Rectangle> boundsPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject() {
            return new Rectangle();
        }
    };

    @Override
    public void addedToEngine(Engine engine) {
        ImmutableArray<Entity> startEntitites = engine.getEntitiesFor(Family.all(CollisionableComponent.class).get());
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
        CollisionableComponent collision;
        for(Entity entity:entities){
            position = ComponentMappers.position.get(entity);
            collision = ComponentMappers.collision.get(entity);

        }
    }
}
