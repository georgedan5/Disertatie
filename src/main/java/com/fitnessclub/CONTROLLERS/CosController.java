package com.fitnessclub.CONTROLLERS;


import com.fitnessclub.DOMAIN.Client;
import com.fitnessclub.DOMAIN.Comanda;
import com.fitnessclub.DOMAIN.Cos;
import com.fitnessclub.DOMAIN.Produs;
import com.fitnessclub.REPOSITORIES.ClientRepository;
import com.fitnessclub.REPOSITORIES.CosRepository;
import com.fitnessclub.SERVICES.CosService;
import com.fitnessclub.SERVICES.ProdusService;
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
public class CosController {
    @Autowired
    CosService cosService;

    @Autowired
    CosRepository cosRepository;
    @Autowired
    ProdusService produsService;

    @Autowired
    ClientRepository clientRepository;

    @RequestMapping("/cos/delete/idu")
    public Long deleteByIdu(@RequestParam Long idu){
        return cosRepository.deleteByIdu(idu);
    }


    @RequestMapping("/cos/list")
    public String CosList(Model model){
        return listByPage(model,1,"id","asc");
    }

    @GetMapping("/cos/page/{pageNumber}")
    public String listByPage(
            Model model,
            @PathVariable("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection

    )
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Client client=new Client();
        client=clientRepository.findByEmail(currentPrincipalName);


        double total=0.0;
        Page<Cos> page= cosService.findAll(currentPage,sortField,sortDirection);
       long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
       // List<Cos> cosP=page.getContent();
       List<Cos> cosP=cosService.findByIdu(client.getId());
        //List<Cos> cosP=cosService.findAll();
        for (int i = 0; i < cosP.size(); i++) {
            total=total + cosP.get(i).getPret();
        }



        model.addAttribute("currentPage",currentPage);
        model.addAttribute("cosP",cosP);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("total",total);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("comanda", new Comanda());
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "cos-cump";
    }


    @RequestMapping("/asd/{id}")
    public String saveOrUpdate(@ModelAttribute Cos cos,@PathVariable String id)
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();


        Client client=new Client();
        client=clientRepository.findByEmail(currentPrincipalName);


        Produs produs=new Produs();
        produs=produsService.findById(Long.valueOf(id));
        Cos toAdd=new Cos();
        toAdd.setId(cos.getId());
        toAdd.setAroma(produs.getAroma());
        toAdd.setPret(produs.getPret());
        toAdd.setGramaj(produs.getGramaj());
        toAdd.setDenumire(produs.getDenumire());
        toAdd.setImage(produs.getImage());
        toAdd.setIdu(client.getId());
        //toAdd.setProdusC(produs);
       toAdd.setProdusC(produs);




        Cos savedCos=cosService.save(toAdd);
        return  "redirect:/cos/list";
    }



    @RequestMapping("/cos/new")
    public String newProductToCos(Model model) {

        model.addAttribute("cos", new Cos());
        return "cos-cump";
    }

    @PostMapping("/cos")
    public String saveOrUpdate(@Valid @ModelAttribute Cos cos, BindingResult bindingResult)
    {

        if (bindingResult.hasErrors()){
            return "cos-cump";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();


        Client client=new Client();
        client=clientRepository.findByEmail(currentPrincipalName);


        Produs produs=new Produs();
        produs=produsService.findById(1L);
        Cos toAdd=new Cos();
        toAdd.setId(cos.getId());
        toAdd.setAroma(produs.getAroma());
        toAdd.setPret(produs.getPret());
        toAdd.setDenumire(produs.getDenumire());
        toAdd.setImage(produs.getImage());
        toAdd.setIdu(client.getId());

        Cos savedCos=cosService.save(toAdd);
        return  "redirect:/cos/list";
    }


    @RequestMapping("/cos/delete/{id}")
    public String deleteById(@PathVariable String id){
        cosService.deleteById(Long.valueOf(id));


        return "redirect:/cos/list";
    }



}
