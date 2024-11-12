package br.com.digital.flavor.backend.user;

import br.com.digital.flavor.backend.user.dto.NewUserDto;
import br.com.digital.flavor.backend.user.dto.UserLoginDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserLoginDto save(NewUserDto newUserDto) {
        Optional<User> user = userRepository.findByEmail(newUserDto.email());

        if (user.isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        UUID id = UUID.randomUUID();

        User newUser = new User(id, newUserDto, passwordEncoder);
        userRepository.save(newUser);

        return new UserLoginDto(id, newUser.getUserType(), newUserDto.name(), newUserDto.email(), newUserDto.password());
    }
}
