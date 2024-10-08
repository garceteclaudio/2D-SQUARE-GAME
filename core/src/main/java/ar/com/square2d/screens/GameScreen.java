package ar.com.square2d.screens;


import ar.com.square2d.gameobjects.*;
import ar.com.square2d.utils.DIMENSION;
import ar.com.square2d.utils.Render;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen implements Screen {
    private MyGame game;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private BitmapFont font;
    private Player player;
    private EnemyGenerator enemyGenerator;
    private FuelGenerator fuelGenerator;
    private BlockGenerator blockGenerator;
    private Hub hub;

    private static final int SCREEN_WIDTH = DIMENSION.WIDTH;
    private static final int SCREEN_HEIGHT = DIMENSION.HEIGHT;

    public GameScreen(MyGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        shapeRenderer = new ShapeRenderer();
        batch = Render.batch;
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);

        player = new Player();

        // Define block configuration
        int[] yPositions = {400, 100,200, 500};
        blockGenerator = new BlockGenerator(12, 12, 40, 0, yPositions);

        enemyGenerator = new EnemyGenerator(5);
        fuelGenerator = new FuelGenerator(5, player);
        hub = new Hub(this.batch, player);
    }

    @Override
    public void render(float delta) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        Render.limpiarPantalla();

        camera.update();
        player.update(deltaTime);
        enemyGenerator.update(deltaTime);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            player.render();
            enemyGenerator.render();
            fuelGenerator.render();
            blockGenerator.render();
        shapeRenderer.end();

        // Handle collisions
        fuelGenerator.checkColissionWithPlayer(player.getPlayerRectangle());
        for (Block block : blockGenerator.getBlocks()) {
            player.checkCollisionWithBlock(block);
        }
        enemyGenerator.checkCollisionsWithPlayer(player);
        enemyGenerator.checkCollisionsWithBlocks(blockGenerator.getBlocks());

        batch.begin();
        hub.render();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Handle resizing
    }

    @Override
    public void pause() {
        // Handle pause
    }

    @Override
    public void resume() {
        // Handle resume
    }

    @Override
    public void hide() {
        // Handle hide
    }

    @Override
    public void dispose() {
        font.dispose();
        shapeRenderer.dispose();
        player.dispose();
        blockGenerator.dispose();
        enemyGenerator.dispose();
        fuelGenerator.dispose();
        hub.dispose();
        batch.dispose();
    }
}
