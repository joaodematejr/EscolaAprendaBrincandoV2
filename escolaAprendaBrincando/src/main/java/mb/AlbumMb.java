package mb;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import commons.UploadUtil;
import entity.Turma;
import entity.Documento;
import rn.AlbumRN;
import rn.TurmaRN;

@ViewScoped
@ManagedBean(name = "albumMb")
public class AlbumMb {

	private String turmaId;
	private Turma turma;
	private Documento foto;

	private Part fotoUploaded;
	private List<Documento> imagens;
	private AlbumRN albumRN;
	private TurmaRN turmaRN;

	@PostConstruct
	public void init() {
		turmaRN = new TurmaRN();
		albumRN = new AlbumRN();
		foto = new Documento();
		turma = new Turma();
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Documento> getImagens() {
		if (imagens == null && turma != null && turma.getId() != null) {
			imagens = albumRN.listarDocumentoPorTurma(turma.getId());
		}
		return imagens;
	}

	public void setImagens(List<Documento> imagens) {
		this.imagens = imagens;
	}

	public Documento getFoto() {
		return foto;
	}

	public void setFoto(Documento foto) {
		this.foto = foto;
	}

	public Part getFotoUploaded() {
		return fotoUploaded;
	}

	public void setFotoUploaded(Part fotoUploaded) {
		this.fotoUploaded = fotoUploaded;
	}

	public String getTurmaId() {
		return turmaId;
	}

	public void setTurmaId(String turmaId) {
		this.turmaId = turmaId;
	}

	public void load() {
		if (turmaId != null) {
			Long id = Long.parseLong(turmaId);
			turma = turmaRN.buscarPorId(id);
		}
	}

	public void excluir(String idImg) {
		Long id = Long.parseLong(idImg);
		Documento img = albumRN.buscarDocumentoPorId(id);

		UploadUtil.removerArquivo(img.getNome());
		albumRN.excluir(img);

		imagens = null;
	}

	public String salvar() {
		try {
			String nome = UploadUtil.moverArquivo(fotoUploaded, foto.getNome());

			foto.setNome(nome);
			foto.setTurma(turma);

			albumRN.adicionar(foto);

			return "adminarquivos?idturma=" + turma.getId().toString() + "&faces-redirect=true";
		} catch (IOException e) {
			e.printStackTrace();
			return "adminarquivos.xhtml";
		}
	}
}
