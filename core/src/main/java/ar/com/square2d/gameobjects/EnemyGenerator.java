package ar.com.square2d.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

public class EnemyGenerator extends GameObject{
    private ArrayList<Enemy> enemies;

    public EnemyGenerator(int enemyQuantity){
        enemies = new ArrayList<Enemy>();
        for (int i=1; i<=enemyQuantity;i++){
            float randomFloatX = random.nextFloat() * 600;
            float randomFloatY = random.nextFloat() * 600;
            float randomFloatSpeedX = random.nextFloat() * 300;
            float randomFloatSpeedY = random.nextFloat() * 420;
            enemies.add(new Enemy(randomFloatX,randomFloatY,randomFloatSpeedX,randomFloatSpeedY, generateRandomColor()));
        }
    }

    @Override
    public void render() {
        for (Enemy enemy: enemies) {
            enemy.render();
        }
    }

    @Override
    public void update(float deltaTime) {
        for (Enemy enemy: enemies) {
            enemy.update(deltaTime);
        }
    }

    @Override
    public void dispose() {
        for (Enemy enemy: enemies) {
            enemy.dispose();
        }
    }

    private static Color generateRandomColor() {
        Random random = new Random();

        // Generar valores aleatorios para cada componente RGB (0.0 a 1.0)
        float red = random.nextFloat();   // Rango 0.0 - 1.0
        float green = random.nextFloat(); // Rango 0.0 - 1.0
        float blue = random.nextFloat();  // Rango 0.0 - 1.0

        // Crear un nuevo color con los valores aleatorios generados
        return new Color(red, green, blue, 1.0f); // Alfa (opacidad) es 1.0 (totalmente opaco)
    }

    public void checkCollisionsWithCar(Car car) {
        for (Enemy enemy : enemies) {
            car.checkCollisionWithEnemy(enemy);
        }
    }

    public void checkCollisionsWithBlocks(List<Block> blocks) {
        for (Enemy enemy : enemies) {
            Rectangle enemyRect = enemy.getRectangle();

            for (Block block : blocks) {
                Rectangle blockRect = new Rectangle(block.getxPosition(), block.getyPosition(), block.getWidth(), block.getHeight());

                if (enemyRect.overlaps(blockRect)) {
                    // Determina la dirección de la colisión y ajusta la velocidad del enemigo
                    float overlapLeft = enemyRect.x + enemyRect.width - blockRect.x;
                    float overlapRight = blockRect.x + blockRect.width - enemyRect.x;
                    float overlapTop = blockRect.y + blockRect.height - enemyRect.y;
                    float overlapBottom = enemyRect.y + enemyRect.height - blockRect.y;

                    float minOverlapX = Math.min(overlapLeft, overlapRight);
                    float minOverlapY = Math.min(overlapTop, overlapBottom);

                    if (minOverlapX < minOverlapY) {
                        enemy.reverseXSpeed();
                    } else {
                        enemy.reverseYSpeed();
                    }

                    // Ajusta la posición del enemigo para evitar que quede atrapado en el bloque
                    if (minOverlapX < minOverlapY) {
                        if (overlapLeft < overlapRight) {
                            enemy.setPosition(blockRect.x - enemyRect.width, enemyRect.y);
                        } else {
                            enemy.setPosition(blockRect.x + blockRect.width, enemyRect.y);
                        }
                    } else {
                        if (overlapTop < overlapBottom) {
                            enemy.setPosition(enemyRect.x, blockRect.y + blockRect.height);
                        } else {
                            enemy.setPosition(enemyRect.x, blockRect.y - enemyRect.height);
                        }
                    }

                    break; // Sale del loop para evitar múltiples colisiones simultáneas
                }
            }
        }
    }


}
