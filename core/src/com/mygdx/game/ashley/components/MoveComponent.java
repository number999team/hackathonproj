package com.mygdx.game.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dimamir999 on 06.12.15.
 */
public class MoveComponent implements Component{

    private Vector2 prevPosition;
    private Vector2 target;
    private Vector2 direction = new Vector2();
    private float maxSpeed;

    public MoveComponent(Vector2 target, float maxSpeed, Vector2 prevPosition) {
        this.target = target;
        this.maxSpeed = maxSpeed;
        this.prevPosition = prevPosition;
    }

    public Vector2 getTarget() {
        return target;
    }



    public void setTarget(Vector2 target) {
        this.target = target;

    }

    public Vector2 getPrevPosition() {
        return prevPosition;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }
}
