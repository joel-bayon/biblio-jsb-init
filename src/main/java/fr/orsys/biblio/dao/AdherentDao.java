package fr.orsys.biblio.dao;
import fr.orsys.biblio.entity.Adherent;

public interface AdherentDao extends Dao<Integer, Adherent> {
	public boolean isPresent(Adherent adherent) ;
}
