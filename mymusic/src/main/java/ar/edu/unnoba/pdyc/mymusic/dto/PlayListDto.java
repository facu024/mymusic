package ar.edu.unnoba.pdyc.mymusic.dto;

public class PlayListDto {
    private Long id;
    private String name;

    public int getSongsAmount() {
        return SongAmount;
    }

    public void setSongAmount(int SongAmount) {
        this.SongAmount = SongAmount;
    }

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
    }




