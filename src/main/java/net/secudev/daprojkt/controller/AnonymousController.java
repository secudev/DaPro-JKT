package net.secudev.daprojkt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class AnonymousController {
	
	@GetMapping("/ping")
	public String ping()
	{
		return "OK";
	}

	//Register, password lost, check AUthentication

	
}
