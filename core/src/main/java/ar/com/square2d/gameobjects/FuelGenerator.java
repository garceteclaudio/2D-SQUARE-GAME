package ar.com.square2d.gameobjects;

import ar.com.square2d.utils.DIMENSION;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;

import static com.badlogic.gdx.math.MathUtils.random;

public class FuelGenerator extends GameObject{
    private ArrayList<Fuel> fuels;
    private ArrayList<Rectangle> fuelGeneratorRectangleList;
    private Player player;

    public FuelGenerator(int fuelQuantity, Player player){
        this.player = player;
        fuels = new ArrayList<Fuel>();
        fuelGeneratorRectangleList = new ArrayList<Rectangle>();
        for (int i=1; i<=fuelQuantity;i++){
            float randomFloatX = random.nextFloat() * DIMENSION.HEIGHT;
            float randomFloatY = random.nextFloat() * DIMENSION.HEIGHT;
            fuels.add(new Fuel(randomFloatX,randomFloatY));
            fuelGeneratorRectangleList.add(new Rectangle(randomFloatX, randomFloatY,30,30));
        }
    }

    public void checkColissionWithPlayer(Rectangle playerRectangle) {
        Iterator<Fuel> fuelIterator = fuels.iterator();
        Iterator<Rectangle> rectangleIterator = fuelGeneratorRectangleList.iterator();
        while (rectangleIterator.hasNext() && fuelIterator.hasNext()) {
            Rectangle rectangle = rectangleIterator.next();
            Fuel fuel = fuelIterator.next();
            if (rectangle.overlaps(playerRectangle)) {
                // Elimina el cuadrado colisionado de las listas
                player.setPickupItem(1);
                rectangleIterator.remove();
                fuelIterator.remove();
            }
        }
    }


    @Override
    public void render() {
        for (Fuel fuel: fuels) {
            fuel.render();
        }
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void dispose() {
        for (Fuel fuel: fuels) {
            fuel.dispose();
        }
    }
}
