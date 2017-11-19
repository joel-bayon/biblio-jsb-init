package fr.orsys.biblio.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<K extends Serializable, E> {
	public E save(E entity);
	public void update(E entity);
	public E findOne(K primaryKey);
	public List<E> findAll();
	public void delete(E entity);
	public void delete(K  id);
	public void delete(Iterable<E> entities) ;	
	
}

