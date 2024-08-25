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

public class ScreenConCamaraSprite implements Screen {
    private SpriteBatch batch;
    private Texture squareTexture;
    private ShapeRenderer shapeRenderer; // Para dibujar la cuadrícula y los obstáculos
    private PrincipalScreen game;
    private int[][] map;
    private int tileSize = 16; // Tamaño de cada tile en píxeles

    private int mapWidth = 30; // Ancho del mapa en tiles
    private int mapHeight = 30; // Alto del mapa en tiles

    private float playerX = 0; // Posición inicial del jugador en la matriz
    private float playerY = 0;

    private OrthographicCamera camera; // Cámara para seguir al jugador

    private float playerSpeed = 80f; // Velocidad del jugador en píxeles por segundo

    // configurar zoom
    float zoom = 2f;

    public ScreenConCamaraSprite(PrincipalScreen game) {
        this.game = game;
    }

    private void handleInput(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.W) && playerY < (mapHeight - 1) * tileSize) {
            playerY += playerSpeed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) && playerY > 0) {
            playerY -= playerSpeed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && playerX > 0) {
            playerX -= playerSpeed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && playerX < (mapWidth - 1) * tileSize) {
            playerX += playerSpeed * deltaTime;
        }

        // Actualiza la posición de la cámara para seguir al jugador
        camera.position.set(playerX + tileSize / 2, playerY + tileSize / 2, 0);
        camera.update();
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
        // Obten el deltaTime
        float deltaTime = Gdx.graphics.getDeltaTime();

        // Procesa la entrada del usuario
        handleInput(deltaTime);

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
        batch.draw(squareTexture, playerX, playerY, tileSize, tileSize);
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
