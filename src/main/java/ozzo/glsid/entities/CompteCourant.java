package ozzo.glsid.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {
private double decouvert;

public double getDecouvert() {
	return decouvert;
}

public void setDecouvert(double decouvert) {
	this.decouvert = decouvert;
}

public CompteCourant(String code, double solde, Date dateCreation, Client client,double decouvert) {
	super(code, solde, dateCreation, client);
	this.decouvert = decouvert;
}

public CompteCourant() {
	super();
	// TODO Auto-generated constructor stub
}






}