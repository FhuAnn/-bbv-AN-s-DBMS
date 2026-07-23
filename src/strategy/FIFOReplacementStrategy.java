package strategy;

import java.util.Collection;

import classes.storageengine.BufferFrame;

public class FIFOReplacementStrategy
        implements IPageReplacementStrategy {

    public FIFOReplacementStrategy() {
        // TODO: Implement
    }

    @Override
    public void onPageLoaded(BufferFrame frame) {
        // TODO: Implement
    }

    @Override
    public void onPageAccessed(BufferFrame frame) {
        // TODO: Implement
    }

    @Override
    public void onPageRemoved(BufferFrame frame) {
        // TODO: Implement
    }

    @Override
    public BufferFrame selectVictim(
            Collection<BufferFrame> frames) {
        return null;
    }
}