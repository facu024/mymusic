package ar.edu.unnoba.pdyc.mymusic.service;

import ar.edu.unnoba.pdyc.mymusic.model.Playlist;
import java.util.List;

public interface PlaylistService {
    List<Playlist> getAllPlaylists();
    Playlist getPlaylistById(Long id);
    Playlist createPlaylist(Playlist playlist);
    void deletePlaylist(Long id);

}
