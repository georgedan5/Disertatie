package com.fitnessclub.CONTROLLERS;
import com.fitnessclub.DOMAIN.Angajat;
import com.fitnessclub.DOMAIN.Cos;
import com.fitnessclub.DOMAIN.Produs;
import com.fitnessclub.SERVICES.AngajatService;
import com.fitnessclub.SERVICES.CosService;
import com.fitnessclub.SERVICES.ImageService;
import com.fitnessclub.SERVICES.ProdusService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    private final ProdusService produsService;
    private final CosService cosService;
    private final AngajatService angajatService;


    public ImageController(@Autowired ImageService imageService, @Autowired ProdusService produsService, @Autowired CosService cosService, @Autowired AngajatService angajatService) {
        this.produsService = produsService;
        this.cosService=cosService;
        this.angajatService=angajatService;
    }

    @GetMapping("produs/getimage/{id}")
    public void downloadImage(@PathVariable String id, HttpServletResponse response) throws IOException {
        Produs produs = produsService.findById(Long.valueOf(id));

            if (produs.getImage() != null) {
                byte[] byteArray = new byte[produs.getImage().length];
                int i = 0;
                for (Byte wrappedByte : produs.getImage()) {
                    byteArray[i++] = wrappedByte;
                }
                response.setContentType("image/jpeg");
                InputStream is = new ByteArrayInputStream(byteArray);
                try {
                    IOUtils.copy(is, response.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }


    @GetMapping("cos/getimage/{id}")
    public void downloadImageCos(@PathVariable String id, HttpServletResponse response) throws IOException {
        Cos cos = cosService.findById(Long.valueOf(id));

        if (cos.getImage() != null) {
            byte[] byteArray = new byte[cos.getImage().length];
            int i = 0;
            for (Byte wrappedByte : cos.getImage()) {
                byteArray[i++] = wrappedByte;
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            try {
                IOUtils.copy(is, response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    @GetMapping("angajat/getimage/{id}")
    public void downloadImageAngajat(@PathVariable String id, HttpServletResponse response) throws IOException {
        Angajat angajat = angajatService.findById(Long.valueOf(id));

        if (angajat.getImage() != null) {
            byte[] byteArray = new byte[angajat.getImage().length];
            int i = 0;
            for (Byte wrappedByte : angajat.getImage()) {
                byteArray[i++] = wrappedByte;
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            try {
                IOUtils.copy(is, response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
