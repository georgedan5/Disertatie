package com.fitnessclub.CONTROLLERS;


import com.fitnessclub.DOMAIN.*;
import com.fitnessclub.REPOSITORIES.ClientRepository;
import com.fitnessclub.REPOSITORIES.CosRepository;
import com.fitnessclub.SERVICES.ComandaService;
import com.fitnessclub.SERVICES.CosService;
import com.fitnessclub.SERVICES.DetaliiComandaService;
import com.fitnessclub.SERVICES.MesajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ComandaController {

    @Autowired
    CosRepository cosRepository;

    @Autowired
    ComandaService comandaService;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CosService cosService;

    @Autowired
    DetaliiComandaService detaliiComandaService;


    @RequestMapping("/comanda/list")
    public String ComandaList(Model model){
        return listByPage(model,1,"id","asc");
    }

    @GetMapping("/comenzi/page/{pageNumber}")
    public String listByPage(
            Model model,
            @PathVariable("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {

        Page<Comanda> page= comandaService.findAll(currentPage,sortField,sortDirection);



        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Comanda> comandaAfi=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("comandaAfi",comandaAfi);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "comenzi";
    }


    @RequestMapping("/comanda/new")
    public String newComanda(Model model) {

        model.addAttribute("comanda", new Comanda());

        return "cos-cump";
    }

    @PostMapping("/comanda")
    public String saveOrUpdate(@Valid @ModelAttribute Comanda comanda, BindingResult bindingResult)
    {


        if (bindingResult.hasErrors()){
            return "cos-cump";

        }


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Client client=new Client();

        client=clientRepository.findByEmail(currentPrincipalName);

        Comanda savedComanda=new Comanda();
        savedComanda.setEmail(currentPrincipalName);

        savedComanda.setNume(client.getNume());

        savedComanda.setPrenume(client.getPrenume());

        savedComanda.setTelefon(client.getTelefon());

        savedComanda.setStatus("Inregistrata");

        savedComanda.setAdresa(comanda.getAdresa());

        savedComanda.setObservatie(comanda.getObservatie());

        savedComanda.setId_client(client.getId());


        float total=0;
        List<Cos> cosP=cosService.findByIdu(client.getId());


        //List<Cos> cosP=cosService.findAll();
        for (int i = 0; i < cosP.size(); i++) {
            total=total + cosP.get(i).getPret();

        }
        savedComanda.setTotalC(total);

        comandaService.save(savedComanda);

        for (int i = 0; i < cosP.size(); i++) {
            Detalii_comanda detalii_comanda=new Detalii_comanda();
            Produs pr=cosP.get(i).getProdusC();

            detalii_comanda.setComanda(savedComanda);
            detalii_comanda.setProdus(pr);
            detaliiComandaService.save(detalii_comanda);
        }






        cosRepository.deleteByIdu(client.getId());
        return  "redirect:cos/list";
    }


    @RequestMapping("/comanda/delete/{id}")
    public String deleteById(@PathVariable String id){
        comandaService.deleteById(Long.valueOf(id));
        return "redirect:/comanda/list";
    }
}
