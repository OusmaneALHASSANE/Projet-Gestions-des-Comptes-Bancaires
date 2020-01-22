package ozzo.glsid.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name="TYPE_CPTE",discriminatorType=DiscriminatorType.STRING,length=2)
public abstract class Compte implements Serializable {
@Id
private String code;
private double solde;
private Date dateCreation;
@ManyToOne
@JoinColumn(name="CODE_CLI")
private Client client;
@OneToMany(mappedBy="compte",fetch=FetchType.LAZY)
private Collection<Operation> operations;
// Gnénérer un constructeur sans param
public Compte() { super(); }
// Générer un constructeur avec params

public Compte(String code2, double solde2, Date dateCreation2, Client client2) {
	// TODO Auto-generated constructor stub
	super(); this.code = code2; this.solde = solde2;
	this.dateCreation = dateCreation2; this.client = client2;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public double getSolde() {
	return solde;
}
public void setSolde(double solde) {
	this.solde = solde;
}
public Date getDateCreation() {
	return dateCreation;
}
public void setDateCreation(Date dateCreation) {
	this.dateCreation = dateCreation;
}
public Client getClient() {
	return client;
}
public void setClient(Client client) {
	this.client = client;
}
public Collection<Operation> getOperations() {
	return operations;
}
public void setOperations(Collection<Operation> operations) {
	this.operations = operations;
}

}
