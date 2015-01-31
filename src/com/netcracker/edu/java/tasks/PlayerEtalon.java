package com.netcracker.edu.java.tasks;

import java.util.List;

public class PlayerEtalon implements Player {
    private List<Player.Song> playlist;

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

    }

    @Override
    public Song getCurrentSong(double time) {
        return null;
    }

    @Override
    public List<Song> sortedByArtist() {
        return null;
    }

    @Override
    public List<Song> sortedByName() {
        return null;
    }

    @Override
    public List<Song> sortedByDuration() {
        return null;
    }
}
