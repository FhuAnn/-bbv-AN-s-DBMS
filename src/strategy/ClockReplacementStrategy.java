package strategy;

import java.util.Collection;

import classes.storageengine.BufferFrame;

public class ClockReplacementStrategy
        implements IPageReplacementStrategy {

    private int clockHand;

    public ClockReplacementStrategy() {
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
