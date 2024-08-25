package ar.com.square2d.screens;


import ar.com.square2d.gameobjects.Car;
import ar.com.square2d.utils.DIMENSION;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*
* Hud: se refiere a una interfaz o pantalla donde se muestra información importante al jugador.
*/
public class Hub {
    private SpriteBatch batch;
    private BitmapFont font;
    private Car car;

    public Hub(SpriteBatch batch, Car car){
        this.batch = batch;
        this.font = new BitmapFont();
        this.car = car;
    }

    public void render(){
        int fps = Gdx.graphics.getFramesPerSecond();
        String fpsText = String.format("FPS: %d", fps);
        String xPosText = String.format("X: %.2f", this.car.getxPosition());
        String yPosText = String.format("Y: %.2f", this.car.getyPosition());

        font.draw(this.batch, fpsText, 10, DIMENSION.HEIGHT - 10);
        font.draw(this.batch, xPosText, 10, DIMENSION.HEIGHT - 30);
        font.draw(this.batch, yPosText, 10, DIMENSION.HEIGHT - 50);
    }



    public void dispose(){
        font.dispose();
    }
}
