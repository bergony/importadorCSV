package com.bergony.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import utils.CsvExtractorUtil;
import utils.PdfUtil;
import utils.ValidarArquivoUtil;

@Named
@ManagedBean
@ViewScoped
public class ImportadorBean {

	private UploadedFile file;
	private StreamedContent pdf;
	
	private List<String> listaA;
	private List<String> listaC;

	public void upload() {
		 CsvExtractorUtil csvExtractorUtil = new CsvExtractorUtil();
		
		if (ValidarArquivoUtil.validar(file)) {
			setListaA(new ArrayList<>());
			setListaC(new ArrayList<>());
		
			try {
				
				listaA = csvExtractorUtil.extrairColuna(file, 0, true);
				listaC = csvExtractorUtil.extrairColuna(file, 2, false);
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bem Sucedido", file.getFileName() + " foi carregado."));
				
			} catch (IOException e) {
				FacesMessage message = new FacesMessage("Error", " Error no arquivo CSV.");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, message);
				e.printStackTrace();
			}
		} else {
			FacesMessage message = new FacesMessage("Falha", " Formato inválido, arquivo deve ser CSV.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	

	public StreamedContent getPdf() throws FileNotFoundException {

		PdfUtil pdfUtil = new PdfUtil();
		if(listaA == null || listaA.isEmpty() || listaC == null || listaC.isEmpty()) {
			FacesMessage message = new FacesMessage("Falha", " Lista Invalida.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		pdf =  pdfUtil.gerarPDF(listaA, listaC);

		return pdf;
	}


	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<String> getListaA() {
		return listaA;
	}

	public void setListaA(List<String> listaA) {
		this.listaA = listaA;
	}



	public List<String> getListaC() {
		return listaC;
	}



	public void setListaC(List<String> listaC) {
		this.listaC = listaC;
	}

}
