package net.secudev.daprojkt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/regular")
@PreAuthorize("hasRole('ROLE_regular')")
public class RegularController {
	
	//crud ad, post file, answer to someone
	
	
	@GetMapping("/ping")
	public String ping()
	{
		return "OK";
	}

}
