package pl.edu.agh.weplay.domain.playlist;

/**
 * Created by Roksana on 2016-10-18.
 */
public class SpotifyParams {

    private int energy;
    private int hot;
    private int activity;
    private int mood;

    public int getEnergy() {
        return energy;
    }

    public SpotifyParams setEnergy(int energy) {
        this.energy = energy;
        return this;
    }

    public int getHot() {
        return hot;
    }

    public SpotifyParams setHot(int hot) {
        this.hot = hot;
        return this;
    }

    public int getActivity() {
        return activity;
    }

    public SpotifyParams setActivity(int activity) {
        this.activity = activity;
        return this;
    }

    public int getMood() {
        return mood;
    }

    public SpotifyParams setMood(int mood) {
        this.mood = mood;
        return this;
    }
}
