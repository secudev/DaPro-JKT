package net.secudev.daprojkt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vip")
@PreAuthorize("hasRole('ROLE_vip')")
public class VIPController {

	@GetMapping("/ping")
	public String ping()
	{
		return "OK";
	}
}
