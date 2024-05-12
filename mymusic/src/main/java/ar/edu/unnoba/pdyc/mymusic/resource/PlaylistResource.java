package ar.edu.unnoba.pdyc.mymusic.resource;

import ar.edu.unnoba.pdyc.mymusic.dto.PlayListDto;
import ar.edu.unnoba.pdyc.mymusic.dto.SongDto;
import ar.edu.unnoba.pdyc.mymusic.model.Playlist;
import ar.edu.unnoba.pdyc.mymusic.model.Song;
import ar.edu.unnoba.pdyc.mymusic.repository.PlayListRepository;
import ar.edu.unnoba.pdyc.mymusic.service.PlaylistService;
import ar.edu.unnoba.pdyc.mymusic.service.SongService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.modelmapper.ModelMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Path("/playlists")
public class PlaylistResource {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private PlayListRepository playlistRepository;

    @Autowired
    private SongService songService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public ResponseEntity<List<PlayListDto>> getPlaylists() {
        ModelMapper modelMapper = new ModelMapper();
        List<Playlist> playlists = playlistService.getAllPlaylists();
        List<PlayListDto> playlistDtoList = playlists.stream().map(playlist -> {
            PlayListDto playlistDto = modelMapper.map(playlist, PlayListDto.class);
            playlistDto.setName(playlist.getName());
            playlistDto.setSongAmount(playlist.getSongs().size());
            List<SongDto> songDtoList = playlist.getSongs().stream()
                    .map(song -> modelMapper.map(song, SongDto.class))
                    .collect(Collectors.toList());
            playlistDto.setSongs(songDtoList);
            return playlistDto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(playlistDtoList);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPlaylist(PlayListDto playlistDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        playlistService.createPlaylist(playlistDTO.getName(), mail);
        return Response.status(Response.Status.CREATED).build();
    }
    @POST
    @Path("/{id}/song")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public ResponseEntity<PlayListDto> addSong(@PathParam("id") Long playlistId, Map<String, Object> requestBody) {
        Integer songId = (Integer) requestBody.get("songId");
        Long songIdLong = songId.longValue();

        Playlist playlist = playlistRepository.findById(playlistId).orElse(null);
        if (playlist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Playlist not found
        }

        Song song = songService.getSongById(songIdLong);
        if (song == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Song not found
        }

        List<Song> songList = playlist.getSongs();
        songList.add(song);
        playlistRepository.save(playlist);

        ModelMapper modelMapper = new ModelMapper();
        PlayListDto playlistDto = modelMapper.map(playlist, PlayListDto.class);
        playlistDto.setSongAmount(playlist.getSongs().size());

        return ResponseEntity.ok(playlistDto);
    }


    @DELETE
    @Path("/{idP}/songs/{idS}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public ResponseEntity<PlayListDto> deleteSong(@PathParam("idP") Long idP, @PathParam("idS") Long idS) {
        Playlist playlist = playlistRepository.findById(idP).orElse(null);
        if (playlist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Playlist not found
        }
        Song song = songService.getSongById(idS);
        if (song == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Song not found
        }
        playlist.getSongs().remove(song);
        ModelMapper modelMapper = new ModelMapper();
        PlayListDto playlistDto = modelMapper.map(playlist, PlayListDto.class);
        playlistDto.setSongAmount(playlist.getSongs().size());
        return ResponseEntity.ok(playlistDto);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePlaylist(@PathParam("id") Long id, PlayListDto playlistDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        playlistService.updatePlaylistName(id, playlistDTO.getName(), mail);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/{idP}/songs")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public ResponseEntity<List<SongDto>> getSongsOfPlaylist(@PathParam("idP") Long idPlaylist) {
        ModelMapper modelMapper = new ModelMapper();
        Playlist playlist = playlistRepository.findById(idPlaylist).orElse(null);
        if (playlist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Playlist not found
        }
        List<Song> songList = playlist.getSongs();
        List<SongDto> songDtoList = songList.stream().map(song -> {
            SongDto songDto = modelMapper.map(song, SongDto.class);
            songDto.setName(song.getName());
            songDto.setAuthor(song.getAuthor());
            songDto.setGenre(song.getGenre());
            songDto.setId(song.getId());
            return songDto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(songDtoList);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void deletePlaylist(@PathParam("id") Long idPlaylist) {
        playlistService.deletePlaylist(idPlaylist);
    }
}
