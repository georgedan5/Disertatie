package com.fitnessclub.SERVICES;

import com.fitnessclub.DOMAIN.Angajat;
import com.fitnessclub.DOMAIN.Produs;
import java.io.IOException;

import com.fitnessclub.REPOSITORIES.AngajatRepository;
import com.fitnessclub.REPOSITORIES.ProdusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService{

    ProdusRepository produsRepository;
    AngajatRepository angajatRepository;

    @Autowired
    public ImageServiceImpl(ProdusRepository produsRepository,AngajatRepository angajatRepository) {

        this.produsRepository = produsRepository;
        this.angajatRepository=angajatRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long productId, MultipartFile file) {
        try {
            Produs produs = produsRepository.findById(productId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0; for (byte b : file.getBytes()){
                byteObjects[i++] = b; }


            produs.setImage(byteObjects);

            produsRepository.save(produs); }
        catch (IOException e) {
        }
    }

    @Override
    @Transactional
    public void saveImageFile2(Long angajatId, MultipartFile file) {
        try {
            Angajat angajat = angajatRepository.findById(angajatId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0; for (byte b : file.getBytes()){
                byteObjects[i++] = b; }


            angajat.setImage(byteObjects);

            angajatRepository.save(angajat); }
        catch (IOException e) {
        }
    }
}
