package mb;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URL;
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
import com.itextpdf.text.Image;
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
	private Calendario calendario;
	private BaseColor color;
	public static final Font RED_ITALIC = new Font(FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.RED);
	public static final Font BLUE_ITALIC = new Font(FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.BLUE);
	public static final Font GREEN_ITALIC = new Font(FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.GREEN);
	public static final Chunk B = new Chunk("b", BLUE_ITALIC);
	public static final Chunk G = new Chunk("g", GREEN_ITALIC);
	public static final Font NORMAL = new Font(FontFamily.HELVETICA, 24, Font.BOLDITALIC | Font.getStyleValue("text-align: center;"));

	public void gerarPdf() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		try {

			Document document = new Document();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);
			document.open();
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			String imageUrl = "http://i67.tinypic.com/bhay6h.png";
			Image image2 = Image.getInstance(new URL(imageUrl));
			image2.scalePercent(75, 75);
			image2.setAlignment(Element.ALIGN_CENTER);
			document.add(image2);
			Paragraph p2 = new Paragraph("Agenda Escola Aprenda Brincando",RED_ITALIC);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			BaseColor colorHeader = new BaseColor(135, 206, 250);
			CalendarioRN calendarioRN = new CalendarioRN();
			List<Calendario> calendarios = calendarioRN.listaCalendarios();
			PdfPTable table = new PdfPTable(2);
			table.setSpacingBefore(15);
			color = BaseColor.BLUE;
			for (Calendario u : calendarios) {
				PdfPCell cell = new PdfPCell(new Phrase(u.getTurma().getNomeTurma(),BLUE_ITALIC));
				cell.setRotation(0);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				PdfPTable inner2 = new PdfPTable(1);
				inner2.addCell("Titulo : " + u.getTitulo());
				inner2.addCell("Professor : " + u.getProfessor().getNome());
				inner2.addCell("Ambiente : " + u.getAmbiente().getNome());
				inner2.addCell("Sala : " + u.getTurma().getNomeTurma());
				inner2.addCell("Periodo : " + u.getTurma().getTurno());
				inner2.addCell("Alunos : " + u.getTurma().getqAluno());
				inner2.addCell("Descrição : " + u.getDescricao());
				inner2.addCell("Inicio : " + u.getInicio().toLocaleString());
				inner2.addCell("Encerramento : " + u.getFim().toLocaleString());
				cell = new PdfPCell(inner2);
				cell.setPadding(2);
				table.addCell(cell);

			}

			document.add(table);
			Paragraph p3 = new Paragraph("Esse PDF Foi Gerado Usando Java IText",RED_ITALIC);
			p3.setAlignment(Element.ALIGN_CENTER);
			document.add(p3);
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
