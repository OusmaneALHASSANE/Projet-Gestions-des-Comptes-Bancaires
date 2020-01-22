package ozzo.glsid.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ozzo.glsid.entities.Client;
import ozzo.glsid.entities.Compte;
import ozzo.glsid.entities.CompteCourant;
import ozzo.glsid.entities.CompteEpargne;
import ozzo.glsid.entities.Operation;
import ozzo.glsid.service.BanqueMetierImpl;
import ozzo.glsid.service.ClientMetierImpl;
import ozzo.glsid.service.IBanqueMetier;
import ozzo.glsid.service.IClientMetier;
import ozzo.glsid.dao.ClientRepository;
import ozzo.glsid.dao.CompteRepository;
@Controller
public class BanqueControleur {
	@Autowired
	private IBanqueMetier banqueMetier;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	IClientMetier clientMetier;
	@RequestMapping("/operations")
	public String index() {

		
	  return "comptes";
}
	@RequestMapping("/consulterCompte")
	public String consulter(Model model,String codeCompte, @RequestParam(name="page",defaultValue="0") int p,
			@RequestParam(name="size",defaultValue="5")int s) {
    try {
    	Compte cp=banqueMetier.consulterCompte(codeCompte);
    	Page<Operation>pageOperations=banqueMetier.listOperation(codeCompte, p, s);
    	int[] pages=new int[pageOperations.getTotalPages()];
        model.addAttribute("compte",cp);
        model.addAttribute("listOperations",pageOperations.getContent());
        model.addAttribute("pages",pages);
		model.addAttribute("size",s);
		model.addAttribute("pageCourante",p);
	} catch (Exception e) {
		model.addAttribute("exeption",e);
	}
	
    return "comptes";
}
	@RequestMapping("/Saveoperation")
	public String operation(Model model,String op,String montant,String cp,String vers ) {
		try {
			if(op.equals("versement")) {
				banqueMetier.verser(cp, Double.parseDouble(montant));
			}
			if(op.equals("retrait")) {
				banqueMetier.retirer(cp,Double.parseDouble(montant));
			}
			if(op.equals("virement")) {
				banqueMetier.virement(cp, vers,Double.parseDouble(montant));

			}
			
			model.addAttribute("operation", op);
			Compte compte=banqueMetier.consulterCompte(cp);
			model.addAttribute("cp", compte);
		
		} catch (Exception e) {
			model.addAttribute("erreurs", e);
			return "redirect:/consulterCompte?codeCompte="+cp+"&erreurs="+e.getMessage();
			
		}
		
		return "redirect:/consulterCompte?codeCompte="+cp;
}
	
	@RequestMapping(value="/clients")
	public String client( Model model,@RequestParam(name="page",defaultValue="0") int p,
		@RequestParam(name="size",defaultValue="5")int s,
		@RequestParam(name="codeCpte",defaultValue="h")String codeCpte) {
		
		Page<Client> pageClients=clientRepository.chercher("%"+codeCpte+"%",new PageRequest(p, s));
		model.addAttribute("listClients",pageClients.getContent());
		int[] pages=new int[pageClients.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size",s);
		model.addAttribute("pageCourante",p);
		model.addAttribute("codeCpte",codeCpte);
	
		return "clients";
	
	}
	@RequestMapping(value="/update")
	public String update( Model model,@RequestParam(name="page",defaultValue="0") int p,
		@RequestParam(name="size",defaultValue="5")int s,
		@RequestParam(name="codeCpte",defaultValue="h")String codeCpte) {
		
		Page<Client> pageClients=clientRepository.chercher("%"+codeCpte+"%",new PageRequest(p, s));
		model.addAttribute("listClients",pageClients.getContent());
		int[] pages=new int[pageClients.getTotalPages()];
		model.addAttribute("pages",pages);
		model.addAttribute("size",s);
		model.addAttribute("pageCourante",p);
		model.addAttribute("codeCpte",codeCpte);
	
		return "update";
	
	}
	
	 @RequestMapping(value="/delete",method=RequestMethod.GET)
		public String delete(Long code,String motCle,int page,int size) {
clientRepository.deleteById(code);
			return "redirect:/clients?page="+page+"&size="+size+"&motCle="+motCle;
		}
	 
	 @RequestMapping(value="/editer",method=RequestMethod.GET)
		public String editer(Model model,Long code,@RequestParam(name="page",defaultValue="0") int p,
				@RequestParam(name="size",defaultValue="5")int s,
				@RequestParam(name="codeCpte",defaultValue="h")String codeCpte) {
		 
		    Page<Client> pageClients=clientRepository.chercher("%"+codeCpte+"%",new PageRequest(p, s));
			model.addAttribute("listClients",pageClients.getContent());
			int[] pages=new int[pageClients.getTotalPages()];
			model.addAttribute("pages",pages);
			model.addAttribute("size",s);
			model.addAttribute("pageCourante",p);
			model.addAttribute("codeCpte",codeCpte);
            Client client=clientRepository.findById(code).orElse(null);
            model.addAttribute(client);
            Collection<Compte>listComptes=client.getComptes();
         	Page<Compte>pageComptes=compteRepository.listCompte(code, new PageRequest(p, s));
            model.addAttribute("listComptes",pageComptes.getContent());
			return  "clients";
		}
	 @RequestMapping(value="/modifier",method=RequestMethod.GET)
		public String modifier(Model model,Long code,@RequestParam(name="page",defaultValue="0") int p,
				@RequestParam(name="size",defaultValue="5")int s,
				@RequestParam(name="codeCpte",defaultValue="h")String codeCpte) {
		 
		    Page<Client> pageClients=clientRepository.chercher("%"+codeCpte+"%",new PageRequest(p, s));
			model.addAttribute("listClients",pageClients.getContent());
			int[] pages=new int[pageClients.getTotalPages()];
			model.addAttribute("pages",pages);
			model.addAttribute("size",s);
			model.addAttribute("pageCourante",p);
			model.addAttribute("codeCpte",codeCpte);
         Client client=clientRepository.findById(code).orElse(null);
         model.addAttribute(client);
			return  "update";
		}
	 @RequestMapping(value="/addClient", method=RequestMethod.GET)
		public String formProduit(Model model) {
			model.addAttribute("client",new Client());
			 return "addClient";
		}
	 @RequestMapping(value="/saveClient",method=RequestMethod.POST)
	 public String save(Model model,String c,@Valid Client client, BindingResult bindingResult) {
	if(bindingResult.hasErrors())  return "addClient";
	Compte compte=null;
	if(c.equals("cc")) {  compte=new CompteCourant();
	compte.setDateCreation(new Date());
	compte.setSolde(0.0);
	compte.setCode("cc"+0);

	}
	if(c.equals("ce")) {compte=new CompteEpargne();
	compte.setDateCreation(new Date());
	compte.setSolde(0.0);
	compte.setCode("ce"+0);
	compte.setClient(client);
	}
	Collection<Compte> comptes = new ArrayList<Compte>();
	comptes.add(compte);
	client.setComptes(comptes);
	 clientRepository.save(client);
	 compteRepository.save(compte);
	 model.addAttribute(client);
	 return "confirmationAddClient";
}
	 @RequestMapping(value="/Saveupdate",method=RequestMethod.POST)
	 public String save(Model model,@Valid Client client, BindingResult bindingResult) {
	if(bindingResult.hasErrors())  return "update";
	 clientRepository.save(client);
	 return "ConfirmationUpdateClient";
}	
	 
	 @RequestMapping(value="/addCompte",method=RequestMethod.GET)
		public String addCompte(Model model,Long codeClient,
										            @RequestParam(defaultValue="0") int page,
										            @RequestParam(defaultValue="3")int size){
			Client c=clientRepository.getOne(codeClient);
			Page<Compte> comptes=clientMetier.listComptes(codeClient, page, size);
			model.addAttribute("comptes",comptes.getContent());
			model.addAttribute("pages",comptes.getTotalPages());
			model.addAttribute("codeClient",codeClient);
			model.addAttribute("(client",c);
			model.addAttribute("pageCourant",page);
			return "formCompte";
		}	 
	 @RequestMapping(value="/saveCompte",method=RequestMethod.POST)
		public String saveCompte(Model model,Long codeClient,
			String code	,double solde ,String typeCte,@RequestParam(defaultValue="0")double decouvert ,@RequestParam(defaultValue="0")double taux,
										            @RequestParam(defaultValue="0") int page,
										            @RequestParam(defaultValue="3")int size){
			Client c=clientMetier.consulterClient(codeClient);
			if(typeCte.equals("CC")){
				Compte cpte=new CompteCourant(code, solde, new Date(), c, decouvert);
				cpte=banqueMetier.ajouterCompte(cpte);
			}else if (typeCte.equals("CE")){
				Compte cpte=new CompteEpargne(code, solde, new Date(), c, taux);
				cpte=banqueMetier.ajouterCompte(cpte);
			}
			Page<Compte> comptes=clientMetier.listComptes(codeClient,page, size);
			model.addAttribute("comptes",comptes.getContent());
			model.addAttribute("pages",comptes.getTotalPages());
			model.addAttribute("codeClient",codeClient);
			model.addAttribute("(client",c);
			model.addAttribute("pageCourant",page);
			return "ConfirmationAddCompte";
		}
	 
	 
	 
	 
	 
	 
	 
	 
}