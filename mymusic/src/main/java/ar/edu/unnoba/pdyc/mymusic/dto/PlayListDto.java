package ar.edu.unnoba.pdyc.mymusic.dto;

import java.util.List;

public class PlayListDto {
    private Long id;
    private String name;
    private List<SongDto> songs;
    private UserDto owner;
    private int SongAmount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SongDto> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDto> songs) {
        this.songs = songs;
    }

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    public int getSongAmount() {
        return SongAmount;
    }

    public void setSongAmount(int songAmount) {
        SongAmount = songAmount;
    }
}




