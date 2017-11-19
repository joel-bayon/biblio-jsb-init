package fr.orsys.biblio.dao;
import fr.orsys.biblio.entity.Livre;

public interface LivreDao extends Dao<Integer, Livre> {
	public long getCount(Livre livre);
}
