package com.mygdx.game.ashley.core;

import com.badlogic.ashley.core.ComponentMapper;
import com.mygdx.game.ashley.components.CollisionableComponent;
import com.mygdx.game.ashley.components.MoveComponent;
import com.mygdx.game.ashley.components.PositionComponent;
import com.mygdx.game.ashley.components.RenderComponent;

/**
 * Created by dimamir999 on 06.12.15.
 */
public class ComponentMappers {
    public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<RenderComponent> render = ComponentMapper.getFor(RenderComponent.class);
    public static final ComponentMapper<MoveComponent> move = ComponentMapper.getFor(MoveComponent.class);
    public static final ComponentMapper<CollisionableComponent> collision = ComponentMapper.getFor(CollisionableComponent.class);
}
