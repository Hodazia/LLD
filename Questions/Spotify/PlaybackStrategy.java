package Spotify;

import java.util.Random;
import java.util.List;

public interface PlaybackStrategy {
    Song getNextSong(MusicQueue queue,Song currentSong);
}

class NormalPlaybackStrategy implements PlaybackStrategy {

    @Override
    public Song getNextSong(MusicQueue queue, Song currentSong) {
        return queue.remove();
    }
}

class ShufflePlaybackStrategy implements PlaybackStrategy {

    private final Random random = new Random();

    @Override
    public Song getNextSong(MusicQueue queue,Song currentSong) {
        return queue.removeRandom();
    }
}

class RepeatOneStrategy
        implements PlaybackStrategy {

    @Override
    public Song getNextSong(
            MusicQueue queue,
            Song currentSong) {

        return currentSong;
    }
}