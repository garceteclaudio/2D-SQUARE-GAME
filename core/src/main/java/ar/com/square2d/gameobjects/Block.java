package ar.com.square2d.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Block extends GameObject{
    private float xPosition;
    private float yPosition;
    private ShapeRenderer shapeRenderer;
    private float height;
    private float width;

    public Block(float x, float y, float height, float width){
        this.xPosition = x;
        this.yPosition = y;
        this.height = height;
        this.width = width;
        shapeRenderer = new ShapeRenderer();
    }


    @Override
    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.BLUE);
            shapeRenderer.rect(this.xPosition, this.yPosition, this.width, this.height);
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

    public float getxPosition() {
        return xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }
}
