package br.com.digital.flavor.backend.security;

import br.com.digital.flavor.backend.canteen.Canteen;
import br.com.digital.flavor.backend.canteen.CanteenRepository;
import br.com.digital.flavor.backend.security.dto.LoginRequest;
import br.com.digital.flavor.backend.security.dto.LoginResponse;
import br.com.digital.flavor.backend.security.tenant.CanteenContext;
import br.com.digital.flavor.backend.user.User;
import br.com.digital.flavor.backend.user.UserRepository;
import br.com.digital.flavor.backend.user.dto.UserLoginDto;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class SecurityService {

    private final int EXPIRES_AT = 960;
    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CanteenRepository canteenRepository;

    public SecurityService(JwtEncoder jwtEncoder,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserRepository userRepository,
                           CanteenRepository canteenRepository) {
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.canteenRepository = canteenRepository;
    }

    public UserLoginDto login(LoginRequest loginRequest) {
        Optional<User> optUser = this.userRepository.findByEmail(loginRequest.email());

        if (optUser.isEmpty() || !optUser.get().isValidLogin(loginRequest.password(), this.passwordEncoder)) {
            throw new BadCredentialsException("E-mail ou senha inválidos");
        }

        User user = optUser.get();

        return new UserLoginDto(user.getId(), user.getUserType(), user.getName(), user.getEmail(), loginRequest.password());
    }

    public LoginResponse loginCanteen(LoginRequest loginRequest) {
        UserLoginDto user = login(loginRequest);
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
            return new LoginResponse("Bearer " + jwtToken, this.EXPIRES_AT);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public JwtClaimsSet getJwtClaimsSet(String issuer, String subject, String scope, String canteenId) {
        Instant instant = Instant.now();

        return JwtClaimsSet.builder()
                .issuer(issuer)
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plusSeconds(this.EXPIRES_AT))
                .claim("scope", scope)
                .claim("canteenId", canteenId)
                .build();
    }

    public JwtClaimsSet getJwtClaimsSet(UserLoginDto user, String canteenId) {
        return getJwtClaimsSet("backend", user.id().toString(), user.userType().toString(), canteenId);
    }

    public LoginResponse renewToken() {
        try {
            JwtClaimsSet claims = getJwtClaimsSet(
                    CanteenContext.getCurrentIssuer(),
                    CanteenContext.getCurrentSubject(),
                    CanteenContext.getCurrentScope(),
                    CanteenContext.getCurrentCanteenId()
            );

            String jwtToken = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
            return new LoginResponse("Bearer " + jwtToken, this.EXPIRES_AT);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao decodificar authorization JWT - " + e.getMessage());
        }
    }

}
