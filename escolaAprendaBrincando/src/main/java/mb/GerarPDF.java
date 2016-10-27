package mb;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.Calendario;
import rn.CalendarioRN;

@ManagedBean
@RequestScoped

/* http://developers.itextpdf.com/examples-itext5 */
public class GerarPDF {
	public void gerarPdf() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		try {
			Document document = new Document();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);
			document.open();
			String s = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
			s = s + "/resources/imagens/logo.png";
			document.addTitle("Agenda em PDF");
			document.add(new Paragraph(""));
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);

			CalendarioRN calendarioRN = new CalendarioRN();
			List<Calendario> calendarios = calendarioRN.listaCalendarios();

			PdfPTable table = new PdfPTable(7);
			PdfPCell cell = new PdfPCell(new Phrase("Agenda Geral"));
			cell.setColspan(8);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table.addCell(cell);
			table.addCell(createBody("Turma", null));

			for (Calendario u : calendarios) {
				table.addCell(createBody(u.getInicio().toString(), null));
				table.addCell(createBody(u.getFim().toString(), null));
				table.addCell(createBody(u.getTitulo(), null));
				table.addCell(createBody(u.getDescricao(), null));
				table.addCell(createBody(u.getTurma().getNomeTurma(), null));

				document.add(table);
			}
			document.close();
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline;filename=teste.pdf");
			response.setContentLength(baos.size());
			OutputStream os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			System.out.println("Erro ao gerar o pdf!");
			e.printStackTrace();
		}
		context.responseComplete();
	}

	private PdfPCell createHeader(String titulo) {
		Phrase phrase = new Phrase(titulo);
		phrase.setFont(new Font(FontFamily.HELVETICA, 16, Font.BOLD));
		PdfPCell cell = new PdfPCell(phrase);
		cell.setBorder(Rectangle.BOTTOM);
		cell.setBorderWidthBottom(3);
		return cell;
	}

	private PdfPCell createBody(String value, BaseColor color) {
		Phrase phrase = new Phrase(value);
		phrase.setFont(new Font(FontFamily.HELVETICA, 14, Font.NORMAL));
		PdfPCell cell = new PdfPCell(phrase);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setBackgroundColor(color);
		return cell;
	}

}
