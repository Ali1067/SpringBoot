package com.security.OAuth2;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class IntegrationController {

    @GetMapping("/me")
    public ResponseEntity<?> getIdentity(@AuthenticationPrincipal OidcUser principal) {
        // Verification: Proves OpenID Connect successfully shared the profile
        return ResponseEntity.ok(Map.of(
                "email", principal.getEmail(),
                "full_name", principal.getFullName(),
                "google_id", principal.getSubject()
        ));
    }

    @GetMapping("/google-drive")
    public ResponseEntity<?> getDriveAccess(
            @RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {

        // This is the token you use to call https://www.googleapis.com/drive/v3/files
        String googleAccessToken = client.getAccessToken().getTokenValue();

        return ResponseEntity.ok(Map.of(
                "message", "Use this token for Google Drive API calls",
                "access_token", googleAccessToken
        ));
    }
}