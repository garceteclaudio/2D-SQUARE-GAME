package ar.com.square2d.screens;


import ar.com.square2d.utils.Render;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen implements Screen {
    private MyGame game;
    private SpriteBatch batch;
    private BitmapFont font;

    public MainMenuScreen(MyGame game) {
        this.game = game;
        this.batch = Render.batch;
        this.font = new BitmapFont(); // Fuente por defecto
    }

    @Override
    public void show() {
        // Configuración inicial de la pantalla de inicio
        font.getData().setScale(2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
            font.draw(batch, "Presiona Enter para comenzar el juego", 100, 150);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        // Código para manejar el redimensionado de la ventana
    }

    @Override
    public void pause() {
        // Código para pausar la pantalla
    }

    @Override
    public void resume() {
        // Código para reanudar la pantalla
    }

    @Override
    public void hide() {
        // Código para ocultar la pantalla
    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}
