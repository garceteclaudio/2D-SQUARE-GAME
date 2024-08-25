package ar.com.square2d.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends GameObject {
    private float xPosition;
    private float yPosition;
    private float speedX;
    private float speedY;
    private ShapeRenderer shapeRenderer;
    private Rectangle enemyRectangle;

    // Agregar constantes para los límites del mapa
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int ENEMY_SIZE = 40; // Asumimos que el tamaño del enemigo es 40x40

    public Enemy(float x, float y, float speedX, float speedY, Color color) {
        this.xPosition = x;
        this.yPosition = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.shapeRenderer = new ShapeRenderer();
        this.enemyRectangle = new Rectangle(x, y, ENEMY_SIZE, ENEMY_SIZE);
    }

    @Override
    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(xPosition, yPosition, ENEMY_SIZE, ENEMY_SIZE);
        shapeRenderer.end();
    }

    public float getxPosition() {
        return xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public Rectangle getEnemyRectangle() {
        return enemyRectangle;
    }

    @Override
    public void update(float deltaTime) {
        xPosition += speedX * deltaTime;
        yPosition += speedY * deltaTime;

        // Verificar colisión con los bordes del mapa y revertir velocidad si es necesario
        if (xPosition < 0) {
            xPosition = 0;
            reverseXSpeed();
        } else if (xPosition + ENEMY_SIZE > SCREEN_WIDTH) {
            xPosition = SCREEN_WIDTH - ENEMY_SIZE;
            reverseXSpeed();
        }

        if (yPosition < 0) {
            yPosition = 0;
            reverseYSpeed();
        } else if (yPosition + ENEMY_SIZE > SCREEN_HEIGHT) {
            yPosition = SCREEN_HEIGHT - ENEMY_SIZE;
            reverseYSpeed();
        }

        enemyRectangle.setPosition(xPosition, yPosition);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    public Rectangle getRectangle() {
        return enemyRectangle;
    }

    public void reverseXSpeed() {
        speedX = -speedX;
    }

    public void reverseYSpeed() {
        speedY = -speedY;
    }

    public void setPosition(float x, float y) {
        this.xPosition = x;
        this.yPosition = y;
        enemyRectangle.setPosition(x, y);
    }
}
