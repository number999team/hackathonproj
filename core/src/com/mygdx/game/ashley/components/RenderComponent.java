package com.mygdx.game.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by dimamir999 on 06.12.15.
 */
public class RenderComponent implements Component{
    TextureRegion view;

    public RenderComponent(TextureRegion view) {
        this.view = view;
    }

    public TextureRegion getView() {
        return view;
    }
}
