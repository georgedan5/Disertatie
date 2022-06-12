package com.fitnessclub.CONTROLLERS;


import com.fitnessclub.DOMAIN.Detalii_comanda;
import com.fitnessclub.SERVICES.DetaliiComandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DetaliiComandaController {

    @Autowired
    DetaliiComandaService detaliiComandaService;

    @GetMapping("/comanda/detalii/{id}")
    public String listByPage2( Model model,@PathVariable String id){
        List<Detalii_comanda> dc =detaliiComandaService.findByComanda_Id(Long.valueOf(id));
        model.addAttribute("dc", dc);
        return "detalii_comanda";
    }
}
