package ar.com.square2d.screens;


import ar.com.square2d.utils.Render;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends Game {


    @Override
    public void create() {
        Render.batch = new SpriteBatch();
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void dispose() {
        Render.batch.dispose();
        super.dispose();
    }
}
