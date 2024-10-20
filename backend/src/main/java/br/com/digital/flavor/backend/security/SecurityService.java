package br.com.digital.flavor.backend.security;

import br.com.digital.flavor.backend.canteen.Canteen;
import br.com.digital.flavor.backend.canteen.CanteenRepository;
import br.com.digital.flavor.backend.security.dto.LoginRequest;
import br.com.digital.flavor.backend.security.dto.LoginResponse;
import br.com.digital.flavor.backend.user.User;
import br.com.digital.flavor.backend.user.UserDto;
import br.com.digital.flavor.backend.user.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class SecurityService {

    private final int EXPIRES_AT = 960;
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CanteenRepository canteenRepository;

    public SecurityService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, CanteenRepository canteenRepository) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.passwordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.canteenRepository = canteenRepository;
    }

    public UserDto login(LoginRequest loginRequest) {
        Optional<User> optUser = this.userRepository.findByEmail(loginRequest.email());

        if (optUser.isEmpty() || !optUser.get().isValidLogin(loginRequest, this.passwordEncoder)) {
            throw new BadCredentialsException("E-mail ou senha inválidos");
        }

        User user = optUser.get();

        return new UserDto(user.getId(), user.getUserType(), user.getName(), user.getEmail());
    }

    public LoginResponse loginCanteen(LoginRequest loginRequest) {
        UserDto user = login(loginRequest);
        String canteenId = loginRequest.canteenId();

        if (canteenId == null || canteenId.isEmpty()) {
            throw new IllegalArgumentException("Id da cantina não pode ser nulo/vazio");
        }

        Optional<Canteen> optCanteen = this.canteenRepository.findById(UUID.fromString(canteenId));

        if (optCanteen.isEmpty()) {
            throw new IllegalArgumentException("Id da cantina inválido");
        }

        try {
            String jwtToken = this.jwtEncoder.encode(JwtEncoderParameters.from(getJwtClaimsSet(user, canteenId))).getTokenValue();
            return new LoginResponse(jwtToken, this.EXPIRES_AT);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public JwtClaimsSet getJwtClaimsSet(UserDto user, String canteenId) {
        Instant instant = Instant.now();

        return JwtClaimsSet.builder()
                .issuer("backend")
                .subject(user.id().toString())
                .issuedAt(instant)
                .expiresAt(instant.plusSeconds(this.EXPIRES_AT))
                .claim("role", user.userType())
                .claim("canteenId", canteenId)
                .build();
    }

    public String renewToken(String token) {
        try {
            Jwt jwt = this.jwtDecoder.decode(token);
            Instant instant = Instant.now();

            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("backend")
                    .subject(jwt.getClaim("sub"))
                    .issuedAt(instant)
                    .expiresAt(instant.plusSeconds(this.EXPIRES_AT))
                    .claim("role", jwt.getClaim("sub"))
                    .claim("canteenId",  jwt.getClaim("sub"))
                    .build();

            return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao decodificar token JWT - " + e.getMessage());
        }
    }
}
