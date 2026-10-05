package Spotify;

public interface PlayerState {
    public void play(MusicPlayer player);
    public void pause(MusicPlayer player);
    public void stop(MusicPlayer player);
    public void next(MusicPlayer player);
} 

class StoppedState implements PlayerState {
    // the stop state of the songs

    @Override 
    public void play(MusicPlayer player)
    {
        player.startCurrentSong();
        player.setState(new PlayingState());
    }

    @Override
    public void pause(MusicPlayer player) {
        System.out.println("Player is already stopped.");
    }

    @Override
    public void stop(MusicPlayer player) {
        System.out.println("Already stopped.");
    }

    @Override
    public void next(MusicPlayer player) {
        player.playNext();
    }
}

class PlayingState implements PlayerState {

    @Override
    public void play(MusicPlayer player) {
        System.out.println("Already playing.");
    }

    @Override
    public void pause(MusicPlayer player) {
        System.out.println("Paused: " + player.getCurrentSong());
        player.setState(new PausedState());
    }

    @Override
    public void stop(MusicPlayer player) {
        System.out.println("Stopped.");
        player.setState(new StoppedState());
    }

    @Override
    public void next(
            MusicPlayer player) {

        player.playNext();
    }
}

class PausedState implements PlayerState {

    @Override
    public void play(MusicPlayer player) {

        System.out.println("Resuming: "+ player.getCurrentSong());
        player.setState(new PlayingState());
    }

    @Override
    public void pause(MusicPlayer player) {
        System.out.println("Already paused.");
    }

    @Override
    public void stop(MusicPlayer player) {
        System.out.println("Stopped.");
        player.setState(new StoppedState());
    }

    @Override
    public void next(MusicPlayer player) {
        player.playNext();
        player.setState(new PlayingState());
    }
}