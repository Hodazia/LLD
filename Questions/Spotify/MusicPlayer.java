package Spotify;

import java.util.List;

public class MusicPlayer {
    // 
    private final MusicQueue queue;
    private PlaybackStrategy strategy;
    private PlayerState state;
    private Song currentSong;
    public MusicPlayer()
    {
        queue = new MusicQueue();
        state = new StoppedState();
        strategy = new NormalPlaybackStrategy();
    }

    public synchronized void play()
    {
        state.play(this);
    }
    public synchronized void pause() {
        state.pause(this);
    }

    public synchronized void stop() {
        state.stop(this);
    }

    public synchronized void next() {
        state.next(this);
    }

    public synchronized void addToQueue(Song song) {
        // add a song to queue
        queue.add(song);
    }

    public synchronized void addToQueue(List<Song> songs)
    {
        queue.addAll(songs);
    }
    public synchronized void setStrategy(PlaybackStrategy strategy) 
    {
        this.strategy = strategy;
    }

    public synchronized void startCurrentSong() {

        if (currentSong == null) {
            currentSong = queue.remove();
        }

        if (currentSong == null) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("▶ Playing: " + currentSong);
    }


    public synchronized void playNext() {

        Song next = strategy.getNextSong(queue,currentSong);

        if (next == null) {
            System.out.println("Queue finished.");
            currentSong = null;
            state = new StoppedState();
            return;
        }

        currentSong = next;
        System.out.println("▶ Playing next: " + currentSong);
    }
    public synchronized Song getCurrentSong() {
        return currentSong;
    }

    public synchronized void setState(PlayerState state) {
        this.state = state;
    }

}
