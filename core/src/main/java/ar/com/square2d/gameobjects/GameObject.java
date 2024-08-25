package ar.com.square2d.gameobjects;

public abstract class GameObject {

    public abstract void render();
    public abstract void update(float deltaTime);
    public abstract void dispose();
}
