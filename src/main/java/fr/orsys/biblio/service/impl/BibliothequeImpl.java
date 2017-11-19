package fr.orsys.biblio.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.orsys.biblio.dao.AdherentDao;
import fr.orsys.biblio.dao.EmpruntDao;
import fr.orsys.biblio.dao.LivreDao;
import fr.orsys.biblio.dao.jdbc.LivreDaoJdbc;
import fr.orsys.biblio.dao.memory.AdherentDaoMemory;
import fr.orsys.biblio.dao.memory.EmpruntDaoMemory;
import fr.orsys.biblio.entity.Adherent;
import fr.orsys.biblio.entity.Emprunt;
import fr.orsys.biblio.entity.Livre;
import fr.orsys.biblio.service.Bibliotheque;
import fr.orsys.biblio.service.BusinessException;

public class BibliothequeImpl implements Bibliotheque {
	final int maxLivreIdentique;
	final int maxEmpruntAdherent;
	
	LivreDao livreDao = new LivreDaoJdbc();
	AdherentDao adherentDao = new AdherentDaoMemory();
	EmpruntDao empruntDao = new EmpruntDaoMemory();
	
	@Autowired
	public BibliothequeImpl(@Value("3")   Integer maxLivreIdentique, @Value("5") Integer maxEmpruntAdherent) {
		this.maxLivreIdentique = maxLivreIdentique; 
		this.maxEmpruntAdherent = maxEmpruntAdherent;
	}

	@Override
	public int getMaxEmpruntAdherent() { 
		return maxEmpruntAdherent;
	}
	
	@Override
	public int getMaxLivreIdentique() {
		return maxLivreIdentique;
	}
	
	@Override
	public int ajouterLivre(Livre livre)  {
		//RG : maxLivreIdentique ?
		if(livreDao.getCount(livre) == maxLivreIdentique ) 
			throw new BusinessException("BibliothequeImpl.ajouterLivre", null);
		livreDao.save(livre);
		return livre.getId();
	}


	@Override
	public void retirerLivre(int idLivre) {
		//RG : livre vacant ?
		if(empruntDao.getEmpruntEnCoursByLivre(idLivre) != null) 
			throw new BusinessException("BibliothequeImpl.retirerLivre", null); 
		//détruire d'abord les anciens emprunts puis le live ....
		empruntDao.delete(empruntDao.getAllByLivre(idLivre));
		livreDao.delete(idLivre);
	}

	@Override
	public int ajouterAdherent(Adherent adherent) {
		//RG est déjà Present ?
		if(adherentDao.isPresent(adherent))
			throw new BusinessException("BibliothequeImpl.ajouterAdherent", null); 
		adherentDao.save(adherent);
		return adherent.getId();
	}


	@Override
	public void retirerAdherent(int idAdherent) {
		//RG : adherent vacant ?
		if(empruntDao.getEmpruntsEnCoursByAdherent(idAdherent).size() > 0)
			throw new BusinessException("BibliothequeImpl.retirerAdherent", null); 
		//détruire d'abord les anciens emprunts puis l'adhérent ....
		empruntDao.delete(empruntDao.getAllByAdherent(idAdherent));
		adherentDao.delete(idAdherent);	
	}

	@Override
	public void emprunterLivre(int idLivre, int idAdherent) {
		//RG : maxEmpruntAdherent ?
		if( empruntDao.getAllByAdherent(idAdherent).size() == maxEmpruntAdherent)
			throw new BusinessException("BibliothequeImpl.emprunterLivre", null); 
		//RG : livre déjà emprunté !
		if(empruntDao.getEmpruntEnCoursByLivre(idLivre) != null)
			throw new BusinessException("BibliothequeImpl.emprunterLivre", null); 

		empruntDao.save(new Emprunt(livreDao.findOne(idLivre), adherentDao.findOne(idAdherent)));
	}
	
	@Override
	public void restituerLivre(int idLivre, int idAdherent) {
		// RG : un emprunt doit exist� avec le couple idLivre/idAdherent
		Emprunt emprunt = empruntDao.getEmpruntEnCoursByLivre(idLivre);
		if(emprunt == null || emprunt.getAdherent().getId() != idAdherent)
			throw new BusinessException("BibliothequeImpl.restituerLivre", null);  /// A finir ...
		emprunt.setFin(new Date());
		empruntDao.update(emprunt);
		
	}

	@Override
	public void transfererEmprunt(int idAdherentPrecedent, int idLivre,
			int idAdherentSuivant) {
		restituerLivre(idLivre, idAdherentPrecedent);
		emprunterLivre(idLivre, idAdherentSuivant);	
	}

	@Override
	public List<Livre> listerLivre() {
		// TODO Auto-generated method stub
		return livreDao.findAll();
	}

	@Override
	public LivreDao getLivreDao() {
		
		// TODO Auto-generated method stub
		return livreDao;
	}

	@Override
	public AdherentDao getAdherentDao() {
		// TODO Auto-generated method stub
		return adherentDao;
	}

	@Override
	public EmpruntDao getEmpruntDao() {
		// TODO Auto-generated method stub
		return empruntDao;
	}
	

}
