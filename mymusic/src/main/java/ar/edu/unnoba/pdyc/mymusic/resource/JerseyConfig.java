package ar.edu.unnoba.pdyc.mymusic.resource;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(PlaylistResource.class);
        register(SongResource.class);
    }
}
