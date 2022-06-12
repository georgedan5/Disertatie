package com.fitnessclub.CONTROLLERS;
import com.fitnessclub.DOMAIN.Client;
import com.fitnessclub.DOMAIN.Rol;
import com.fitnessclub.DOMAIN.Utilizator;
import com.fitnessclub.REPOSITORIES.ClientRepository;
import com.fitnessclub.SERVICES.ClientService;
import com.fitnessclub.SERVICES.RolService;
import com.fitnessclub.SERVICES.UtilizatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
public class ClientController {
    @Autowired
    ClientService clientService;

    @Autowired
    UtilizatorService utilizatorService;
    @Autowired
    RolService rolService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService=clientService;
        this.utilizatorService=utilizatorService;
        this.rolService=rolService;
        this.clientRepository=clientRepository;
    }

/*
   @GetMapping("/cient/name")
   public ResponseEntity<Client> findByEmail(@RequestParam String name) {

       return new ResponseEntity<Client>(clientRepository.findByEmail(name), HttpStatus.OK);
   }


 */
/*
    @GetMapping("/client/name")
    public void findByEmail(@RequestParam String name) {
        Client asd=new Client();
        asd=new ResponseEntity<Client>(clientRepository.findByEmail(name), HttpStatus.OK).getBody();
        System.out.println("aoloo:"+asd.getEmail()+asd.getTelefon());

    }
 */

/*
    //sau folosind ModelAndView
    @RequestMapping("/clienti/list")
    public String clientiList(Model model){
        return listByPage(model,1,"id","asc");
    }

    @GetMapping("/client/list/page/{pageNumber}")
    public String listByPage(
            Model model,
            @PathVariable ("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Client> page= clientService.findAll(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Client> clients=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("clients",clients);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "clients";

    }


    @RequestMapping("/client/delete/{id}")
    public String deleteById(@PathVariable String id){
        clientService.deleteById(Long.valueOf(id));
        return "redirect:/client/list";
    }
*/


    @RequestMapping("/client/new")
    public String newClient(Model model) {
        /*List<Client> clientsAll = clientService.findAll();

         */
        model.addAttribute("client", new Client());
        return "register";
    }

    @PostMapping("/client")
    public String saveOrUpdate(@Valid  @ModelAttribute Client client, BindingResult bindingResult)
    {

        if (bindingResult.hasErrors()){
            return "register";
        }
        String encoded = new BCryptPasswordEncoder().encode(client.getParola());
        client.setParola(encoded);
        Client savedClient=clientService.save(client);

        //add utilizator when a new client has been added
        Utilizator utilizator=new Utilizator();
        utilizator.setId(client.getId());
        utilizator.setUsername(client.getEmail());
        utilizator.setParola(client.getParola());
        long stat=1;
        utilizator.setStatus(stat);
        utilizator.setCli(client);

        Utilizator savedutilizator=utilizatorService.save(utilizator);

        //add role
        Rol rol=new Rol();
        rol.setId(client.getId());
        rol.setUsername(client.getEmail());
        rol.setRol("ROLE_USER");

        Rol savedrol=rolService.save(rol);
        return  "redirect:/showLogInForm";
    }

/*
    //update
    @RequestMapping("/product/update/{id}")
    public ModelAndView updateProductById(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("productUpdate");
        Product product = productService.findById(Long.valueOf(id));
        modelAndView.addObject("productUpdate",product);
        List<Category> categoriesAll = categoryService.findAll();
        modelAndView.addObject("categoriesAll", categoriesAll);
        List<Provider> providerAll=providerService.findAll();
        modelAndView.addObject("providerAll",providerAll);
        return modelAndView;
    }

 */
    @GetMapping("/client/email")
    public Client getClientByEmail(@RequestParam String email) {
        Client ll=clientRepository.findByEmail(email);
        System.out.println("Id" +ll.getId());
        return ll;
    }




}
