package com.netcracker.edu.java.tasks;

import org.junit.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.netcracker.edu.java.tasks.Player.Song;
import static org.junit.Assert.*;


/*@IpcTestClass(weight = 6)*/
public class PlayerTest /*extends IpccenterTest<Player>*/ {

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
    @Test(timeout = 3000)
    /*@IpcTest(mark = 1, failedMessage = "Incorrect work of setPlaylist() (return unexpected value)", testName = "test setter for Playlist")*/
    public void checkSetPlaylist() {
        assertNotNull(playerTest.getPlaylist());
        assertFalse(playerTest.getPlaylist().isEmpty());
        assertEquals(playlistTest, playerTest.getPlaylist());
    }


    /**
     * Check if playlist was cleared not properly
     */
    @Test(timeout = 3000)
    /*@IpcTest(mark = 1, failedMessage = "Incorrect work of clearPlaylist() (list isn't empty)", testName = " test clear Playlist")*/
    public void checkClearPlaylist() {
        playerTest.clearPlaylist();
        assertTrue(playerTest.getPlaylist().isEmpty());
    }

    /**
     * This test checks the right order in user's playlist after sort by song's name
     */
    @Test(timeout = 3000)
    /*@IpcTest(mark = 1, failedMessage = "Incorrect work of sortingByName() (wrong order in playlist)", testName = " test order by name")*/
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
    @Test(timeout = 3000)
    /*@IpcTest(mark = 1, failedMessage = "Incorrect work of sortingByArtist() (wrong order in playlist)", testName = "test order by artist")*/
    public void checkSortingByArtist() {
        Collections.sort(playlistTest, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Song) o1).getArtist().compareTo(((Song) o2).getArtist());
            }
        });
        assertEquals(playlistTest, playerTest.sortedByArtist());
    }

    /**
     * This test checks the right order in user's playlist after sort by song's duration
     */
    @Test(timeout = 3000)
    /*@IpcTest(mark = 1, failedMessage = "Incorrect work of sortingBySongDuration() (wrong order in playlist)", testName = "test order by song duration")*/
    public void checkSortingBySongDuration() {
        Collections.sort(playlistTest, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Song) o1).getSongDuration() - (((Song) o2).getSongDuration());
            }
        });

        assertEquals(playlistTest, playerTest.sortedByDuration());
    }

    @Test(timeout = 3000)
    /*@IpcTest(mark = 1, failedMessage = "Incorrect work of getSong() (return unexpected song by the time)", testName = "test get song")*/
    public void checkGetSong() {
        //test of stopped player
        assertNull(playerTest.getSong(0));

        //test of playing player
        playerTest.play(50);
        assertEquals(playerTest.getSong(51), firstSong);
        assertEquals(playerTest.getSong(333), secondSong);
        assertEquals(playerTest.getSong(671), fourthSong);
        assertNull(playerTest.getSong(927));

        //test of playing player after pause's call
        playerTest.play(0);
        playerTest.pause(183);
        assertEquals(playerTest.getSong(1231), secondSong);

        playerTest.play(195);
        playerTest.pause(315);
        assertEquals(playerTest.getSong(1231), firstSong);

        playerTest.play(598);
        playerTest.pause(1687);
        assertNull(playerTest.getSong(21));

        //test of stopped player after playing
        playerTest.play(168);
        playerTest.stop();
        assertNull(playerTest.getSong(123));
    }
}
