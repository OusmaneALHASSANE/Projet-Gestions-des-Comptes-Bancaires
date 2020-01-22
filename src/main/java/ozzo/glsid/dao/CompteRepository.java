package ozzo.glsid.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ozzo.glsid.entities.Compte;

public interface CompteRepository  extends JpaRepository<Compte, String>{
	@Query("select c from Compte c where c.client.code=:x order by c.dateCreation desc")
	public Page<Compte> listCompte(@Param("x")Long code,org.springframework.data.domain.Pageable pageable);
}
