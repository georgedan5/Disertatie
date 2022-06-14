package com.fitnessclub.CONTROLLERS;

import com.fitnessclub.DOMAIN.Angajat;
import com.fitnessclub.DOMAIN.Client;
import com.fitnessclub.DOMAIN.Mesaj;
import com.fitnessclub.REPOSITORIES.ClientRepository;
import com.fitnessclub.SERVICES.ClientService;
import com.fitnessclub.SERVICES.MesajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MesajController {
    @Autowired
    MesajService mesajService;
    @Autowired
    ClientRepository clientRepository;

    @RequestMapping("/mesaj/list")
    public String mesajList(Model model){
        return listByPage(model,1,"id","asc");
    }

    @GetMapping("/gestionare-mesaje/page/{pageNumber}")
    public String listByPage(
            Model model,
            @PathVariable("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Mesaj> page= mesajService.findAll(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Mesaj> mesajm=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("mesajm",mesajm);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "gestionare-mesaje";
    }



    @RequestMapping("/mesaj/new")
    public String newMesaj(Model model) {

        model.addAttribute("mesaj", new Mesaj());
        return "contact";
    }

    @PostMapping("/mesaj")
    public String saveOrUpdate(@Valid @ModelAttribute Mesaj mesaj, BindingResult bindingResult)
    {

        if (bindingResult.hasErrors()){
            return "contact";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Client client=new Client();
        client=clientRepository.findByEmail(currentPrincipalName);
        long idc=0;
        idc=client.getId();
        mesaj.setClientm(client);
        Mesaj savedMesaj=mesajService.save(mesaj);
        return  "redirect:new";
    }


    @RequestMapping("/mesaj/delete/{id}")
    public String deleteById(@PathVariable String id){
        mesajService.deleteById(Long.valueOf(id));
        return "redirect:/mesaj/list";
    }
}
