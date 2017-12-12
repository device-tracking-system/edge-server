package pl.edu.agh.iet.dts.edge.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Bartłomiej Grochal
 */
@RestController
public class AuthenticationController {

    @RequestMapping(value = "/me", method = GET)
    public Map<String, String> getAuthenticatedUserInfo(Authentication authentication) {
        final Map<String, String> userDetails = new HashMap<>();
        final Map<String, String> authenticationDetails =
                (Map<String, String>) ((OAuth2Authentication) authentication).getUserAuthentication().getDetails();

        userDetails.put("id", authenticationDetails.get("sub"));
        userDetails.put("name", authenticationDetails.get("name"));
        return userDetails;
    }

}
