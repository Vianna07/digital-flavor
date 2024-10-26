package br.com.digital.flavor.backend.security.tenant;

import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.UUID;

public class CanteenContext {

    private static final ThreadLocal<Jwt> CURRENT_CANTEEN = new ThreadLocal<>();

    public static void setCurrentCanteen(Jwt tenant) {
        CURRENT_CANTEEN.set(tenant);
    }

    public static String getCurrentIssuer() {
        return CURRENT_CANTEEN.get().getClaim("iss");
    }

    public static String getCurrentSubject() {
        return CURRENT_CANTEEN.get().getClaim("sub");
    }

    public static Instant getCurrentIssuedAt() {
        return Instant.from(CURRENT_CANTEEN.get().getClaim("iat"));
    }

    public static String getCurrentExpiresAt() {
        return CURRENT_CANTEEN.get().getClaim("exp");
    }

    public static String getCurrentScope() {
        return CURRENT_CANTEEN.get().getClaim("scope");
    }

    public static String getCurrentCanteenId() {
        return CURRENT_CANTEEN.get().getClaim("canteenId");
    }

    public static UUID getCurrentCanteenUUID() {
        return UUID.fromString(getCurrentCanteenId());
    }

}
