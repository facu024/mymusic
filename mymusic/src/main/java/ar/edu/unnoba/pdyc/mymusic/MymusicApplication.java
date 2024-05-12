package ar.edu.unnoba.pdyc.mymusic;

import ar.edu.unnoba.pdyc.mymusic.model.User;
import ar.edu.unnoba.pdyc.mymusic.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
	public class MymusicApplication {

		public static void main(String[] args) {

			ConfigurableApplicationContext context = SpringApplication.run(MymusicApplication.class, args);
			UserRepository userRepository = context.getBean(UserRepository.class);
			PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);


			User user = new User();
			user.setEmail("example@example.com");
			user.setPassword(passwordEncoder.encode("password123"));
			userRepository.save(user);
		}

	}

