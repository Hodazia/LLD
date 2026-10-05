package Spotify;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class MusicQueue {
    private final Deque<Song> queue = new ArrayDeque<>();

    public synchronized void add(Song song) {
        queue.addLast(song);
    }

    public synchronized void addAll(List<Song> songs) {
        queue.addAll(songs);
    }

    public synchronized Song peek() {
        return queue.peekFirst();
    }

    public synchronized Song remove() {
        return queue.pollFirst();
    }

    public synchronized void addFirst(Song song) {
        queue.addFirst(song);
    }

    public synchronized void clear() {
        queue.clear();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized List<Song> getSongs() {
        return new ArrayList<>(queue);
    }

    public synchronized Song removeRandom() {

        if (queue.isEmpty()) {
            return null;
        }
    
        List<Song> songs = new ArrayList<>(queue);
        int index = new Random().nextInt(songs.size());
        Song selected = songs.get(index);
        queue.remove(selected);    
        return selected;
    }
}
