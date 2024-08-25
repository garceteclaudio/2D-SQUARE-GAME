package ar.com.square2d.gameobjects;


import ar.com.square2d.utils.DIMENSION;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Player extends GameObject {
    private float xPosition;
    private float yPosition;
    private float velocity;
    private ShapeRenderer shapeRenderer;
    private static final float height = 40;
    private static final float width = 40;
    private static final int SCREEN_WIDTH = DIMENSION.WIDTH;
    private static final int SCREEN_HEIGHT = DIMENSION.HEIGHT;
    private Rectangle playerRectangle;
    private float pickupItem;

    public Player() {
        xPosition = 350;
        yPosition = 240;
        velocity = 350;
        shapeRenderer = new ShapeRenderer();
        playerRectangle = new Rectangle(xPosition, yPosition, width, height);
    }

    @Override
    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(xPosition, yPosition, width, height);
        shapeRenderer.end();
    }

    @Override
    public void update(float deltaTime) {
        // Mueve el coche con las teclas WSAD
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            yPosition += velocity * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            yPosition -= velocity * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xPosition -= velocity * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xPosition += velocity * deltaTime;
        }

        // Limita el coche para que no salga de la pantalla
        if (xPosition < 0) {
            xPosition = 0;
        }
        if (xPosition > SCREEN_WIDTH - width) {
            xPosition = SCREEN_WIDTH - width;
        }
        if (yPosition < 0) {
            yPosition = 0;
        }
        if (yPosition > SCREEN_HEIGHT - height) {
            yPosition = SCREEN_HEIGHT - height;
        }

        // Actualiza la posición del rectángulo del coche
        playerRectangle.setPosition(xPosition, yPosition);
    }

    @Override
    public void dispose() {
        if (shapeRenderer != null) {
            shapeRenderer.dispose();
        }
    }

    public void checkCollisionWithBlock(Block block) {
        Rectangle blockRectangle = new Rectangle(block.getxPosition(), block.getyPosition(), block.getWidth(), block.getHeight());

        if (playerRectangle.overlaps(blockRectangle)) {
            // Calcula las posiciones relativas
            float carLeft = playerRectangle.getX();
            float carRight = playerRectangle.getX() + playerRectangle.getWidth();
            float carTop = playerRectangle.getY() + playerRectangle.getHeight();
            float carBottom = playerRectangle.getY();

            float blockLeft = blockRectangle.getX();
            float blockRight = blockRectangle.getX() + blockRectangle.getWidth();
            float blockTop = blockRectangle.getY() + blockRectangle.getHeight();
            float blockBottom = blockRectangle.getY();

            // Determina la dirección de la colisión y ajusta la posición del coche
            float overlapLeft = carRight - blockLeft;
            float overlapRight = blockRight - carLeft;
            float overlapTop = blockTop - carBottom;
            float overlapBottom = carTop - blockBottom;

            float minOverlapX = Math.min(overlapLeft, overlapRight);
            float minOverlapY = Math.min(overlapTop, overlapBottom);

            if (minOverlapX < minOverlapY) {
                if (overlapLeft < overlapRight) {
                    // Colisión desde la izquierda
                    xPosition = blockLeft - playerRectangle.getWidth();
                } else {
                    // Colisión desde la derecha
                    xPosition = blockRight;
                }
            } else {
                if (overlapTop < overlapBottom) {
                    // Colisión desde abajo
                    yPosition = blockTop;
                } else {
                    // Colisión desde arriba
                    yPosition = blockBottom - playerRectangle.getHeight();
                }
            }

            // Actualiza la posición del rectángulo del coche después de la colisión
            playerRectangle.setPosition(xPosition, yPosition);
        }
    }


    public void checkCollisionWithEnemy(Enemy enemy) {
        Rectangle enemyRectangle = new Rectangle(enemy.getxPosition(), enemy.getyPosition(), 40, 40);

        if (playerRectangle.overlaps(enemyRectangle)) {
            System.out.println("Colisión con enemigo detectada!!!");

            // Calcula las posiciones relativas
            float carLeft = playerRectangle.getX();
            float carRight = playerRectangle.getX() + playerRectangle.getWidth();
            float carTop = playerRectangle.getY() + playerRectangle.getHeight();
            float carBottom = playerRectangle.getY();

            float enemyLeft = enemyRectangle.getX();
            float enemyRight = enemyRectangle.getX() + enemyRectangle.getWidth();
            float enemyTop = enemyRectangle.getY() + enemyRectangle.getHeight();
            float enemyBottom = enemyRectangle.getY();

            // Determina la dirección de la colisión y ajusta la posición del coche
            float overlapLeft = carRight - enemyLeft;
            float overlapRight = enemyRight - carLeft;
            float overlapTop = enemyTop - carBottom;
            float overlapBottom = carTop - enemyBottom;

            float minOverlapX = Math.min(overlapLeft, overlapRight);
            float minOverlapY = Math.min(overlapTop, overlapBottom);

            if (minOverlapX < minOverlapY) {
                if (overlapLeft < overlapRight) {
                    // Colisión desde la izquierda
                    xPosition = enemyLeft - playerRectangle.getWidth();
                } else {
                    // Colisión desde la derecha
                    xPosition = enemyRight;
                }
            } else {
                if (overlapTop < overlapBottom) {
                    // Colisión desde abajo
                    yPosition = enemyTop;
                } else {
                    // Colisión desde arriba
                    yPosition = enemyBottom - playerRectangle.getHeight();
                }
            }

            // Actualiza la posición del rectángulo del coche después de la colisión
            playerRectangle.setPosition(xPosition, yPosition);
        }
    }

    public float getPickupItem() {
        return pickupItem;
    }

    public void setPickupItem(int pickupItem) {
        this.pickupItem += pickupItem;
    }

    public float getxPosition() {
        return xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public Rectangle getPlayerRectangle() {
        return playerRectangle;
    }
}
