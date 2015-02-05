package com.netcracker.edu.java.tasks;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Vadim Varnavsky
 * @author Yefim Krokhin
 */

public class PlayerImpl implements Player {
    private List<Song> playlist;
    private int startTime = 0;
    private int pauseTime = 0;

    private enum Status {
        STOPPED, PLAYING, PAUSED;
    }

    private Status status = Status.STOPPED;

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getPauseTime() {
        return pauseTime;
    }

    public void setPauseTime(int pauseTime) {
        this.pauseTime = pauseTime;
    }

    @Override
    public void setPlaylist(List<Song> playlist) {
        this.playlist = playlist;
    }

    @Override
    public List<Song> getPlaylist() {
        return playlist;
    }

    @Override
    public void clearPlaylist() {
        playlist.clear();
    }

    @Override
    public void play(int startTime) {
        if (status == Status.STOPPED) {
            setStartTime(startTime);
            status = Status.PLAYING;
        } else if (status == Status.PAUSED) {
            setStartTime(pauseTime);
            status = Status.PLAYING;
        } else {
            setStartTime(startTime);
        }
    }

    @Override
    public void stop() {
        status = Status.STOPPED;
    }

    @Override
    public void pause(int pauseTime) {
        setPauseTime(pauseTime);
        status = Status.PAUSED;
    }

    @Override
    public Song getSong(int time) {

        if (status == Status.STOPPED) {
            return null;
        } else if (status == Status.PAUSED) {
            int deltaPausePlay;
            int upTime = 0;
            deltaPausePlay = getPauseTime() - getStartTime();
            for (Song temp : playlist) {
                upTime += temp.getSongDuration();
                if (deltaPausePlay < upTime) {
                    return temp;
                }
            }
        } else if (status == Status.PLAYING) {
            int deltaPlay;
            int upTime = 0;
            deltaPlay = time - getStartTime();
            for (Song temp : playlist) {
                upTime += temp.getSongDuration();
                if (deltaPlay < upTime) {
                    return temp;
                }
            }
        } else {
            return null;
        }
        return null;
    }

    @Override
    public List<Song> sortedByArtist() {
        try {
            Collections.sort(playlist, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    return ((Song) o1).getArtist().compareTo(((Song) o2).getArtist());
                }
            });
        } catch (NullPointerException e) {
            throw new IllegalStateException();
        }
        return playlist;
    }

    @Override
    public List<Song> sortedByName() {
        try {


            Collections.sort(playlist, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    return ((Song) o1).getSongName().compareTo(((Song) o2).getSongName());
                }
            });

        } catch (NullPointerException e) {
            throw new IllegalStateException();
        }
        return playlist;
    }

    @Override
    public List<Song> sortedByDuration() {

        try {
            if (playlist.isEmpty()) {
                throw new IllegalStateException();
            }
            Collections.sort(playlist, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    return ((Song) o1).getSongDuration() - (((Song) o2).getSongDuration());
                }
            });
        } catch (NullPointerException e) {
            throw new IllegalStateException();
        }

        return playlist;
    }
}
