package Spotify;

enum SongType {
    AUDIO
}

public class Song {
    private final String id;
    private final String title;
    private final Artist artist;
    private final Album album;
    private final int durationInSeconds;


    public Song(String id,String title,Artist artist,Album album,int durationInSeconds) {

    this.id = id;
    this.title = title;
    this.artist = artist;
    this.album = album;
    this.durationInSeconds = durationInSeconds;
    }

    // getters and setters,
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public Album getAlbum() {
        return album;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    @Override
    public String toString() {
        return title + " - " + artist.getName();
    }

}
