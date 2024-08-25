package ar.com.square2d.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class ScreenCuadriculaACuadricula implements Screen {
    private SpriteBatch batch;
    private Texture squareTexture;
    private ShapeRenderer shapeRenderer; // Para dibujar la cuadrícula y los obstáculos
    private PrincipalScreen game;
    private int[][] map;
    private int tileSize = 16; // Tamaño de cada tile en píxeles

    private int mapWidth = 30; // Ancho del mapa en tiles
    private int mapHeight = 30; // Alto del mapa en tiles

    private Vector2 currentPos; // Posición actual del jugador en la matriz
    private Vector2 targetPos;  // Posición de destino para el movimiento

    private OrthographicCamera camera; // Cámara para seguir al jugador

    private boolean isMoving = false; // Para controlar si el jugador está en movimiento
    private float moveTime = 0.3f; // Tiempo para moverse de una casilla a otra
    private float moveTimer = 0f; // Temporizador para controlar el movimiento

    // configurar zoom
    float zoom = 2f;

    public ScreenCuadriculaACuadricula(PrincipalScreen game) {
        this.game = game;
        currentPos = new Vector2(0, 0);
        targetPos = new Vector2(0, 0);
    }

    private void handleInput() {
        if (!isMoving) {
            if (Gdx.input.isKeyPressed(Input.Keys.W) && targetPos.y < (mapHeight - 1) * tileSize) {
                targetPos.y += tileSize;
                isMoving = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S) && targetPos.y > 0) {
                targetPos.y -= tileSize;
                isMoving = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A) && targetPos.x > 0) {
                targetPos.x -= tileSize;
                isMoving = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D) && targetPos.x < (mapWidth - 1) * tileSize) {
                targetPos.x += tileSize;
                isMoving = true;
            }
        }
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer(); // Inicializa el ShapeRenderer
        squareTexture = new Texture("personaje.png"); // Debes tener una textura para el jugador

        // Inicializa el mapa con ceros (0 = vacío, 1 = obstáculo)
        map = new int[mapWidth][mapHeight];

        // Inicializa la cámara con mayor zoom y la centra en el jugador
        float zoomFactor = zoom; // Factor de zoom, cuanto mayor es este valor, más cerca estará la cámara
        camera = new OrthographicCamera(Gdx.graphics.getWidth() / zoomFactor, Gdx.graphics.getHeight() / zoomFactor);
        camera.update();

        // Personaliza el mapa con obstáculos (1 representa un obstáculo)
        map[2][2] = 1;
        map[3][3] = 1;
        map[4][4] = 1;
    }

    @Override
    public void render(float delta) {
        // Procesa la entrada del usuario
        handleInput();

        if (isMoving) {
            moveTimer += delta;
            float alpha = moveTimer / moveTime; // Calcula cuánto del movimiento se ha completado
            if (alpha >= 1f) {
                alpha = 1f;
                moveTimer = 0f;
                isMoving = false;
            }
            currentPos.lerp(targetPos, alpha); // Interpola entre la posición actual y la posición de destino
        }

        // Limpiar pantalla
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Configura la cámara para batch y shapeRenderer
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Dibuja el mapa y los obstáculos en rojo
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                // Dibuja obstáculos en rojo
                if (map[x][y] == 1) {
                    shapeRenderer.setColor(Color.RED);
                    shapeRenderer.rect(x * tileSize, y * tileSize, tileSize, tileSize);
                }
            }
        }
        shapeRenderer.end();

        // Dibuja el jugador
        batch.begin();
        batch.draw(squareTexture, currentPos.x, currentPos.y, tileSize, tileSize);
        batch.end();

        // Dibuja la cuadrícula
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        for (int x = 0; x <= mapWidth - 1; x++) {
            for (int y = 0; y <= mapHeight - 1; y++) {
                shapeRenderer.rect(x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }
        shapeRenderer.end();

        // Actualiza la posición de la cámara para seguir al jugador
        camera.position.set(currentPos.x + tileSize / 2, currentPos.y + tileSize / 2, 0);
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        // Reajusta la cámara cuando se cambia el tamaño de la ventana
        float zoomFactor = zoom; // Asegúrate de que coincida con el factor de zoom en show()
        camera.setToOrtho(false, width / zoomFactor, height / zoomFactor);
        camera.update();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        squareTexture.dispose();
        shapeRenderer.dispose(); // Libera recursos de ShapeRenderer
    }
}
