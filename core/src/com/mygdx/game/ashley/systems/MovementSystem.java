package com.mygdx.game.ashley.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import com.mygdx.game.ashley.components.PositionComponent;
import com.mygdx.game.ashley.components.MoveComponent;
import com.mygdx.game.ashley.core.ComponentMappers;

/**
 * Created by dimamir999 on 06.12.15.
 */
public class MovementSystem extends EntitySystem{

    private ImmutableArray<Entity> entities;
    private Entity hero;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer mapRenderer;

    public MovementSystem(Entity hero, OrthographicCamera camera, OrthogonalTiledMapRenderer mapRenderer) {
        this.hero = hero;
        this.camera = camera;
        this.mapRenderer = mapRenderer;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, MoveComponent.class).get());
    }

    @Override
    public void update(float delta) {
        PositionComponent position;
        MoveComponent move;

        for(Entity entity: entities){
            position = ComponentMappers.position.get(entity);
            move = ComponentMappers.move.get(entity);

            Vector2 prevPos = move.getPrevPosition();
            Vector2 curPos = position.getPosition();
            Vector2 target = move.getTarget();
            Vector2 direction = move.getDirection();

            if((Math.max(prevPos.x, curPos.x) >= target.x && Math.min(prevPos.x, curPos.x) <= target.x)
                    | (Math.max(prevPos.y, curPos.y) >= target.y && Math.min(prevPos.y, curPos.y) <= target.y)){
                curPos.set(target);
            } else {
                move.getPrevPosition().set(curPos);
                direction.set(target);
                direction.sub(curPos);
                direction.setLength(move.getMaxSpeed());
                position.getPosition().add(move.getDirection().scl(delta));
                move.getDirection().scl(1 / delta);
            }
        }
        updateCamera();
    }

    private void updateCamera(){
        camera.position.set(ComponentMappers.position.get(hero).getPosition().x,
                ComponentMappers.position.get(hero).getPosition().y, 0);
        camera.update();
        //maybe should be there
        mapRenderer.setView(camera);
    }
}
