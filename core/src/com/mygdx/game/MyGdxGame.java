package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.ashley.core.EntityManager;

public class MyGdxGame extends ApplicationAdapter {

	//private GameWorld gameWorld;
	private EntityManager entityManager;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 40, 30);
		camera.update();
		map = new TmxMapLoader().load("map_for_test.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 16f);
		renderer.setView(camera);
		entityManager = new EntityManager(renderer, camera);
		/*TiledMap map = new TmxMapLoader().load("map_for_test.tmx");
		Texture heroTexture = new Texture(Gdx.files.internal("Boss.png"));

		Hero hero = new Hero(new TextureRegion(heroTexture, 64, 64));
		gameWorld = new GameWorld(map, hero);*/
	}

	@Override
	public void render () {
		entityManager.update(Gdx.graphics.getDeltaTime());
		/*Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameWorld.update(Gdx.graphics.getDeltaTime());
		gameWorld.render();*/
	}
}
