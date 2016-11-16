package mb;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
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
	private BaseColor color;
	public static final String IMAGE = "file:///C:/Users/joaod/git/EscolaAprendaBrincandoV2/escolaAprendaBrincando/src/main/webapp/resources/imagens/logo.png";

	public void gerarPdf() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		try {

			Document document = new Document();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);
			document.open();
			document.add(new Paragraph(""));
			document.addTitle("Agenda em PDF");
			CalendarioRN calendarioRN = new CalendarioRN();
			List<Calendario> calendarios = calendarioRN.listaCalendarios();
			PdfPTable table = new PdfPTable(2);
			table.setSpacingBefore(15);
			color = BaseColor.BLUE;
			for (Calendario u : calendarios) {
				PdfPCell cell = new PdfPCell(new Phrase("Turma : " + u.getTurma().getNomeTurma()));
				cell.setRotation(0);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				PdfPTable inner = new PdfPTable(1);
				inner.addCell("Titulo : " + u.getTitulo());
				inner.addCell("Professor : " + u.getProfessor().getNome());
				inner.addCell("Ambiente : " + u.getAmbiente().getNome());
				inner.addCell("Sala : " + u.getTurma().getNomeTurma());
				inner.addCell("Periodo : " + u.getTurma().getTurno());
				inner.addCell("Alunos : " + u.getTurma().getqAluno());
				inner.addCell("Descrição : " + u.getDescricao());
				inner.addCell("Inicio : " + u.getInicio().toLocaleString());
				inner.addCell("Encerramento : " + u.getFim().toLocaleString());
				cell = new PdfPCell(inner);
				cell.setPadding(2);
				table.addCell(cell);
			}

			document.add(table);
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
