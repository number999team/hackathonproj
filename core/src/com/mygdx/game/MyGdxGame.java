package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MyGdxGame extends ApplicationAdapter {

	private GameWorld gameWorld;

	@Override
	public void create () {
		TiledMap map = new TmxMapLoader().load("map_for_test.tmx");
		Texture heroTexture = new Texture(Gdx.files.internal("Boss.png"));

		Hero hero = new Hero(new TextureRegion(heroTexture, 64, 64));
		gameWorld = new GameWorld(map, hero);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameWorld.update(Gdx.graphics.getDeltaTime());
		gameWorld.render();
	}
}
