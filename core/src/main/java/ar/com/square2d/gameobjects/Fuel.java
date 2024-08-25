package ar.com.square2d.gameobjects;


import ar.com.square2d.utils.DIMENSION;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Fuel extends GameObject{
    private float xPosition;
    private float yPosition;
    private ShapeRenderer shapeRenderer;
    private static final float height = 30;
    private static final float width = 30;
    private static final int SCREEN_WIDTH = DIMENSION.WIDTH;
    private static final int SCREEN_HEIGHT = DIMENSION.HEIGHT;

    public Fuel(float x, float y){
        this.xPosition = x;
        this.yPosition = y;
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.YELLOW);
            shapeRenderer.rect(xPosition, yPosition, width, height);
        shapeRenderer.end();
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void dispose() {
        if (shapeRenderer != null) {
            shapeRenderer.dispose();
        }
    }
}
