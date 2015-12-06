package com.mygdx.game.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dimamir999 on 06.12.15.
 */
public class PositionComponent implements Component{

    Vector2 position;

    public PositionComponent(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }
}
