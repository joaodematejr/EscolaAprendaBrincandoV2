package entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Turma {
	@Id
	@GeneratedValue
	private Long id;
	private String nomeTurma;
	private String qAluno;
	private String turno;
	private String semestre;
	private Date dataInicio;
	private Date dataFinal;
	@ManyToOne
	private Cliente professor;
	@ManyToMany
	private List<Cliente> clienteTurma;
	@ManyToMany
	private List<Cliente> aluno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	public String getqAluno() {
		return qAluno;
	}

	public void setqAluno(String qAluno) {
		this.qAluno = qAluno;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Cliente getProfessor() {
		return professor;
	}

	public void setProfessor(Cliente professor) {
		this.professor = professor;
	}

	public List<Cliente> getClienteTurma() {
		return clienteTurma;
	}

	public void setClienteTurma(List<Cliente> clienteTurma) {
		this.clienteTurma = clienteTurma;
	}

	public List<Cliente> getAluno() {
		return aluno;
	}

	public void setAluno(List<Cliente> aluno) {
		this.aluno = aluno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((clienteTurma == null) ? 0 : clienteTurma.hashCode());
		result = prime * result + ((dataFinal == null) ? 0 : dataFinal.hashCode());
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeTurma == null) ? 0 : nomeTurma.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((qAluno == null) ? 0 : qAluno.hashCode());
		result = prime * result + ((semestre == null) ? 0 : semestre.hashCode());
		result = prime * result + ((turno == null) ? 0 : turno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Turma other = (Turma) obj;
		if (aluno == null) {
			if (other.aluno != null) {
				return false;
			}
		} else if (!aluno.equals(other.aluno)) {
			return false;
		}
		if (clienteTurma == null) {
			if (other.clienteTurma != null) {
				return false;
			}
		} else if (!clienteTurma.equals(other.clienteTurma)) {
			return false;
		}
		if (dataFinal == null) {
			if (other.dataFinal != null) {
				return false;
			}
		} else if (!dataFinal.equals(other.dataFinal)) {
			return false;
		}
		if (dataInicio == null) {
			if (other.dataInicio != null) {
				return false;
			}
		} else if (!dataInicio.equals(other.dataInicio)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (nomeTurma == null) {
			if (other.nomeTurma != null) {
				return false;
			}
		} else if (!nomeTurma.equals(other.nomeTurma)) {
			return false;
		}
		if (professor == null) {
			if (other.professor != null) {
				return false;
			}
		} else if (!professor.equals(other.professor)) {
			return false;
		}
		if (qAluno == null) {
			if (other.qAluno != null) {
				return false;
			}
		} else if (!qAluno.equals(other.qAluno)) {
			return false;
		}
		if (semestre == null) {
			if (other.semestre != null) {
				return false;
			}
		} else if (!semestre.equals(other.semestre)) {
			return false;
		}
		if (turno == null) {
			if (other.turno != null) {
				return false;
			}
		} else if (!turno.equals(other.turno)) {
			return false;
		}
		return true;
	}

}
