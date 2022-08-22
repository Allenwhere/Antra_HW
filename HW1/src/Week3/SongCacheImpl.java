/**
 * @author Wenxuan Zeng
 */

package Week3;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

// original interface
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
     * song ID is unknown.
     */
    int getPlaysForSong(String songId);

    /**
     * Return the top N songs played, in descending
     * order of number of plays.
     */
    List<String> getTopNSongsPlayed(int n);
}

public class SongCacheImpl implements SongCache {

    // use concurrentHashMap to assure thread safe
    private static final Map<String, Integer> songs = new ConcurrentHashMap<String, Integer>();


    @Override
    public void recordSongPlays(String songId, int numPlays) {
        // if the key is existed, add numPlays to the old value
        songs.compute(songId, (k, v) -> (v == null) ? numPlays : v + numPlays);
    }


    @Override
    public int getPlaysForSong(String songId) {
        // return -1 if you get null
        return songs.get(songId) == null ? -1 : songs.get(songId);
    }

    @Override
    public List<String> getTopNSongsPlayed(int n) {
        // make sure the size of the list won't exceed the number of the songs
        int num = Math.min(n, songs.size());
        // put entrySet into an ArrayList
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<>(songs.entrySet());
        // sort the ArrayList with override comparator(negative sign to achieve descending order)
        entries.sort((o1, o2) -> -o1.getValue().compareTo(o2.getValue()));

        // a new list to hold the result
        List<String> result = new ArrayList<>();
        for (int i = 0; i < num; i++) {
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

