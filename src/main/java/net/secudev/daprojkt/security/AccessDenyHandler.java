package net.secudev.daprojkt.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AccessDenyHandler implements AccessDeniedHandler {

	 private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		String ip = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getRemoteAddr();
		
		String url = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getRequestURI();

		logger.trace("Accès refusé pour "+SecurityContextHolder.getContext().getAuthentication().getName() + " vers " + url + " depuis "
				+ ip);
		response.sendError(403, "Forbiden");
	}
}
