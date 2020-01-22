package ozzo.glsid.service;


import org.springframework.data.domain.Page;

import ozzo.glsid.entities.Client;
import ozzo.glsid.entities.Compte;

public interface IClientMetier {

	public Client consulterClient(Long codeClt);
	public void supprimerClient(Long codeClt);
	public Client ajouter(Client c);
	//public Client modifier(Client c);
	public Page<Client> listClients(String motCle,int page,int size);
	public Page<Compte> listComptes(Long codeClt,int page,int size);
}
