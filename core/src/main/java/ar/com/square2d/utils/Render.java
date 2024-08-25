package ar.com.square2d.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Render {
    public static SpriteBatch batch;

    public static void limpiarPantalla(){
        ScreenUtils.clear(0, 0, 0, 1);
        //Gdx.gl.glClearColor(0   ,0,0,0);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
