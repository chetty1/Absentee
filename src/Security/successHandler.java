package Security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by chett_000 on 2017/05/10.
 */
public class successHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }


    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {

            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {


        User user = (User) authentication.getPrincipal();
        String url = "/register";
        for (GrantedAuthority auth : user.getAuthorities()) {

            System.out.println(auth.getAuthority());
            switch (auth.getAuthority()) {
                case "ROLE_REGISTER":
                    url = "/register";
                    break;
                case "ROLE_HR":
                    url = "/approvals";
                    break;
                case "ROLE_LINDA":
                    url = "/factoryoverview";
                    break;
                case "ROLE_MANAGER":
                    url = "/stats";
                    break;


            }

        }
        return url;
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
