package utils;

import org.primefaces.model.file.UploadedFile;

public class ValidarArquivoUtil {
	
	public static boolean validar(UploadedFile file) {
		return file != null && file.getFileName().length() > 3
				&& file.getFileName().substring(file.getFileName().length() - 3).equalsIgnoreCase("csv");
	}

}
