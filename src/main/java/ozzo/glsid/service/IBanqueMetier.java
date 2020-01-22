package ozzo.glsid.service;

import org.springframework.data.domain.Page;

import ozzo.glsid.entities.Compte;
import ozzo.glsid.entities.Operation;

public interface IBanqueMetier {
public Compte ajouterCompte(Compte compte);
public Compte consulterCompte(String codeCpte);
public void verser(String codeCpte,double montant);
public void retirer(String codeCpte,double montant);
public void virement(String codeCpte1,String codeCpte2,double montant);
public Page<Operation>listOperation(String codeCpte,int page,int size);
}
