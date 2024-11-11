package br.com.digital.flavor.backend.security.tenant;

import br.com.digital.flavor.backend.security.SecurityConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        String requestURI = req.getRequestURI();

        for (String publicRoute : SecurityConfig.PUBLIC_ROUTES) {
            if (requestURI.startsWith(publicRoute.replace("*", ""))) {
                chain.doFilter(request, response);
                return;
            }
        }

        try {
            CanteenContext.setCurrentCanteen(this.jwtDecoder.decode(authorization.substring(7)));
            chain.doFilter(request, response);
        } catch (Exception e) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Token inválido ou expirado.");
        } finally {
            CanteenContext.setCurrentCanteen(null);
        }
    }
}