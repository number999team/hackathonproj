package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by dimamir999 on 01.12.15.
 */
public class GameWorld {

    public static final float TILED_WIDTH = 40;
    public static final float TILED_HEIGTH = 30;

    private TiledMap map;
    private Hero hero;
    private Array<Monster> monsters = new Array<Monster>();
    private Pool<Monster> monsterPool;
    private SpawnPoint spawnPoint;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private HeroInputListener inputListener;

    public GameWorld(TiledMap map, Hero hero) {
        this.map = map;
        this.hero = hero;
        createWorld();
    }

    private void createWorld(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, TILED_WIDTH, TILED_HEIGTH);
        camera.update();
        renderer = new OrthogonalTiledMapRenderer(map, 1 / 16f);
        renderer.setView(camera);
        final Texture monsterTexture = new Texture(Gdx.files.internal("People.png"));
        monsterPool = new Pool<Monster>() {
            @Override
            protected Monster newObject() {
                return new Monster();
            }
        };
        spawnPoint = new SpawnPoint(2f, new Vector2(8f, 33f), new TextureRegion(monsterTexture, 32, 32), hero, monsterPool);
        inputListener = new HeroInputListener(hero, camera);
        Gdx.input.setInputProcessor(inputListener);

    }

    public void render(){
        renderer.render(new int[]{0, 1});
        for(Monster monster : monsters){
            monster.render(renderer.getBatch());
        }
        hero.render(renderer.getBatch());
        renderer.render(new int[]{2});
    }

    public void update(float delta){
        //operation for safety
        renderer.getBatch().setProjectionMatrix(camera.combined);

        hero.update(delta);
        for(Monster monster : monsters){
            monster.update(delta);
        }
        spawnPoint.update(delta, monsters);
        camera.position.x = hero.position.x + Hero.WIDTH / 2;
        camera.position.y = hero.position.y + Hero.HEIGHT / 2;

        camera.update();

        //maybe should be there
        renderer.setView(camera);
    }

    public Hero getHero() {
        return hero;
    }

    public TiledMap getMap() {
        return map;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
