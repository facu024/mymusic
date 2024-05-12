package ar.edu.unnoba.pdyc.mymusic.service;


import ar.edu.unnoba.pdyc.mymusic.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDto> getAllUsers();

    boolean existsByEmail(String email);
}
