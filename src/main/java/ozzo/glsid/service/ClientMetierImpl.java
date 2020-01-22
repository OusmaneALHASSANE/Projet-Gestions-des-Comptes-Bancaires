package ozzo.glsid.service;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ozzo.glsid.dao.ClientRepository;
import ozzo.glsid.dao.CompteRepository;
import ozzo.glsid.entities.Client;
import ozzo.glsid.entities.Compte;

@Service
@Transactional
public class ClientMetierImpl implements IClientMetier {

	@Autowired
	ClientRepository clientRepository;
	@Autowired
	CompteRepository compteRepository;
	
	@Override
	public Client consulterClient(Long codeClt) {
		
		return clientRepository.findById(codeClt).get();
	}

	@Override
	public void supprimerClient(Long codeClt) {
		clientRepository.deleteById(codeClt);
	}

	@Override
	public Client ajouter(Client c) {
		return clientRepository.save(c);
	}

	@Override
	public Page<Compte> listComptes(Long codeClt,int page, int size) {	
		return compteRepository.listCompte(codeClt, new PageRequest(page, size)) ;
	}

	@Override
	public Page<Client> listClients(String motCle, int page, int size) {	
		return clientRepository.chercher(motCle, new PageRequest(page, size));
	}

}
