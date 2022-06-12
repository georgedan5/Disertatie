package com.fitnessclub.CONTROLLERS;

import com.fitnessclub.DOMAIN.Angajat;
import com.fitnessclub.DOMAIN.Rol;
import com.fitnessclub.DOMAIN.Utilizator;
import com.fitnessclub.SERVICES.AngajatService;
import com.fitnessclub.SERVICES.ImageService;
import com.fitnessclub.SERVICES.RolService;
import com.fitnessclub.SERVICES.UtilizatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AngajatController {
    @Autowired
    AngajatService angajatService;
    @Autowired
    UtilizatorService utilizatorService;
    @Autowired
    RolService rolService;
    @Autowired
    ImageService imageService;



    @RequestMapping("/angajat/list")
    public String angajatList(Model model){
        return listByPage(model,1,"nume","asc");
    }

    @RequestMapping("/angajat/list2")
    public String angajatList2(Model model){
        return listByPage2(model,1,"nume","asc");
    }

    @GetMapping("/angajat/list/page/{pageNumber}")
    public String listByPage(
            Model model,
            @PathVariable("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Angajat> page= angajatService.findAll(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Angajat> angajatm=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("angajatm",angajatm);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "echipa";
    }
    @GetMapping("/gestionare-echipa/page/{pageNumber}")
    public String listByPage2(
            Model model,
            @PathVariable("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Angajat> page= angajatService.findAll2(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Angajat> angajatm=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("angajatm",angajatm);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "gestionare-echipa";
    }


    @RequestMapping("/angajat/delete/{id}")
    public String deleteById(@PathVariable String id){
        angajatService.deleteById(Long.valueOf(id));
        return "redirect:/angajat/list2";
    }

    @RequestMapping("/angajat/new")
    public String newAngajat(Model model){

        model.addAttribute("angajatm",new Angajat());
        return "adaugareAngajat";
    }



    @PostMapping("/angajat")
    public String saveOrUpdate(@Valid @ModelAttribute("angajatm") Angajat angajat, BindingResult bindingResult,@RequestParam("imagefile") MultipartFile file){
        if(bindingResult.hasErrors()){
            return "adaugareAngajat";
        }
        String encoded = new BCryptPasswordEncoder().encode(angajat.getParola());
        angajat.setParola(encoded);
        Angajat saveAngajat= angajatService.save(angajat);
        imageService.saveImageFile2(Long.valueOf(saveAngajat.getId()), file);
        //add utilizator when a new client has been added
        Utilizator utilizator=new Utilizator();
        utilizator.setId(angajat.getId());
        utilizator.setUsername(angajat.getEmail());
        utilizator.setParola(angajat.getParola());
        long stat=1;
        utilizator.setStatus(stat);

        utilizator.setAng(angajat);
        Utilizator savedutilizator=utilizatorService.save(utilizator);

        //add role
        Rol rol=new Rol();
        rol.setId(angajat.getId());
        rol.setUsername(angajat.getEmail());
        rol.setRol("ROLE_ANG");

        Rol savedrol=rolService.save(rol);
        return "redirect:/angajat/list2";
    }
    //update
    @RequestMapping("/angajat/update/{id}")
    public ModelAndView updateAngajatById(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("adaugareAngajat");
        Angajat angajat = angajatService.findById(Long.valueOf(id));

        angajat.setParola("asd");
        modelAndView.addObject("angajatm",angajat);
        return modelAndView;
    }

}
