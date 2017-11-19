package fr.orsys.biblio.dao.memory;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.orsys.biblio.dao.AdherentDao;
import fr.orsys.biblio.dao.EmpruntDao;
import fr.orsys.biblio.dao.LivreDao;
import fr.orsys.biblio.dao.memory.AdherentDaoMemory;
import fr.orsys.biblio.dao.memory.EmpruntDaoMemory;
import fr.orsys.biblio.dao.memory.LivreDaoMemory;
import fr.orsys.biblio.entity.Adherent;
import fr.orsys.biblio.entity.Emprunt;
import fr.orsys.biblio.entity.Livre;


public class TestEmpruntDaoMemory {

	LivreDao livreDao = new LivreDaoMemory();
	AdherentDao adherentDao = new AdherentDaoMemory();
	EmpruntDao empruntDao = new EmpruntDaoMemory();
	
	
	@Test
	public  void run() {
		Livre l1 = new Livre("L'étranger",1942, "Albert Camus");
		Livre l2 = new Livre("Tintin au Tibet",1952, "Hergé");

		l1 = livreDao.save(l1);
		l2 = livreDao.save(l2);
		
		Adherent ad1 = new Adherent("Durant", "Pascal", "0240563412", "pascal.durant@free.fr");
		Adherent ad2 = new Adherent("Martin", "Jean", "0240992345", "jean.martin@laposte.fr");

		ad1 = adherentDao.save(ad1);
		ad2 = adherentDao.save(ad2);
		System.out.println(l1.getId());
		livreDao.findOne(l1.getId());
		livreDao.findOne(l2.getId());
		Adherent a1 = adherentDao.findOne(ad1.getId());
		Adherent a2 = adherentDao.findOne(ad2.getId());
		System.out.println(l1 + " " + l2);
		System.out.println(a1 + " " + a2);
		
		Emprunt e1 = new Emprunt(l1,a1);
		e1 = empruntDao.save(e1);
		
		empruntDao.save(new Emprunt(livreDao.findOne(l2.getId()), adherentDao.findOne(ad1.getId())));
		empruntDao.save(new Emprunt(livreDao.findOne(l2.getId()), adherentDao.findOne(ad2.getId())));
	
		Assert.assertEquals(3, empruntDao.findAll().size());
		Assert.assertEquals(2, empruntDao.getAllByLivre(l2.getId()).size());
		Assert.assertEquals(2, empruntDao.getAllByAdherent(ad1.getId()).size());
		Assert.assertEquals(1, empruntDao.getAllByAdherent(ad2.getId()).size());
		
		empruntDao.delete(empruntDao.findOne(e1.getId()));
		Assert.assertEquals(2, empruntDao.findAll().size());
		Assert.assertEquals(0, empruntDao.getAllByLivre(l1.getId()).size());
		Assert.assertEquals(1, empruntDao.getAllByAdherent(ad1.getId()).size());
		
		List<Emprunt> emprunts = empruntDao.findAll();
		empruntDao.delete(emprunts);
		Assert.assertEquals(0, empruntDao.findAll().size());
		Assert.assertEquals(0, empruntDao.getAllByLivre(l1.getId()).size());
		Assert.assertEquals(0, empruntDao.getAllByAdherent(ad1.getId()).size());
	
	}
	
}
