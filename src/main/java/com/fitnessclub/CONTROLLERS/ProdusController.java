package com.fitnessclub.CONTROLLERS;


import com.fitnessclub.DOMAIN.Angajat;
import com.fitnessclub.DOMAIN.Detalii_comanda;
import com.fitnessclub.DOMAIN.Mesaj;
import com.fitnessclub.DOMAIN.Produs;
import com.fitnessclub.SERVICES.ImageService;
import com.fitnessclub.SERVICES.MesajService;
import com.fitnessclub.SERVICES.ProdusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProdusController {
    @Autowired
    ProdusService produsService;

    @Autowired
    ImageService imageService;
    @RequestMapping("/produs/list")
    public String produsList(Model model){
        return listByPage(model,1,"id","asc");
    }


    @RequestMapping("/produse/list")
    public String produseList(Model model){
        return listByPage2(model,1,"id","asc");
    }


    @GetMapping("/produs/page/{pageNumber}")
    public String listByPage(
            Model model,
            @PathVariable("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Produs> page= produsService.findAll(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Produs> produsc=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("produsc",produsc);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "gestionare-produse";
    }

    @GetMapping("/produse/page/{pageNumber}")
    public String listByPage2(
            Model model,
            @PathVariable("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Produs> page= produsService.findAll(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Produs> produsc=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("produsc",produsc);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "produse";
    }


    @RequestMapping("/produs/new")
    public String newProdus(Model model) {

        listByPage(model,1,"id","asc");
        model.addAttribute("produs", new Produs());
        return "gestionare-produse";
    }

    @PostMapping("/produs")
    public String saveOrUpdate(@Valid @ModelAttribute Produs produs, BindingResult bindingResult,Model model,@RequestParam("imagefile") MultipartFile file)
    {

        if (bindingResult.hasErrors()){
            listByPage(model,1,"id","asc");
            return "gestionare-produse";

        }

        Produs savedProdus=produsService.save(produs);
        imageService.saveImageFile(Long.valueOf(savedProdus.getId()), file);
        return  "redirect:new";
    }


    @RequestMapping("/produs/delete/{id}")
    public String deleteById(@PathVariable String id){
        produsService.deleteById(Long.valueOf(id));
        return "redirect:/produs/new";
    }



    @RequestMapping("/produs/update/{id}")
    public ModelAndView updateProdusById(@PathVariable String id,Model model){
        ModelAndView modelAndView = new ModelAndView("gestionare-produse");
        Produs produs = produsService.findById(Long.valueOf(id));
        listByPage(model,1,"id","asc");
        modelAndView.addObject("produs",produs);
        return modelAndView;
    }


    @RequestMapping("/produse/comanda/{id}")
    public String ShowByIdCom(@PathVariable String id){
      ///cautam detalii-comanda dupa comanda_id care reprezinta id
        //Creare lista de produse
        return "redirect:/produs/new";
    }


}
