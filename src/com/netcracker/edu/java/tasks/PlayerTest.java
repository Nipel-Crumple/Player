package com.netcracker.edu.java.tasks;

import org.junit.*;

import java.util.*;

import static com.netcracker.edu.java.tasks.Player.Song;
import static java.util.Arrays.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlayerTest {

    private static Player playerTest;
    private static List playlistTest;
    private static Song firstSong;
    private static Song secondSong;
    private static Song thirdSong;
    private static Song fourthSong;
    private static Song fivethSong;

    @BeforeClass
    public static void setUpOneTime() {
       playerTest = new PlayerEtalon();
       firstSong = new Song("Miles Kane", "Don't Forget Who You Are", 182);
       secondSong = new Song("Katy Perry", "Roar", 202);
       thirdSong = new Song("The Beatles", "Help", 135);
       fourthSong = new Song("Arctic Monkeys", "Snap Out of It", 193);
       fivethSong = new Song("Jack White", "Steady as She Go", 164);
    }

    @AfterClass
    public static void tearDownOneTime() {
        playerTest = null;
    }

    @Before
    public void setUp() {
        playlistTest = new ArrayList<Song>();
        playlistTest.add(firstSong);
        playlistTest.add(secondSong);
        playlistTest.add(thirdSong);
        playlistTest.add(fourthSong);
        playlistTest.add(fivethSong);

        // setting playlist in playerTest object before every test
        playerTest.setPlaylist(playlistTest);
    }

    @After
    public void tearDown() {
        playlistTest.clear();

        //cleaning playlist after every test
        playerTest.clearPlaylist();
    }

    /**
     * Test helps us to find out if user's playlist was installed incorrectly
     */
    @Test
    public void checkSetPlaylist() {
        assertNotNull(playerTest.getPlaylist());
        assertFalse(playerTest.getPlaylist().isEmpty());
        assertEquals(playlistTest, playerTest.getPlaylist());
    }

    /**
     * This test checks the right order in user's playlist after sort by song's name
     */

    /**
     * Check if playlist was cleared not properly
     */
    @Test
    public void checkClearPlaylist() {
        playerTest.clearPlaylist();
        assertTrue(playerTest.getPlaylist().isEmpty());
    }
    @Test
    public void checkSortingByName() {
        Collections.sort(playlistTest, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Song) o1).getSongName().compareTo(((Song) o2).getSongName());
            }
        });

        assertEquals(playlistTest, playerTest.sortedByName());
    }

    /**
     * This test checks the right order in user's playlist after sort by song's artist
     */
    @Test
    public void checkSortingByArtist() {
        Collections.sort(playlistTest, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Song) o1).getArtist().compareTo(((Song) o2).getArtist());
            }
        });
        System.out.println(playlistTest);
        assertEquals(playlistTest, playerTest.sortedByArtist());
    }

    /**
     * This test checks the right order in user's playlist after sort by song's duration
     */
    @Test
    public void checkSortingBySongDuration() {
        Collections.sort(playlistTest, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Song) o1).getSongDuration()-(((Song) o2).getSongDuration());
            }
        });

        assertEquals(playlistTest, playerTest.sortedByDuration());
    }

    @Ignore
    @Test
    public void checkCurrentSong() {

    }
}
