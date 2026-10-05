package Spotify;


/*

User Management: Users can register, log in, and manage their profiles.
Browse & Search: Users can browse and search for songs, albums, and artists.
Playlists: Users can create, update, and manage playlists.
Playback Controls: Users can play, pause, skip, and seek within songs.
Recommendations: The system recommends songs and playlists based on user preferences and listening history.
Follow Artists: Users can follow artists to get updates and recommendations.

*/

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* ======== Domain ======== */

enum PlayBackState{
    Playing, Paused, Skipped
}

class User{
    String id;
    String userName;
    String email;
    List<String> playListIds;
    List<String> artistFollowingIds;
}

class Playlist{
    String id;
    String name;
    List<String> songIds;
}

class Artist{
    String id;
    String name;
    List<String> songIds;
}

class Song{
    String id;
    String name;
    String artistId;
    double duration;
}

/* ======== Repository ======== */

class UserRepository{
    Map<String,User> usersDb;

    void addUser(User user){

    }

    User getUser(String id){

    }
}

class ArtistRepository{
    Map<String,Artist> artistDb;

    void addArtist(Artist artist){

    }

    Artist getArtist(String id){

    }
}

class PlaylistRepository{
    Map<String,Playlist> playlistDb;

    void addPlaylist(Playlist playlist){

    }

    Playlist getPlaylist(String id){

    }
}

class SongRepository{
    Map<String,Song> usersDb;

    void addSong(Song song){

    }

    Song getSong(String id){

    }

    List<Song> getAllSongs(){

    }
}

/* ======== Search +  Recommendation Strategy ======== */

interface SearchStrategy{
    List<Song> search(List<Song> songs, String query);
}

class SearchBySongName implements SearchStrategy{
    @Override
    public List<Song> search(List<Song> songs, String query){

    }
}

class SearchByArtistName implements SearchStrategy{
    @Override
    public List<Song> search(List<Song> songs, String query){

    }
}

interface RecommendationStrategy{
    List<Song> recommend(List<Song> songs, String userId);
}

class RecommentByFollwedArtist implements RecommendationStrategy{
    @Override
    public List<Song> recommend(List<Song> songs, String userId){

    }
}

class RecommentTopTrendingSongs implements RecommendationStrategy{
    @Override
    public List<Song> recommend(List<Song> songs, String userId){

    }
}

/* ======== Services ======== */

class SearchService{
    SongRepository songRepository;
    SearchStrategy strategy;

    SearchService(SearchStrategy strategy){
        this.strategy=strategy;
    }

    List<Song> search(String query){
        return strategy.search(songRepository.getAllSongs(),query);
    }
}

class RecommendationService{
    SongRepository songRepository;
    RecommendationStrategy strategy;

    List<Song> recommend(String userId){
        return strategy.recommend(songRepository.getAllSongs(),userId);
    }
}

class PlayListService{
    UserRepository userRepository;
    PlaylistRepository playlistRepository;

    Playlist createPlaylist(String userId, String name){
        Playlist playlist=new Playlist();
        playlist.name=name;
        playlistRepository.addPlaylist(playlist);
        User user=userRepository.getUser(userId);
        user.playListIds.add(playlist.id);
        return playlist;
    }

    void addToPlayList(String userId, String playlistId, String songId){
        Playlist playlist=playlistRepository.getPlaylist(playlistId);
        playlist.songIds.add(songId);
    }

    void removeFromPlayList(String userId, String playlistId, String songId){
        Playlist playlist=playlistRepository.getPlaylist(playlistId);
        playlist.songIds.remove(songId);
    }

    List<String> getSongsInPlayList(String playlistId){
        Playlist playlist=playlistRepository.getPlaylist(playlistId);
        return new ArrayList<>(playlist.songIds);
    }
}

class PlayBackSession{
    String userId;
    String currsongId;
    PlayBackState playBackState;
    double positionseconds;

    PlayBackSession(String userId){
        this.userId=userId;
    }
}

class PlayBackService{
    SongRepository songRepository;
    Map<String,PlayBackSession> sessionMap; // userId , playbacksession

    void play(String userId, String songId){
        Song song=songRepository.getSong(songId);
        PlayBackSession session=sessionMap.get(userId);
        if(session==null){
            session=new PlayBackSession(userId);
            sessionMap.put(userId,session);
        }
        session.currsongId=songId;
        session.playBackState=PlayBackState.Playing;
    }

    void pause(String userId){
        PlayBackSession session=sessionMap.get(userId);
        if(session==null || session.playBackState!=PlayBackState.Playing){
            System.out.println("Nothing to pause");
            return;
        }
        session.playBackState=PlayBackState.Paused;
    }

    void resume(String userId){
        PlayBackSession session=sessionMap.get(userId);
        if(session==null || session.playBackState!=PlayBackState.Paused){
            System.out.println("Nothing to resume");
            return;
        }
        session.playBackState=PlayBackState.Playing;
    }

    void seek(String userId, double positionSeconds){
        PlayBackSession session=sessionMap.get(userId);
        Song song=songRepository.getSong(session.currsongId);
        session.positionseconds=positionSeconds;
    }
}

class SpotifyService{
    UserRepository userRepository;
    ArtistRepository artistRepository;
    PlaylistRepository playlistRepository;
    SongRepository songRepository;

    SearchService searchService;
    RecommendationService recommendationService;
    PlayListService playListService;
    PlayBackService playBackService;

    void followArtist(String userId, String artistId){
        User user=userRepository.getUser(userId);
        boolean alreadyFollowing= user.artistFollowingIds.contains(artistId);
        if(!alreadyFollowing) user.artistFollowingIds.add(artistId);
    }

    void unfollowArtist(String userId, String artistId){
        User user=userRepository.getUser(userId);
        user.artistFollowingIds.remove(artistId);
    }

    //playlist ops
    Playlist createPlaylist(String userId, String playlistName){
        return playListService.createPlaylist(userId,playlistName);
    }

    //playback ops
    void playSong(String userId, String songId){
        playBackService.play(userId,songId);
    }

    //search
    List<Song> searchBySongName(String query){
        searchService.strategy=new SearchBySongName();
        return searchService.search(query);
    }

    List<Song> searchByArtistName(String query){
        searchService.strategy=new SearchByArtistName();
        return searchService.search(query);
    }

    //recommend
    List<Song> recommendByFollwedArtist(String userId){
        recommendationService.strategy=new RecommentByFollwedArtist();
        return recommendationService.recommend(userId);
    }
}

/* ======== Controller ======== */

class SpotifyController{
    SpotifyService service;

    void followArtist(String userId, String artistId){
        service.followArtist(userId,artistId);
    }

    void unfollowArtist(String userId, String artistId){
        service.unfollowArtist(userId,artistId);
    }

    //playlist ops
    Playlist createPlaylist(String userId, String playlistName){
        return service.createPlaylist(userId,playlistName);
    }

    //playback ops
    void playSong(String userId, String songId){
        service.playSong(userId,songId);
    }

    //search
    List<Song> searchBySongName(String query){
        return service.searchBySongName(query);
    }

    List<Song> searchByArtistName(String query){
        return service.searchByArtistName(query);
    }

    //recommend
    List<Song> recommendByFollwedArtist(String userId){
        return service.recommendByFollwedArtist(userId);
    }

}

/* ======== Client Code ======== */
public class Solution2 {
    public static void main(String[] args) {
        
    }
}
