package ar.edu.unnoba.pdyc.mymusic.service;

import ar.edu.unnoba.pdyc.mymusic.model.Playlist;
import java.util.List;

public interface PlaylistService {
    List<Playlist> getAllPlaylists();
    Playlist getPlaylistById(Long id);
    void createPlaylist(String name, String mail);
    void deletePlaylist(Long id);
    void updatePlaylistName(Long playlistId, String nameSong, String mail);


}
