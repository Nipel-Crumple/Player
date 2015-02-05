package com.netcracker.edu.java.tasks;

import com.netcracker.edu.java.test.IpcTest;
import com.netcracker.edu.java.test.IpcTestClass;
import com.netcracker.edu.java.test.IpccenterTest;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static com.netcracker.edu.java.tasks.Player.Song;
import static org.junit.Assert.*;

/**
 * @author Vadim Varnavsky
 * @author Yefim Krokhin
 */

@IpcTestClass(weight = 9)
public class PlayerTest extends IpccenterTest<Player> {

    private static Player etalon = new PlayerEtalon();
    private Player playerTestNull = this.getImpl();
    private Player impl = this.getImpl();
    private static List playlistTest;
    private static Song firstSong;
    private static Song secondSong;
    private static Song thirdSong;
    private static Song fourthSong;
    private static Song fifthSong;

    @BeforeClass
    public static void setUpOneTime() {
        //WHAT IS THIS?
        firstSong = new Song("Miles Kane", "Don't Forget Who You Are", 182);
        secondSong = new Song("Katy Perry", "Roar", 202);
        thirdSong = new Song("The Beatles", "Help", 135);
        fourthSong = new Song("Arctic Monkeys", "Snap Out of It", 193);
        fifthSong = new Song("Jack White", "Steady as She Go", 164);
    }

    @AfterClass
    public static void tearDownOneTime() {
        etalon = null;
    }

    @Before
    public void setUp() {
        playlistTest = new ArrayList<Song>();
        playlistTest.add(firstSong);
        playlistTest.add(secondSong);
        playlistTest.add(thirdSong);
        playlistTest.add(fourthSong);
        playlistTest.add(fifthSong);

        // setting playlist in etalon object before every test
        etalon.setPlaylist(playlistTest);
        impl.setPlaylist(playlistTest);
    }

    @After
    public void tearDown() {
        playlistTest.clear();
        //cleaning playlist after every test
        etalon.clearPlaylist();
        impl.clearPlaylist();
    }

    @Test(timeout = 3000, expected = IllegalStateException.class)
    @IpcTest(mark = 1, failedMessage = "Incorrect work of sortingByName() (IllegalStateException expected)",
            testName = "test order by name")
    public void checkSortByNameException() {
        playerTestNull = this.getImpl();
        playerTestNull.sortedByName();
    }

    @Test(timeout = 3000, expected = IllegalStateException.class)
    @IpcTest(mark = 1, failedMessage = "Incorrect work of sortingByArtist() (IllegalStateException expected)",
            testName = "test order by artist")
    public void checkSortByArtistException() {
        playerTestNull = this.getImpl();
        playerTestNull.sortedByArtist();
    }

    @Test(timeout = 3000, expected = IllegalStateException.class)
    @IpcTest(mark = 1, failedMessage = "Incorrect work of sortingByDuration() (IllegalStateException expected)",
            testName = "test order by duration")
    public void checkSortByDurationException() {
        playerTestNull = this.getImpl();
        playerTestNull.sortedByDuration();
    }

    /**
     * Test helps us to find out if user's playlist was installed incorrectly
     */
    @Test(timeout = 3000)
    @IpcTest(mark = 1, failedMessage = "Incorrect work of setPlaylist() (return unexpected value)",
            testName = "test setter for Playlist")
    public void checkSetPlaylist() {
        assertNotNull(impl.getPlaylist());
        assertFalse(impl.getPlaylist().isEmpty());
        assertEquals(etalon.getPlaylist(), impl.getPlaylist());
    }


    /**
     * Check if playlist was cleared not properly
     */
    @Test(timeout = 3000)
    @IpcTest(mark = 1, failedMessage = "Incorrect work of clearPlaylist() (list isn't empty)",
            testName = " test clear Playlist")
    public void checkClearPlaylist() {
        impl.clearPlaylist();
        assertTrue(impl.getPlaylist().isEmpty());
    }

    /**
     * This test checks the right order in user's playlist after sort by song's name
     */
    @Test(timeout = 3000)
    @IpcTest(mark = 1, failedMessage = "Incorrect work of sortingByName() (wrong order in playlist)",
            testName = " test order by name")
    public void checkSortingByName() {


        assertEquals(etalon.sortedByName(), impl.sortedByName());
    }

    /**
     * This test checks the right order in user's playlist after sort by song's artist
     */
    @Test(timeout = 3000)
    @IpcTest(mark = 1, failedMessage = "Incorrect work of sortingByArtist() (wrong order in playlist)",
            testName = "test order by artist")
    public void checkSortingByArtist() {

        assertEquals(etalon.sortedByArtist(), impl.sortedByArtist());
    }

    /**
     * This test checks the right order in user's playlist after sort by song's duration
     */
    @Test(timeout = 3000)
    @IpcTest(mark = 1, failedMessage = "Incorrect work of sortingBySongDuration() (wrong order in playlist)",
            testName = "test order by song duration")
    public void checkSortingBySongDuration() {

        assertEquals(etalon.sortedByDuration(), impl.sortedByDuration());
    }


    @Test(timeout = 3000)
    @IpcTest(mark = 1, failedMessage = "Incorrect work of getSong() (return unexpected song by the time)", testName = "test get song")
    public void checkGetSong() {
        //test of stopped player
        assertNull(impl.getSong(0));
        //test of playing player
        impl.play(50);
        assertEquals(impl.getSong(51), firstSong);
        assertEquals(impl.getSong(333), secondSong);
        assertEquals(impl.getSong(671), fourthSong);
        assertNull(impl.getSong(927));

        //test of playing player after pause's call
        impl.play(0);
        impl.pause(183);
        assertEquals(impl.getSong(1231), secondSong);

        impl.play(195);
        impl.pause(315);
        assertEquals(impl.getSong(1231), firstSong);

        impl.play(598);
        impl.pause(1687);
        assertNull(impl.getSong(21));

        //test of stopped player after playing
        impl.play(168);
        impl.stop();
        assertNull(impl.getSong(123));
    }
}
