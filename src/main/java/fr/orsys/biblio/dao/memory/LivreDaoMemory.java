package fr.orsys.biblio.dao.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.orsys.biblio.dao.LivreDao;
import fr.orsys.biblio.entity.Livre;

public class LivreDaoMemory implements LivreDao {
	
	static Map<Integer, Livre> livres = new HashMap<>();
	

	@Override
	public Livre save(Livre entity) {
		int id = SequenceCounter.getId();
		entity.setId(id);
		livres.put(id, entity);
		return entity;
	}

	@Override
	public void update(Livre entity) {
		livres.put(entity.getId(), entity);
	}

	@Override
	public Livre findOne(Integer primaryKey) {
		// TODO Auto-generated method stub
		return livres.get(primaryKey);
	}

	@Override
	public List<Livre> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<Livre>(livres.values());
	}

	@Override
	public void delete(Livre entity) {
		livres.remove(entity.getId());

	}

	@Override
	public void delete(Integer id) {
		livres.remove(id);
	}

	@Override
	public void delete(Iterable<Livre> entities) {
		for(Livre l : entities)
			delete(l);

	}

	@Override
	public long getCount(Livre livre) {
		// TODO Auto-generated method stub
		int count = 0;
		for(Livre l : livres.values())
			if((l.getAuteur().equals(livre.getAuteur())) && (l.getTitre().equals(livre.getTitre())))
					count++;
		return count;
	}

}
