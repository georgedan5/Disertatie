package com.fitnessclub.CONTROLLERS;

import com.fitnessclub.EXCEPTIONS.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/showLogInForm")
    public String showLogInForm(){ return "autentificare"; }



    @GetMapping("/login-error")public String loginError(Model model) {
        model.addAttribute("errorMessage","Numele de utilizator sau parola sun incorecte!");

        return "login-error";
    }

    @GetMapping("/access_denied")
    public String accessDenied() {
        throw new AccessDeniedException("Nu aveti drepturi de acces la aceasta pagina.Va rugam sa reveniti la pagina anterioara!");
        // return "access_denied";
    }

    @GetMapping("/gestionareEchipa")
    public String gestionareEchipa(){ return "gestionare-echipa"; }

    @GetMapping("/gestionareMesaje")
    public String gestionareMesaje(){ return "gestionare-mesaje"; }

    @GetMapping("/constientizareCalorii")
    public String constientizareCalorii(){ return "constientizarea-caloriilor"; }

    @GetMapping("/Abonamente")
    public String Abonamente(){ return "abonament"; }

    @GetMapping("/Nutritie")
    public String Nutritie(){ return "nutritie"; }

    @GetMapping("/Antrenamente")
    public String Antrenamente(){ return "plan"; }

    @GetMapping("/Spate")
    public String Spate(){ return "spate"; }
    @GetMapping("/Piept")
    public String Piept(){ return "piept"; }
    @GetMapping("/Biceps")
    public String Biceps(){ return "biceps"; }
    @GetMapping("/Picioare")
    public String Picioare(){ return "picioare"; }
    @GetMapping("/Abdomen")
    public String Abdomen(){ return "abdomen"; }
    @GetMapping("/Triceps")
    public String Triceps(){ return "triceps"; }


    @GetMapping("/ContulMeu")
    public String ContulMeu(){ return "contul-meu"; }

/*
    @GetMapping("/access_denied")
    public String accessDenied() {
        throw new AccessDeniedException("You are not authorized to access this page!");
       // return "access_denied";
    }
*/

    @RequestMapping({"", "/", "/home"})
    public String Home(Model model){
        return "home.html";
    }

    @GetMapping("/inregistrare")
    public String Inregistrare(){ return "register"; }

    @GetMapping("/contact")
    public String Contact(){ return "contact"; }

    @GetMapping("/home")
    public String Home(){ return "home"; }

    @GetMapping("/echipa")
    public String Echipa(){ return "echipa"; }
/*
    public String listByPage(
            Model model,
            @PathVariable("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Product> page= productService.findAll(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Product> products=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("products",products);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "products";

    }

 */
}
