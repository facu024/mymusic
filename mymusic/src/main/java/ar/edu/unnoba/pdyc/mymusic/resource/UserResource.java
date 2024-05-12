package ar.edu.unnoba.pdyc.mymusic.resource;

import ar.edu.unnoba.pdyc.mymusic.dto.UserDto;
import ar.edu.unnoba.pdyc.mymusic.service.UserServiceImp;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Path("/users")
public class UserResource {
    @Autowired
    private UserServiceImp userService;

    public UserResource(UserServiceImp userService) {
        this.userService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
            List<UserDto> users = userService.getAllUsers();
            return Response.ok(users).build();
    }

}
