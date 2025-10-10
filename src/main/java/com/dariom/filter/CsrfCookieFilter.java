package com.dariom.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Forces creation of the CSRF cookie on normal requests.
 *
 * <p>After {@link CsrfFilter} runs, this filter reads the request's {@link CsrfToken} attribute,
 * which triggers the configured {@code CsrfTokenRepository} (e.g.
 * {@code CookieCsrfTokenRepository}) to generate/write the {@code XSRF-TOKEN} cookie if it's
 * missing.</p>
 */
public class CsrfCookieFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain) throws ServletException, IOException {
    var csrfToken = (CsrfToken) req.getAttribute(CsrfToken.class.getName());
    if (csrfToken != null) {
      // Accessing the token forces generation (writes XSRF-TOKEN cookie if missing)
      csrfToken.getToken();
    }

    chain.doFilter(req, res);
  }
}
