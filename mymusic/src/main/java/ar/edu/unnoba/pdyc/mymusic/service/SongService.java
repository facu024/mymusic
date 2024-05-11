package ar.edu.unnoba.pdyc.mymusic.service;

import ar.edu.unnoba.pdyc.mymusic.model.Song;
import org.jvnet.hk2.annotations.Service;

import java.util.List;

@Service
public interface SongService {
    List<Song> getAllSongs();
    Song getSongById(Long id);
    Song createSong(Song song);
    void deleteSong(Long id);
}
