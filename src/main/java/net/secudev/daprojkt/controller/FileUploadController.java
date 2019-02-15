package net.secudev.daprojkt.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.secudev.daprojkt.utils.FileUtils;

@RestController
//Pour tous les authenticated
public class FileUploadController {

	@PostMapping("/upload")
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		// Version tres simplifiée d'upload de fichiers :

		// normalement on mettra le chemin dans application.properties et on lira ici
		// mais là on se contente de créer un dossier files a coté du JAR
		Path rootLocation = Paths.get("files");

		// Ici votre logique de renommage du fichier et l'ajout de son nom dans la bdd
		
		//Nom du fichier sans le chemin d access
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		
		//Nom de l'extension
		String extension = FilenameUtils.getExtension(filename);
		
		//Nom du fichier sans l'extension
		//String baseFileName = FilenameUtils.getBaseName(filename);
		
		String storedFileName = UUID.randomUUID().toString()+"."+extension;
		try {
			if (FileUtils.isJpegOrPng(file.getInputStream())) {

				Files.copy(file.getInputStream(), rootLocation.resolve(storedFileName), StandardCopyOption.REPLACE_EXISTING);
			} else {
				return new ResponseEntity<String>("Le fichier ne semble pas etre un JPG ou un PNG ",
						HttpStatus.EXPECTATION_FAILED);
			}
		}

		catch (IOException e) {
			return new ResponseEntity<String>("Impossible de créer le fichier, " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("/files/"+storedFileName, HttpStatus.CREATED);
	}

}
