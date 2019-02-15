package net.secudev.daprojkt.utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
	
	//Les check de nombre magic sont approximatifs pour PNG car on teste 2 octets et la je n'en teste qu'un :=
	//De plus dans l'iédeal en plus du nombre magique il faudrait tester aussi la structure des data ....
	public static boolean isJpegOrPng(InputStream file) throws IOException {
		DataInputStream ins = new DataInputStream(file);
		int firstByte = ins.readInt();
		//JPG puis PNG
		if (firstByte == 0xffd8ffe0 || firstByte == 0x89504E47) {
			return true;
		} else {
			return false;
		}
	}	
}
