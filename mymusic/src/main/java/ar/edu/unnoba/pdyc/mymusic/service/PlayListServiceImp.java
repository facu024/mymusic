package ar.edu.unnoba.pdyc.mymusic.service;

import ar.edu.unnoba.pdyc.mymusic.model.Playlist;
import ar.edu.unnoba.pdyc.mymusic.repository.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayListServiceImp implements PlaylistService {
    private final PlayListRepository playListRepository;

    @Autowired
    public PlayListServiceImp(PlayListRepository playListRepository) {
        this.playListRepository = playListRepository;
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playListRepository.findAll();
    }

    @Override
    public Playlist getPlaylistById(Long id) {
        return playListRepository.findAllById(id);
    }

    @Override
    public Playlist createPlaylist(Playlist playlist) {
        return playListRepository.save(playlist);
    }

    @Override
    public void deletePlaylist(Long id) {

    }
}
