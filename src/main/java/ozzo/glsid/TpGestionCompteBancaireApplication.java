package ozzo.glsid;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ozzo.glsid.dao.ClientRepository;
import ozzo.glsid.dao.CompteRepository;
import ozzo.glsid.dao.OperationRepository;
import ozzo.glsid.entities.Client;
import ozzo.glsid.entities.Compte;
import ozzo.glsid.entities.CompteCourant;
import ozzo.glsid.entities.CompteEpargne;
import ozzo.glsid.entities.Operation;
import ozzo.glsid.entities.Retrait;
import ozzo.glsid.entities.Versement;
import ozzo.glsid.service.BanqueMetierImpl;
import ozzo.glsid.service.IBanqueMetier;

@SpringBootApplication
public class TpGestionCompteBancaireApplication implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier banqueMetier;
	public static void main(String[] args) {
	SpringApplication.run(TpGestionCompteBancaireApplication.class, args);
	
	
	
	}

	@Override
	public void run(String... args) throws Exception {
	
		Client c3=clientRepository.save(new Client("hassan","yayahassan@gmail.com"));
		Client c4=clientRepository.save(new Client("lala","moussaousmane@gmail.com"));
        Compte cp1=compteRepository.save(new CompteCourant("c1",9000.0,new Date(),c3,6000.0));
        Compte cp2=compteRepository.save(new CompteEpargne("c2",5000.0,new Date(),c4,5.5));
	   operationRepository.save(new Versement(new Date(), 25000, cp1));
	   operationRepository.save(new Versement(new Date(), 30000, cp1));
	   operationRepository.save(new Versement(new Date(), 15000, cp1));
	   operationRepository.save(new Versement(new Date(), 10000, cp1));
	   operationRepository.save(new Retrait(new Date(), 17000, cp1));
	   operationRepository.save(new Versement(new Date(), 25000, cp2));
	   operationRepository.save(new Versement(new Date(), 30000, cp2));
	   operationRepository.save(new Versement(new Date(), 15000, cp2));
	   operationRepository.save(new Versement(new Date(), 10000, cp2));
	   operationRepository.save(new Retrait(new Date(), 17000, cp2));

	   banqueMetier.verser("c1", 111111);
	
	}

}
