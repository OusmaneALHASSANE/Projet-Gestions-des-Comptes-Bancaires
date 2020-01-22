package ozzo.glsid.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {
private double taux;
// Constructeur sans param
public CompteEpargne() { super(); }
// Constructeur avec params

public CompteEpargne(String code, double solde, Date dateCreation, Client client,double taux) {
	super(code, solde, dateCreation, client);
	this.taux = taux;
	// TODO Auto-generated constructor stub
}
public double getTaux() {
	return taux;
}
public void setTaux(double taux) {
	this.taux = taux;
}


}