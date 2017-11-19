package fr.orsys.biblio.dao.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.orsys.biblio.dao.AdherentDao;
import fr.orsys.biblio.entity.Adherent;

public class AdherentDaoMemory implements AdherentDao {
	
	static Map<Integer, Adherent> adherents = new HashMap<>();
	

	@Override
	public Adherent save(Adherent entity) {
		int id = SequenceCounter.getId();
		entity.setId(id);
		adherents.put(id, entity);
		return entity;
	}

	@Override
	public void update(Adherent entity) {
		adherents.put(entity.getId(), entity);
	}

	@Override
	public Adherent findOne(Integer primaryKey) {
		// TODO Auto-generated method stub
		return adherents.get(primaryKey);
	}

	@Override
	public List<Adherent> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<Adherent>(adherents.values());
	}

	@Override
	public void delete(Adherent entity) {
		adherents.remove(entity.getId());

	}

	@Override
	public void delete(Integer id) {
		adherents.remove(id);
	}

	@Override
	public void delete(Iterable<Adherent> entities) {
		for(Adherent l : entities)
			delete(l);

	}


	@Override
	public boolean isPresent(Adherent adherent) {
		for(Adherent l : adherents.values())
			if(l.equals(adherent))
				return true;
		return false;
	}

}
