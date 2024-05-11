package ar.edu.unnoba.pdyc.mymusic.service;

import ar.edu.unnoba.pdyc.mymusic.model.Song;
import ar.edu.unnoba.pdyc.mymusic.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImp implements SongService {

    private final SongRepository songRepository;

    @Autowired
    public SongServiceImp(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public Song getSongById(Long id) {
        return songRepository.findSongById(id);
    }

    @Override
    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }
}
