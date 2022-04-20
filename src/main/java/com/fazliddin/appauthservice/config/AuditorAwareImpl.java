package com.fazliddin.appauthservice.config;

import com.fazliddin.appauthservice.payload.UserPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Fazliddin Xamdamov
 * @date 19.04.2022  12:55
 * @project app-fast-food
 */
public class AuditorAwareImpl implements AuditorAware<UUID> {

    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            return Optional.of(userPrincipal.getUser().getId());
        }
        return Optional.empty();
    }
}
