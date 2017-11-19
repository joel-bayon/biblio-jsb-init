package fr.orsys.biblio.dao.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.orsys.biblio.dao.EmpruntDao;
import fr.orsys.biblio.entity.Emprunt;

public class EmpruntDaoMemory implements EmpruntDao {
	
	static Map<Integer, Emprunt> emprunts = new HashMap<>();

	@Override
	public Emprunt save(Emprunt entity) {
		int id = SequenceCounter.getId();
		entity.setId(id);
		emprunts.put(id, entity);
		return entity;
	}

	@Override
	public void update(Emprunt entity) {
		emprunts.put(entity.getId(), entity);
	}

	@Override
	public Emprunt findOne(Integer primaryKey) {
		// TODO Auto-generated method stub
		return emprunts.get(primaryKey);
	}

	@Override
	public List<Emprunt> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<Emprunt>(emprunts.values());
	}

	@Override
	public void delete(Emprunt entity) {
		emprunts.remove(entity.getId());

	}

	@Override
	public void delete(Integer id) {
		emprunts.remove(id);
	}

	@Override
	public void delete(Iterable<Emprunt> entities) {
		for(Emprunt l : entities)
			delete(l);

	}

	@Override
	public List<Emprunt> getAllByLivre(int livreId) {
		List<Emprunt> es = new ArrayList<>();
		for(Emprunt e : emprunts.values())
			if(e.getLivre().getId() == livreId)
				es.add(e);
		return es;
	}

	@Override
	public Emprunt getEmpruntEnCoursByLivre(int livreId) {
		Emprunt e = null;
		for(Emprunt em : emprunts.values())
			if(em.getLivre().getId() == livreId && em.getFin() == null)
				e=em;
		return e;
	}

	@Override
	public List<Emprunt> getAllByAdherent(int adherentId) {
		List<Emprunt> es = new ArrayList<>();
		for(Emprunt e : emprunts.values())
			if(e.getAdherent().getId() == adherentId)
				es.add(e);
		return es;
	}

	@Override
	public List<Emprunt> getEmpruntsEnCoursByAdherent(int adherentId) {
		List<Emprunt> es = new ArrayList<>();
		for(Emprunt e : emprunts.values())
			if(e.getAdherent().getId() == adherentId && e.getFin() == null)
				es.add(e);
		return es;
	}


}
