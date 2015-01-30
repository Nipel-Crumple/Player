package com.netcracker.edu.java.tasks;

import java.util.List;

/**
 * ЦЕЛЬ ЗАДАЧИ
 * <p/>
 * <p/>
 * ЗАДАНИЕ
 * <p/>
 * <p/>
 * ТРЕБОВАНИЯ
 *
 * @author Vadim Varnavsky
 * @author Yefim Krokhin
 */

public interface Player {

    public void setPlaylist(List playlist);

    public List<Song> getPlaylist();

  /*  startPlaying(),  clearList(),  repeat(), getCurrentComposition(time)*/


    public class Song {
        private String artist;
        private String songName;
        private double songDuration;

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getSongName() {
            return songName;
        }

        public void setSongName(String songName) {
            this.songName = songName;
        }

        public double getSongDuration() {
            return songDuration;
        }

        public void setSongDuration(double songDuration) {
            this.songDuration = songDuration;
        }
    }
}


