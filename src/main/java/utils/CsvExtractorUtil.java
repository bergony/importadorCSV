package utils;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.primefaces.model.file.UploadedFile;

public class CsvExtractorUtil {
	
	public List<String> extrairColuna(UploadedFile file, int coluna, boolean isString) throws IOException {
		Reader targetReader = new StringReader(new String(file.getContent()));
		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(targetReader);
		List<String> listaString = new ArrayList<>();
		List<String> listaInt = new ArrayList<>();
		Integer subTotal = 0;
		Integer total = 0;
		int index = 0;

		for (CSVRecord record : records) {

			if (index == 0) {
				index++;
				continue;
			}
			
			String chave = record.get(coluna);
			Integer valor = isString ? 0 : Integer.valueOf(record.get(coluna));
			
			listaString.add(chave);
			listaInt.add(valor.toString());
			subTotal += valor;

			if (index % 20 == 0) {
				total += subTotal;
				listaString.add("SubTotal_" + index / 20);
				listaInt.add(subTotal.toString());
				subTotal = 0;
			}
			index++;
		}
		
		listaString.add("Total ");
		listaInt.add(total.toString());
		
		return isString ? listaString : listaInt;
	}

}
