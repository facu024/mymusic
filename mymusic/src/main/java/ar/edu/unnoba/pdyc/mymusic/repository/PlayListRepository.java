package ar.edu.unnoba.pdyc.mymusic.repository;

import ar.edu.unnoba.pdyc.mymusic.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListRepository extends JpaRepository<Playlist,Long> {
    Playlist findAllById(Long id);

}

