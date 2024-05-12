package ar.edu.unnoba.pdyc.mymusic.service;

import ar.edu.unnoba.pdyc.mymusic.model.Playlist;
import ar.edu.unnoba.pdyc.mymusic.model.User;
import ar.edu.unnoba.pdyc.mymusic.repository.PlayListRepository;
import ar.edu.unnoba.pdyc.mymusic.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PlayListServiceImp implements PlaylistService {
    @Autowired
    private PlayListRepository playListRepository;

    @Autowired
    private SongService songService;

    @Autowired
    private UserRepository userRepository;

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
    public void createPlaylist(String name, String mail) {
        User owner = userRepository.findByEmail(mail);
        Playlist playlist = new Playlist(name, owner);
        playlist.setOwner(owner);
        playlist.setName(name);
        playListRepository.save(playlist);
    }

    @Override
    public void deletePlaylist(Long id) {
        playListRepository.deleteById(id);
    }

    @Override
    public void updatePlaylistName(Long playlistId, String nameSong, String nameOwner) {
        Playlist playlist = playListRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        String ownerEmail = playlist.getOwner().getEmail();
        if (!ownerEmail.equals(nameOwner)) {
            throw new RuntimeException("You are not the owner of the playlist");
        }

        playlist.setName(nameSong);
        playListRepository.save(playlist);
    }
}


