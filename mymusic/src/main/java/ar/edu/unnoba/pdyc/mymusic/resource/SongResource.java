package ar.edu.unnoba.pdyc.mymusic.resource;

import ar.edu.unnoba.pdyc.mymusic.dto.SongDto;
import ar.edu.unnoba.pdyc.mymusic.model.Song;
import ar.edu.unnoba.pdyc.mymusic.service.SongService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Path("/songs")
public class SongResource {

    @Autowired
    private SongService songService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<List<SongDto>> getAllSongs() {
        ModelMapper modelMapper = new ModelMapper();
        List<SongDto> songDtos = songService.getAllSongs().stream()
                .map(song -> modelMapper.map(song, SongDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(songDtos);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<SongDto> createSong(SongDto songDto) {
        ModelMapper modelMapper = new ModelMapper();
        Song song = modelMapper.map(songDto, Song.class);
        song.setAuthor(songDto.getAuthor());
        song.setGenre(songDto.getGenre());
        song.setName(songDto.getName());
        songService.createSong(song);
        return ResponseEntity.ok(songDto);
    }
}
