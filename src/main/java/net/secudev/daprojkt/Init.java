package net.secudev.daprojkt;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Init implements ApplicationRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// s'il n'existe pas, creation du dossier files pour stocker les fichiers
		// uploadés
		File dir = new File("files");		
		if (!dir.exists() && !dir.isDirectory()) {
			dir.mkdir();
			logger.info("Dossier files crée : "+dir.getAbsolutePath());
		}

		// creation de quelques users : admin, regular et vip et leurs roles associés

	}

}
