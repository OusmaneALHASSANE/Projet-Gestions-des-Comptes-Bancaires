package ozzo.glsid.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Client implements Serializable{
@Id @GeneratedValue
private Long code;
private String nom;
private String email;

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
@OneToMany(mappedBy="client",fetch=FetchType.LAZY)
private Collection<Compte> comptes=new ArrayList<>();
public Long getCode() {
	return code;
}
public void setCode(Long code) {
	this.code = code;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
	
}
public Collection<Compte> getComptes() {
	return comptes;
}
public void setComptes(Collection<Compte> comptes) {
	this.comptes = comptes;
}
// Gnénérer un constructeur sans param
public Client() { super(); }
// Générer un constructeur avec params
public Client(String nom,String email) { super();
this.nom = nom;
this.email=email;
}
//Générer les getters et setters
}