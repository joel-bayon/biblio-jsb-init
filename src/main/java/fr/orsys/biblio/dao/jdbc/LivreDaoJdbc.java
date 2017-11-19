package fr.orsys.biblio.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import fr.orsys.biblio.dao.LivreDao;
import fr.orsys.biblio.entity.Livre;
import fr.orsys.biblio.util.ConnectionDBPool;

public class LivreDaoJdbc implements LivreDao {
	

	@Override
	public Livre save(Livre entity) {
		String requete = "INSERT INTO livre (titre, auteur, parution) VALUES (?, ?, ?)";
		Connection cxn = ConnectionDBPool.getConnection();
		try {
			PreparedStatement stmt = cxn.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, entity.getTitre());
			stmt.setString(2, entity.getAuteur());
			stmt.setInt(3, entity.getParution());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();         // Retrieve the automatically generated id    2 
			if (rs.next()) 
				entity.setId(rs.getInt(1));     
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionDBPool.close(cxn);
		}
		return entity;
	}

	@Override
	public void update(Livre entity) {
		String requete = "UPDATE livre SET titre=?, auteur=?, parution=? WHERE id_livre=?";
		Connection cxn = ConnectionDBPool.getConnection();
		try {
			PreparedStatement stmt = cxn.prepareStatement(requete);
			stmt.setString(1, entity.getTitre());
			stmt.setString(2, entity.getAuteur());
			stmt.setInt(3, entity.getParution());
			stmt.setInt(4, entity.getId());
			stmt.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionDBPool.close(cxn);
		}	
	}

	@Override
	public Livre findOne(Integer primaryKey) {
		String requete = "select * from livre where id_livre = ?";
		Connection cxn = ConnectionDBPool.getConnection();
		Livre l = null;
		
		try {
			PreparedStatement stmt = cxn.prepareStatement(requete);
			stmt.setInt(1, primaryKey);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				l = new Livre(rs.getString("titre"), rs.getInt("parution"), rs.getString("auteur"));
				l.setId(rs.getInt("id_livre"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionDBPool.close(cxn);
		}
		return l;
	}

	@Override
	public List<Livre> findAll() {
		String requete = "select * from livre";
		Connection cxn = ConnectionDBPool.getConnection();
		ArrayList<Livre> livres = new ArrayList<Livre>();
		try {
			PreparedStatement stmt = cxn.prepareStatement(requete);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Livre l = new Livre(rs.getString("titre"), rs.getInt("parution"), rs.getString("auteur"));
				l.setId(rs.getInt("id_livre"));
				livres.add(l);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionDBPool.close(cxn);
		}
		return livres;
	}

	@Override
	public void delete(Livre entity) {
		delete(entity.getId());

	}

	@Override
	public void delete(Integer id) {
		String requete = "DELETE FROM livre WHERE id_livre=?";
		Connection cxn = ConnectionDBPool.getConnection();
		try {
			PreparedStatement stmt = cxn.prepareStatement(requete);
			stmt.setInt(1, id);
			stmt.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionDBPool.close(cxn);
		}	
		
	}

	@Override
	public void delete(Iterable<Livre> entities) {
		for(Livre l : entities)
			delete(l);

	}

	@Override
	public long getCount(Livre livre) {
		String requete = "SELECT count(*) as cpt FROM livre where titre=? and auteur =?";
		Connection cxn = ConnectionDBPool.getConnection();
		BigDecimal result = null;
		try {
			PreparedStatement stmt = cxn.prepareStatement(requete);
			stmt.setString(1, livre.getTitre());
			stmt.setString(2, livre.getAuteur());
			ResultSet rs = stmt.executeQuery();
			rs.next();
			result =  rs.getBigDecimal("cpt");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionDBPool.close(cxn);
		}
		return result.longValue();
	}

}
