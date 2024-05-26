package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class PdfUtil {
	
	public StreamedContent gerarPDF(List<String> listaA, List<String> listaC) throws FileNotFoundException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
		Integer pageIndex = 0;
		
		try {
			PDPageContentStream contentStream = criarPagina(document, page, pageIndex);

			int paddingLinha = 0;
			
			for (int i = 0; i < listaA.size(); i++) {
				adicionarTextoPdf(contentStream, 20,775 - paddingLinha, listaA.get(i) + " " + listaC.get(i));
				
				paddingLinha += 15;
				
				if( paddingLinha == 630) {
					contentStream.close();
					
					PDPage secondPage = new PDPage();
					document.addPage(secondPage);
					pageIndex++;
					
					contentStream = criarPagina(document, secondPage, pageIndex);
					
					paddingLinha = 0;
				}
			}
			
			
			contentStream.close();
			
			document.save(out);
			document.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return DefaultStreamedContent.builder()
                .name("pdfGerado.pdf")
                .contentType("application/pdf")
                .stream(() -> new ByteArrayInputStream(out.toByteArray())).build();
	}

	private PDPageContentStream criarPagina(PDDocument document, PDPage page, Integer pageIndex) throws IOException {
		
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
		adicionarTextoPdf(contentStream, 480, 775, new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
		adicionarTextoPdf(contentStream, 550, 20, pageIndex.toString());
		return contentStream;
	}

	private void adicionarTextoPdf(PDPageContentStream contentStream,Integer eixoY, Integer eixoX, String message) throws IOException {
		contentStream.beginText();
		contentStream.newLineAtOffset(eixoY,eixoX);
		contentStream.showText(message);
		contentStream.endText();
	}

}
