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
import com.mygdx.game.ashley.components.PositionComponent;
import com.mygdx.game.ashley.components.RenderComponent;
import com.mygdx.game.ashley.core.ComponentMappers;

/**
 * Created by dimamir999 on 06.12.15.
 */
public class RenderSystem extends EntitySystem{

    private ImmutableArray<Entity> entities;

    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    public RenderSystem(OrthogonalTiledMapRenderer mapRenderer, OrthographicCamera camera) {
        this.mapRenderer = mapRenderer;
        this.camera = camera;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, RenderComponent.class).get());
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

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        mapRenderer.render(new int[]{0, 1});

        batch.begin();

        for(Entity entity: entities){
            position = ComponentMappers.position.get(entity);
            render = ComponentMappers.render.get(entity);
            batch.draw(render.getView(), position.getPosition().x, position.getPosition().y, 4, 6);
        }

        batch.end();

        mapRenderer.render(new int[]{2});
    }

}
