package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Chamada {
	@Id
	@GeneratedValue
	private Long id;
	private Date data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
