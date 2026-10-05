package Spotify;

public class Client {
    public static void main(String[] args) {
        // Artist
        Artist arijit =
                new Artist(
                        "A1",
                        "Arijit Singh"
                );

        // Album
        Album album =
                new Album(
                        "AL1",
                        "Soulful",
                        arijit
                );

        // Songs
        Song song1 =
                new Song(
                        "S1",
                        "Tum Hi Ho",
                        arijit,
                        album,
                        260
                );

        Song song2 =
                new Song(
                        "S2",
                        "Channa Mereya",
                        arijit,
                        album,
                        280
                );

        Song song3 =
                new Song(
                        "S3",
                        "Agar Tum Saath Ho",
                        arijit,
                        album,
                        300
                );

        album.addSong(song1);
        album.addSong(song2);
        album.addSong(song3);

        arijit.addAlbum(album);

        arijit.addSong(song1);
        arijit.addSong(song2);
        arijit.addSong(song3);

        // User
        User user =
                new User(
                        "U1",
                        "Zia"
                );

        user.likeSong(song1);

        Playlist playlist =
                user.createPlaylist(
                        "P1",
                        "My Playlist"
                );

        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);

        // Player
        MusicPlayer player =
                new MusicPlayer();

        player.addToQueue(
                playlist.getSongs()
        );

        // Play
        player.play();

        // Pause
        player.pause();

        // Resume
        player.play();

        // Next
        player.next();

        // Shuffle
        player.setStrategy(
                new ShufflePlaybackStrategy()
        );

        player.next();

        // Repeat current song
        player.setStrategy(
                new RepeatOneStrategy()
        );

        player.next();
    }
}
