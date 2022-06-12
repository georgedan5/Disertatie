package com.fitnessclub.CONTROLLERS;

import com.fitnessclub.DOMAIN.*;
import com.fitnessclub.POJO.Abonament_efectuat_POJO;
import com.fitnessclub.REPOSITORIES.ClientRepository;
import com.fitnessclub.SERVICES.AbonamentEfectuatService;
import com.fitnessclub.SERVICES.AbonamentService;
import com.fitnessclub.SERVICES.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Controller
public class AbonamentEfectuatController {

    @Autowired
    AbonamentEfectuatService abonamentEfectuatService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private EmailSenderService senderService;

    @Autowired
    private AbonamentService abonamentService;

    @Autowired
    public AbonamentEfectuatController (AbonamentEfectuatService abonamentEfectuatService,ClientRepository clientRepository){
        this.abonamentEfectuatService=abonamentEfectuatService;
        this.clientRepository=clientRepository;

    }

    @RequestMapping("/abonament_efectuat/list")
    public String AbonamentEfectuatList(Model model){
        return listByPage(model,1,"id","asc");
    }

    @GetMapping("/gestionare-abonamente/page/{pageNumber}")
    public String listByPage(
            Model model,
            @PathVariable("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Abonament_efectuat> page= abonamentEfectuatService.findAll(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Abonament_efectuat> abef=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("abef",abef);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "gestionare-abonamente";
    }
    @RequestMapping("/abonamentEfectuat/delete/{id}")
    public String deleteById(@PathVariable String id){
        abonamentEfectuatService.deleteById(Long.valueOf(id));
        return "redirect:/abonament_efectuat/list";
    }


    @RequestMapping("/abonament/new")
    public String newAboanemnt( Model model) {

        model.addAttribute("abonamentF", new Abonament_efectuat());
        return "abonament";
    }

    @PostMapping("/abonament")
    public String saveOrUpdate(@Valid @ModelAttribute Abonament_efectuat_POJO abonament_efectuat, BindingResult bindingResult) {

        Client client = new Client();
        client = clientRepository.findByEmail(abonament_efectuat.getEmailc());

        if (bindingResult.hasErrors()) {
            return "abonament";

        }
        long nr_zile = 0;
        long ok = abonament_efectuat.getIda();

        if (ok == 1 || ok == 4 || ok == 7) {
            nr_zile = 30;
        }

        if (ok == 2 || ok == 5 || ok == 8) {
            nr_zile = 180;
        }

        if (ok == 3 || ok == 6 || ok == 9) {
            nr_zile = 365;
        }

        Abonament_efectuat ab = new Abonament_efectuat();
        ab.setId(abonament_efectuat.getIda());
        //Client cl=new Client();
        //cl.setId(1L);
        ab.setId(abonament_efectuat.getId());
        // abonament_efectuat.setAbonament(ab);


        Abonament abs = new Abonament();
        abs.setId(abonament_efectuat.getIda());
        ab.setAbonament(abs);
        ab.setClient(client);

        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate date = abonament_efectuat.getData_inceput().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate expdate = date.plusDays(nr_zile);
        Date date2 = Date.from(expdate.atStartOfDay(defaultZoneId).toInstant());
        ab.setData_expirare(date2);


        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

        ///----validare daca exista deja un abonament pentru acel utilizator



        ////trimite email

        List<Abonament_efectuat> listab=abonamentEfectuatService.findByclient_id(client.getId());

        if(listab.size()>0) {
            return "duplicat_abonament";
        }
        else {


            Abonament_efectuat savedAboanemnt = abonamentEfectuatService.save(ab);
            ////trimite email
          /*
            senderService.sendSimpleEmail("george_dan3@yahoo.com",
                    "This is email body",
                    "This is email subject");

           */

            Abonament qq=abonamentService.findById(abonament_efectuat.getIda());

            senderService.sendSimpleEmail(client.getEmail(),
                    "Abonament Fitness Club",
                    "Buna ziua," + "\r\n\r\n" +
                            "Va multumim pentru alegerea facuta, mai jos gasiti detaliile abonamentului dumneavoastra."  + "\r\n" +
                            "Ati achizitionat abonamentul " +qq.getDenumire()+" " +qq.getDescriere() + " la pretul de "+qq.getPret()+" lei."  +"\r\n" +
                            "Abonament dumneavoastra este valabil pana la data de:"+ dateFormatter.format(ab.getData_expirare())  + "\r\n\r\n" + "Cu drag,"+ "\r\n" + "Echipa Fitness Club!");
            return "redirect:/abonament/new";
        }


    }



}
