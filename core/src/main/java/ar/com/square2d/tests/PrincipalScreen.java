package ar.com.square2d.tests;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PrincipalScreen extends Game {
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        // Inicializar la pantalla de inicio Mapa
        // ScreenConCamaraCuadrado
        setScreen(new ScreenConCamaraSprite(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }
}
