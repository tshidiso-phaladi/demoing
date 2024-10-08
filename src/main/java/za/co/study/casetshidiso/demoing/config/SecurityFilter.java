package za.co.study.casetshidiso.demoing.config;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import za.co.study.casetshidiso.service.AppStateService;
import za.co.study.casetshidiso.service.TokenGenerationService;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.security.Key;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
@RequiresAuthentication
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {
    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    public static final String BEARER = "Bearer";

    @Inject
    private TokenGenerationService tokenGenerationService;

    @Inject
    private AppStateService appStateService;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String authHeader = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            logger.log(Level.SEVERE, "Authorization header is invalid {0}", authHeader);
            throw new NotAuthorizedException("Authorization header is invalid");
        }
        String token = authHeader.substring(BEARER.toString().length()).trim();

        try {
            Key key = tokenGenerationService.generateKey(appStateService.getEmailAddress());
            Jwts.parser().setSigningKey(key).setSigningKey(key).parseClaimsJws(token);
            SecurityContext securityContext = containerRequestContext.getSecurityContext();
            containerRequestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return () -> Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
                }

                @Override
                public boolean isUserInRole(String user) {
                    return securityContext.isUserInRole(user);
                }

                @Override
                public boolean isSecure() {
                    return securityContext.isSecure();
                }

                @Override
                public String getAuthenticationScheme() {
                    return securityContext.getAuthenticationScheme();
                }
            });
            logger.log(Level.INFO, "Parsing of token complete. Successfully authenticated {0}", token);

        }catch (Exception e) {
            logger.log(Level.SEVERE, "Invalid Token", e);
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
