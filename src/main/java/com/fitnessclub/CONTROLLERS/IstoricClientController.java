package com.fitnessclub.CONTROLLERS;

import com.fitnessclub.DOMAIN.*;
import com.fitnessclub.POJO.Istoric_client_POJO;
import com.fitnessclub.REPOSITORIES.ClientRepository;
import com.fitnessclub.REPOSITORIES.IstoricClientRepository;
import com.fitnessclub.SERVICES.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class IstoricClientController {
    @Autowired
    IstoricClientService istoricClientService;
    @Autowired
    IstoricClientRepository istoricClientRepository;

    @Autowired
    ClientRepository clientRepository;

    double calorii=0;
    double imc=0;
    long idc=0;

    @Autowired
    public IstoricClientController (IstoricClientService istoricClientService, ClientRepository clientRepository){
        this.istoricClientService=istoricClientService;
        this.clientRepository=clientRepository;
    }


    @GetMapping("/istoric/list/page/{pageNumber}")
    public String listByPage(
            Model model,
            @PathVariable("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Istoric_client> page= istoricClientService.findAll(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Istoric_client> istoricClients=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("istoricClients",istoricClients);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "contul-meu";
    }

    //aici
  @GetMapping("/istoric/list2/page/{pageNumber}")
    public String listByPage2(
            Model model,
            @PathVariable("greutatea") float greutatea)
    {
        List<Istoric_client> istoricClients= istoricClientService.findByGreutatea(greutatea);


        model.addAttribute("istoricClients",istoricClients);

        return "contul-meu";
    }

    public String listByIdc(
            Model model,
            @PathVariable("idc") long idc)
    {
        List<Istoric_client> istoricClients= istoricClientService.findByIdc(idc);


        model.addAttribute("istoricClients",istoricClients);

        return "contul-meu";
    }


        @RequestMapping("/istoric/new")
    public String newIstoric(Model model){
        //istoricClientService.findAll();
       // listByPage(model,1,"id","asc");
      //  listByPage2(model,80);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
        model.addAttribute("istoricC",new Istoric_client());
            Client client=new Client();
            client=clientRepository.findByEmail(currentPrincipalName);
            idc=client.getId();
            listByIdc(model,idc);
        model.addAttribute("calorii",calorii);
        model.addAttribute("imc",imc);
        return "contul-meu";
    }





    @PostMapping("/istoric")
    public String saveOrUpdate(@Valid @ModelAttribute("istoricC") Istoric_client_POJO istoric_client, BindingResult bindingResult){
        Client client=new Client();
        client=clientRepository.findByEmail(istoric_client.getEmailc());
        if(bindingResult.hasErrors()){
            return "contul-meu";
        }

        Istoric_client is=new Istoric_client();
       is.setId(istoric_client.getId());
       is.setData_inregistrare(is.getData_inregistrare());
       is.setGreutatea(istoric_client.getGreutatea());
       is.setInaltimea(istoric_client.getInaltimea());
       is.setVarsta(istoric_client.getVarsta());
       is.setClients(client);
       is.setIdc(client.getId());
      // idc=client.getId();
       ///calculator
        //imc


        float imca=istoric_client.getInaltimea()/100;
        System.out.println("imca: " +imca);
        float imca2=imca*imca;
        System.out.println("imca2: " +imca2);
        imc=is.getGreutatea()/imca2;
        System.out.println("imc: " +imc);

        //calorii


        if (Integer.parseInt(client.getSex())==1) {
            calorii=10 * is.getGreutatea() + 6.25 * is.getInaltimea() - 5 * is.getVarsta() +5;
        }

            else
        {
            calorii=10 * is.getGreutatea() + 6.25 * is.getInaltimea() - 5 * is.getVarsta() -161;
        }

        System.out.println("calorii: " +calorii);

        Istoric_client saveduIstoric_client=istoricClientService.save(is);


        return "redirect:/istoric/new";
    }

    @GetMapping("/istoric/findByGreutatea/{greutatea}")
    public List<Istoric_client> findByGreutatea(@PathVariable long greutatea) {
        return istoricClientService.findByGreutatea(greutatea);
    }


}
