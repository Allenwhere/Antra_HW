package Week3;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.MatcherAssert.assertThat;


interface SongCache {

    /**
     * Record number of plays for a song.
     */
    void recordSongPlays(String songId, int
            numPlays);

    /**
     * Fetch the number of plays for a song.
     *
     * @return the number of plays, or -1 if the
    song ID is unknown.
     */
    int getPlaysForSong(String songId);

    /**
     * Return the top N songs played, in descending
     order of number of plays.
     */
    List<String> getTopNSongsPlayed(int n);
}

public class SongCacheImpl implements SongCache {

    private static Map<String, Integer> songs = new ConcurrentHashMap<String, Integer>();

    /**
     * Record number of plays for a song.
     *
     * @param songId
     * @param numPlays
     */
    @Override
    public void recordSongPlays(String songId, int numPlays) {
        songs.compute(songId, (k, v) -> (v == null) ? numPlays : v + 1);
    }

    /**
     * Fetch the number of plays for a song.
     *
     * @param songId
     * @return the number of plays, or -1 if the
     * song ID is unknown.
     */
    @Override
    public int getPlaysForSong(String songId) {

        return songs.get(songId) == null ? -1 : songs.get(songId) ;
    }

    /**
     * Return the top N songs played, in descending
     * order of number of plays.
     *
     * @param n
     */
    @Override
    public List<String> getTopNSongsPlayed(int n) {
        int num = n;
        if (n > songs.size()) {
            num = songs.size();
        }
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(songs.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return -o1.getValue().compareTo(o2.getValue());
            }
        });
        List<String> result = new ArrayList<>();
        for (int i = 0;i < n;i++) {
            result.add(entries.get(i).getKey());
        }
        return result;
    }

    @Test
    public void cacheIsWorking() {
        SongCache cache = new SongCacheImpl();
        cache.recordSongPlays("ID-1", 3);
        cache.recordSongPlays("ID-1", 1);
        cache.recordSongPlays("ID-2", 2);
        cache.recordSongPlays("ID-3", 5);

        assertThat(cache.getPlaysForSong("ID-1"), is(4));
        assertThat(cache.getPlaysForSong("ID-9"), is(-1));

        assertThat(cache.getTopNSongsPlayed(2), contains("ID-3",
                "ID-1"));
        assertTrue(cache.getTopNSongsPlayed(0).isEmpty());
    }
    
}

