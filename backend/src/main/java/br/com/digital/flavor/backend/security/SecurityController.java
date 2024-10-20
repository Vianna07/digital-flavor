package br.com.digital.flavor.backend.security;

import br.com.digital.flavor.backend.security.dto.LoginRequest;
import br.com.digital.flavor.backend.security.dto.LoginResponse;
import br.com.digital.flavor.backend.user.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class SecurityController {

    private final SecurityService securityService;

    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(securityService.login(loginRequest));
    }

    @PostMapping("/login-canteen")
    public ResponseEntity<LoginResponse> loginCanteen(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(securityService.loginCanteen(loginRequest));
    }

    @GetMapping("/renew-token")
    public ResponseEntity<String> renewToken(@RequestHeader("token") String token) {
        return ResponseEntity.ok(securityService.renewToken(token));
    }

}
