package com.netcracker.edu.java.tasks;

import java.util.List;

/**
 * ЦЕЛЬ ЗАДАЧИ
 * <p/>
 * Познакомиться со структурами данных в языке программирования java,<br/>
 * в том числе научиться сортировать элементы в данных структурах по заданным <br/>
 * параметрам.
 * <p/>
 * ЗАДАНИЕ
 * <p/>
 * Реализовать простейшую модель части back-end реального проигрывателя
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
     * @param startTime - время, с которого начинается проигрывание установленного плейлиста
     */
    public void play(int starTime);

    /**
     * Метод останавливает проигрывание плейлиста.
     * После вызова stopPlaying() метод getCurrentSong(time) возвращает значение null
     */
    public void stopPlaying();

    /**
     * Метод останавливает проигрывание текущей композиции.
     * Дальнейшее воспроизведение начинается с момента, на котором оно закончилось
     * @param pauseTime - время вызова метода pausePlaying(time)
     */
    public void pausePlaying(int pauseTime);

    /**
     * Метод полностью очищает плейлист.
     */
    public void clearPlaylist();

    /**
     * Метод возвращает песню, "играющую" в данный
     *
     * @param time  время с начала проигрывания плейлиста.
     */
    public Song getCurrentSong(int time);

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


