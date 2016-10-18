package pl.edu.agh.weplay.domain.playlist;

import java.util.List;

/**
 * Created by Roksana on 2016-10-18.
 */
public class PlaylistPreferences {
    private List<Friend> friends;
    private List<Song> songs;
    private Activity activity;
    private SpotifyParams params;


    public List<Friend> getFriends() {
        return friends;
    }

    public PlaylistPreferences setFriends(List<Friend> friends) {
        this.friends = friends;
        return this;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public PlaylistPreferences setSongs(List<Song> songs) {
        this.songs = songs;
        return this;
    }

    public Activity getActivity() {
        return activity;
    }

    public PlaylistPreferences setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public SpotifyParams getParams() {
        return params;
    }

    public PlaylistPreferences setParams(SpotifyParams params) {
        this.params = params;
        return this;
    }
}
