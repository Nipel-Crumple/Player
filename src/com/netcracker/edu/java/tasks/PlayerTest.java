package com.netcracker.edu.java.tasks;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import static com.netcracker.edu.java.tasks.Player.Song;

public class PlayerTest {

    private static Player playerTest;
    private static List playlistTest;

    @BeforeClass
    public static void setUpOneTime() {
        playerTest = new PlayerEtalon();

    }

    @AfterClass
    public static void tearDownOneTime() {
        playerTest = null;
    }

    @Before
    public void setUp() {
        Song firstSong = new Song("Miles Kane", "Don't Forget Who You Are", 182);
        Song secondSong = new Song("Katy Perry", "Roar", 202);
        Song thirdSong = new Song("The Beatles", "Help", 135);
        Song fourthSong = new Song("Arctic Monkeys", "Snap Out of It", 193);
        Song fivethSong = new Song("Jack White", "Steady as She Go", 164);

        playlistTest = new ArrayList<Song>();
        playlistTest.add(firstSong);
        playlistTest.add(secondSong);
        playlistTest.add(thirdSong);
        playlistTest.add(fourthSong);
        playlistTest.add(fivethSong);

    }

    @After
    public void tearDown() {
        playlistTest.clear();
    }

    @Test
    public void checkInstalledPlaylist() {
        playerTest.setPlaylist(playlistTest);
        Assert.assertNotNull(playerTest.getPlaylist());
        Assert.assertFalse(playerTest.getPlaylist().isEmpty());
        Assert.assertEquals(playlistTest, playerTest.getPlaylist());
    }
}
