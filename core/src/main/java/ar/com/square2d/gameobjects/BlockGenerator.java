package ar.com.square2d.gameobjects;

import java.util.ArrayList;
import java.util.List;

public class BlockGenerator extends GameObject {
    private List<Block> blocks;

    public BlockGenerator(int rows, int cols, int blockSize, int startX, int[] yPositions) {
        blocks = new ArrayList<>();
        for (int y : yPositions) {
            for (int i = 0; i < cols; i++) {
                blocks.add(new Block(startX + i * blockSize, y, blockSize, blockSize));
            }
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    @Override
    public void render() {
        for (Block block : blocks) {
            block.render();
        }
    }

    @Override
    public void update(float deltaTime) {
        // Update logic for blocks if necessary
    }

    @Override
    public void dispose() {
        for (Block block : blocks) {
            block.dispose();
        }
    }
}
