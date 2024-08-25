package ar.com.square2d.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
/// MAPA SIN CAMARA
public class Mapa implements Screen {
    private SpriteBatch batch;
    private Texture squareTexture;
    private ShapeRenderer shapeRenderer; // Para dibujar la cuadrícula
    private PrincipalScreen game;
    private int[][] map;
    private int tileSize = 32; // Tamaño de cada tile en píxeles
    private int mapWidth = 30; // Ancho del mapa en tiles   32X30: 960
    private int mapHeight = 20; // Alto del mapa en tiles 32X20: 640

    private int playerX = 5; // Posición inicial del jugador en la matriz
    private int playerY = 5;

    public Mapa(PrincipalScreen game){
        this.game = game;
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W) && playerY < mapHeight - 1) {
            playerY++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) && playerY > 0) {
            playerY--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && playerX > 0) {
            playerX--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && playerX < mapWidth - 1) {
            playerX++;
        }
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer(); // Inicializa el ShapeRenderer
        squareTexture = new Texture("square.png"); // Debes tener una textura para el jugador

        // Inicializa el mapa con ceros (por ejemplo, 0 puede representar un tile vacío)
        map = new int[mapWidth][mapHeight];

        // Aquí podrías personalizar el mapa, por ejemplo:
        map[2][2] = 1; // Este podría representar un obstáculo o algo similar
    }

    @Override
    public void render(float v) {
        // Procesa la entrada del usuario
        handleInput();

        // Limpiar pantalla
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Dibuja el mapa
        batch.begin();
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                // Puedes dibujar diferentes texturas dependiendo del valor en la matriz
                if (map[x][y] == 0) {
                    // Aquí podrías dibujar el fondo
                } else if (map[x][y] == 1) {
                    // Aquí podrías dibujar un obstáculo
                }
            }
        }

        // Dibuja el jugador
        batch.draw(squareTexture, playerX * tileSize, playerY * tileSize, tileSize, tileSize);
        batch.end();

        // Dibuja la cuadrícula
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        for (int x = 0; x <= mapWidth; x++) {
            for (int y = 0; y <= mapHeight; y++) {
                shapeRenderer.rect(x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }
        shapeRenderer.end();
    }

    @Override
    public void resize(int i, int i1) {

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
