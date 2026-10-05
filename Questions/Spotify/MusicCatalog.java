package Spotify;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

// controller class
public class MusicCatalog {
    private final Map<String, Song> songs = new HashMap<>();
    private final Map<String, Artist> artists = new HashMap<>();
    private final Map<String, Album> albums = new HashMap<>();

    public void addSong(Song song) {
        songs.put(song.getId(), song);
    }

    public void addArtist(Artist artist) {
        artists.put(
                artist.getName().toLowerCase(),
                artist
        );
    }

    public void addAlbum(Album album) {
        albums.put(
                album.getTitle().toLowerCase(),
                album
        );
    }

    public List<Song> searchSongs(String query) {

        String q =
                query.toLowerCase();

        return songs.values()
                .stream()
                .filter(song ->
                        song.getTitle()
                                .toLowerCase()
                                .contains(q))
                .toList();
    }
}
