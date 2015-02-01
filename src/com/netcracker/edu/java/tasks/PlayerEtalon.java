package com.netcracker.edu.java.tasks;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlayerEtalon implements Player {
    private List<Player.Song> playlist;
    private Song currentSong;

    @Override
    public void setPlaylist(List<Song> playlist) {
        this.playlist = playlist;
    }

    @Override
    public List<Song> getPlaylist() {
        return playlist;
    }

    @Override
    public void play() {

    }

    @Override
    public void clearPlaylist() {
        playlist.clear();
    }

    @Override
    public Song getCurrentSong(double time) {
        return currentSong;
    }

    @Override
    public List<Song> sortedByArtist() {
        Collections.sort(playlist, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Song) o1).getArtist().compareTo(((Song) o2).getArtist());
            }
        });
        return playlist;
    }

    @Override
    public List<Song> sortedByName() {
        Collections.sort(playlist, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Song) o1).getSongName().compareTo(((Song) o2).getSongName());
            }
        });
        return playlist;
    }

    @Override
    public List<Song> sortedByDuration() {
        Collections.sort(playlist, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Song) o1).getSongDuration()-(((Song) o2).getSongDuration());
            }
        });
        return playlist;
    }
}
