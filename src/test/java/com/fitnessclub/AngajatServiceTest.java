package com.fitnessclub;

import com.fitnessclub.DOMAIN.Angajat;
import com.fitnessclub.REPOSITORIES.AngajatRepository;
import com.fitnessclub.SERVICES.AngajatService;
import com.fitnessclub.SERVICES.AngajatServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class AngajatServiceTest {

    AngajatService angajatService;

    @Mock
    AngajatRepository angajatRepository;

    @Rule
    public MockitoRule rule= MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception{
        angajatService=new AngajatServiceImpl(angajatRepository);
    }


    @Test
    @DisplayName("Find all!")
    public void findAll()
    {
        List<Angajat> ang=new ArrayList<>();

        Angajat angajat=new Angajat();
        angajat.setId(1l);
        angajat.setParola("asd");
        angajat.setDescriere("Loreipsum est ...");
        angajat.setEmail("a@yahoo.com");
        angajat.setFunctie("Antrenor fitness");
        angajat.setGreutate(77.6);
        angajat.setInaltime(176);
        angajat.setNume("Ion");
        angajat.setPrenume("Marcel");
        angajat.setTelefon("09865435789");


        ang.add(angajat);

        when(angajatRepository.findAll()).thenReturn(ang);
        List<Angajat> angajats=angajatService.findAll();
        assertEquals(angajats.size(),1);
        verify(angajatRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Find by id-HF")
    public void findById(){

        Angajat angajat=new Angajat();
        angajat.setId(1l);
        angajat.setParola("asd");
        angajat.setDescriere("Loreipsum est ...");
        angajat.setEmail("a@yahoo.com");
        angajat.setFunctie("Antrenor fitness");
        angajat.setGreutate(77.6);
        angajat.setInaltime(176);
        angajat.setNume("Ion");
        angajat.setPrenume("Marcel");
        angajat.setTelefon("09865435789");


        when(angajatRepository.findById(angajat.getId())).thenReturn(Optional.of(angajat));

        Optional<Angajat> result= Optional.ofNullable(angajatService.findById(angajat.getId()));

        assertEquals(result.isPresent(),true);

    }

    @Test
    @DisplayName("Find by id-ERR")
    public void findByIdERR(){
        Angajat angajat=new Angajat();
        angajat.setId(1l);
        angajat.setParola("asd");
        angajat.setDescriere("Loreipsum est ...");
        angajat.setEmail("a@yahoo.com");
        angajat.setFunctie("Antrenor fitness");
        angajat.setGreutate(77.6);
        angajat.setInaltime(176);
        angajat.setNume("Ion");
        angajat.setPrenume("Marcel");
        angajat.setTelefon("09865435789");
        RuntimeException result=assertThrows(RuntimeException.class,
                ()->angajatService.findById(angajat.getId()));

        assertEquals("Angajatul nu exista!",result.getMessage());


    }


    @Test
    @DisplayName("Create")
    public void newCreate(){
        Angajat angajat=new Angajat();
        angajat.setId(1l);
        angajat.setParola("asd");
        angajat.setDescriere("Loreipsum est ...");
        angajat.setEmail("a@yahoo.com");
        angajat.setFunctie("Antrenor fitness");
        angajat.setGreutate(77.6);
        angajat.setInaltime(176);
        angajat.setNume("Ion");
        angajat.setPrenume("Marcel");
        angajat.setTelefon("09865435789");

        when(angajatRepository.save(angajat)).thenReturn(angajat);
        Angajat result=angajatService.save(angajat);

        assertEquals(angajat.getId(),result.getId());
        assertEquals(angajat.getNume(),result.getNume());
        assertEquals(angajat.getPrenume(),result.getPrenume());
        assertEquals(angajat.getEmail(),result.getEmail());
        assertEquals(angajat.getDescriere(),result.getDescriere());
        assertEquals(angajat.getFunctie(),result.getFunctie());
        assertEquals(angajat.getGreutate(),result.getGreutate());
        assertEquals(angajat.getInaltime(),result.getInaltime());
        assertEquals(angajat.getTelefon(),result.getTelefon());



        verify(angajatRepository,times(1)).save(angajat);
    }

    @Test
    @DisplayName("Delete ab by id")
    public void deleteById(){
        Angajat angajat=new Angajat();
        angajat.setId(1l);
        angajat.setParola("asd");
        angajat.setDescriere("Loreipsum est ...");
        angajat.setEmail("a@yahoo.com");
        angajat.setFunctie("Antrenor fitness");
        angajat.setGreutate(77.6);
        angajat.setInaltime(176);
        angajat.setNume("Ion");
        angajat.setPrenume("Marcel");
        angajat.setTelefon("09865435789");

        when(angajatRepository.findById(angajat.getId())).thenReturn(Optional.of(angajat));
        angajatService.deleteById(angajat.getId());

        verify(angajatRepository).deleteById(angajat.getId());

    }


    @Test
    @DisplayName("Delete ab by id ERR")
    public void deleteByIdErr(){
        Angajat angajat=new Angajat();
        angajat.setId(1l);
        angajat.setParola("asd");
        angajat.setDescriere("Loreipsum est ...");
        angajat.setEmail("a@yahoo.com");
        angajat.setFunctie("Antrenor fitness");
        angajat.setGreutate(77.6);
        angajat.setInaltime(176);
        angajat.setNume("Ion");
        angajat.setPrenume("Marcel");
        angajat.setTelefon("09865435789");

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->angajatService.findById(angajat.getId()));

        assertEquals("Angajatul nu exista!",result.getMessage());

    }

}
