package fr.orsys.biblio.service;

import java.util.List;

import fr.orsys.biblio.dao.AdherentDao;
import fr.orsys.biblio.dao.EmpruntDao;
import fr.orsys.biblio.dao.LivreDao;
import fr.orsys.biblio.entity.Adherent;
import fr.orsys.biblio.entity.Livre;

public interface Bibliotheque {
	
	public int getMaxLivreIdentique();
	public int getMaxEmpruntAdherent();
	
	public int ajouterLivre(Livre livre) ;
	public void retirerLivre(int idLivre) ;
	
	public int ajouterAdherent(Adherent adherent) ;
	public void retirerAdherent(int idAdherent);
	
	public void emprunterLivre(int idLivre, int idAdherent);
	public void transfererEmprunt(int idAdherentPrecedent, int idLivre, int idAdherentSuivant);
	public void restituerLivre(int idLivre, int idAdherent);
	
	public List<Livre> listerLivre();
	public LivreDao getLivreDao();
	public AdherentDao getAdherentDao();
	public EmpruntDao getEmpruntDao();

}
