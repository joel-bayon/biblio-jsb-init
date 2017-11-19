package fr.orsys.biblio.entity;

import java.util.Date;


public class Emprunt {
	
	Integer id; 
	Date debut;
	Date fin;

	Livre livre;
	Adherent adherent;
	
	public Emprunt(Livre livre, Adherent adherent, Date debut, Date fin) {
		this.livre = livre;
		this.adherent = adherent;
		this.debut = debut;
		this.fin = fin;
	}	
	
	public Emprunt(Livre livre, Adherent adherent) {
		this.livre = livre;
		this.adherent = adherent;
		this.debut = new Date();
	}
	
	public Emprunt() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public Adherent getAdherent() {
		return adherent;
	}

	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}

	@Override
	public String toString() {
		return "id=" + id + ", debut=" + debut + ", fin=" + fin 
				+ ", livre=" + getLivre().titre + ", adherent=" + getAdherent().getNom();
	}
	
	
}
