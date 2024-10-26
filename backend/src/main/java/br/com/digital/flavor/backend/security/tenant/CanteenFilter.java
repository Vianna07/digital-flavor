package br.com.digital.flavor.backend.security.tenant;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class CanteenFilter implements Filter {

    private final JwtDecoder jwtDecoder;

    public CanteenFilter(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");

        if (req.getRequestURI().startsWith("/security/login")) {
            chain.doFilter(request, response);
            return;
        }

        CanteenContext.setCurrentCanteen(this.jwtDecoder.decode(authorization.substring(7)));

        try {
            chain.doFilter(request, response);
        } finally {
            CanteenContext.setCurrentCanteen(null);
        }
    }
}