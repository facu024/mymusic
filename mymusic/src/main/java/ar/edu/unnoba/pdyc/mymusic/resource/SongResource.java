package ar.edu.unnoba.pdyc.mymusic.resource;

import ar.edu.unnoba.pdyc.mymusic.dto.SongDto;
import ar.edu.unnoba.pdyc.mymusic.model.Song;
import ar.edu.unnoba.pdyc.mymusic.service.SongService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Path("/songs")
public class SongResource {
    @Autowired
    private  SongService songService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists() {
        ModelMapper modelMapper = new ModelMapper();
        List<SongDto> songDtos = songService.getAllSongs().stream().map(song -> {
            SongDto songDto = modelMapper.map(song,SongDto.class);
            songDto.setId(song.getId());
            songDto.setName(song.getName());
            songDto.setAuthor(song.getAuthor());
            songDto.setGenre(song.getGenre());
            return songDto;

        }).toList();
        return Response.ok(songDtos).build();

    }

    @PostMapping
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSong(@RequestBody SongDto songDto) {
        ModelMapper modelMapper = new ModelMapper();
        Song song = modelMapper.map(songDto, Song.class);
        song.setAuthor(songDto.getAuthor());
        song.setGenre(songDto.getGenre());
        song.setName(songDto.getName());
        songService.createSong(song);
        return Response.ok(songDto).build();
    }

}
