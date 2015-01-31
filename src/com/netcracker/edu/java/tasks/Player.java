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
    /**
     * Метод "загружает" плейлист.
     *
     * @param playlist список объектов типа Song (вложеннный в данный интерфейс класс см.ниже).
     */
    public void setPlaylist(List<Song> playlist);

    /**
     * Метод возвращает плейлист.
     */
    public List<Song> getPlaylist();

    /**
     * Метод начинает проигрывание, т.е. отсчет времени и переход от одной песне к другой в порядке следования с учетом
     * их длительности.
     */
    public void play();

    /**
     * Метод полностью очищает плейлист.
     */
    public void clearPlaylist();

    /**
     * Метод возвращает песню, "играющую" в данный момент.
     *
     * @param time  время с начала проигрывания плейлиста.
     */
    public Song getCurrentSong(double time);

    /**
     * Метод сортирует плейлист по имени артиста.
     *
     * @return сортированный плейлист
     */
    public List<Song> sortedByArtist();

    /**
     * Метод сортирует плейлист по названию трека.
     *
     * @return сортированный плейлист
     */
    public List<Song> sortedByName();

    /**
     * Метод сортирует плейлист по длительности трека.
     *
     * @return сортированный плейлист
     */
    public List<Song> sortedByDuration();


    public static class Song {
        private String artist;
        private String songName;
        private int songDuration;

        public Song(String artist, String songName, int songDuration) {
            setArtist(artist);
            setSongName(songName);
            setSongDuration(songDuration);
        }

        public Song() {

        }

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

        public int getSongDuration() {
            return songDuration;
        }

        public void setSongDuration(int songDuration) {
            this.songDuration = songDuration;
        }
    }
}


