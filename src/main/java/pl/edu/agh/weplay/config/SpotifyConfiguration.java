package pl.edu.agh.weplay.config;

import com.wrapper.spotify.Api;

/**
 * Created by P on 19.10.2016.
 */
public class SpotifyConfiguration {
    private static final String CLIENT_ID = "1f04ae7d7737482eb47594961c217f15";
    private static final String CLIENT_SECRET = "c9751a4110444a67b141df640960eb60";

    public final static Api API = Api.builder()
            .clientId(CLIENT_ID)
            .clientSecret(CLIENT_SECRET)
            .build();
}
