package strategy;

import java.util.Collection;

import classes.storageengine.BufferFrame;

public interface IPageReplacementStrategy {

    void onPageLoaded(BufferFrame frame);

    void onPageAccessed(BufferFrame frame);

    void onPageRemoved(BufferFrame frame);

    BufferFrame selectVictim(
            Collection<BufferFrame> frames
    );
}