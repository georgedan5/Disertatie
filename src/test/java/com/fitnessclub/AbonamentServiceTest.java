package com.fitnessclub;

import com.fitnessclub.DOMAIN.Abonament;
import com.fitnessclub.DOMAIN.Abonament_efectuat;
import com.fitnessclub.DOMAIN.Client;
import com.fitnessclub.REPOSITORIES.AbonamentRepository;
import com.fitnessclub.SERVICES.AbonamentEfectuatServiceImpl;
import com.fitnessclub.SERVICES.AbonamentService;
import com.fitnessclub.SERVICES.AbonamentServiceImpl;
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

public class AbonamentServiceTest {

    AbonamentService abonamentService;

    @Mock
    AbonamentRepository abonamentRepository;

    @Rule
    public MockitoRule rule= MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception{
        abonamentService=new AbonamentServiceImpl(abonamentRepository);
    }


    @Test
    @DisplayName("Find all abonament!")
    public void findAbs()
    {
        List<Abonament> abs=new ArrayList<>();

        Abonament ab=new Abonament();
        ab.setId(1l);
        ab.setDenumire("BASIC1");
        ab.setDescriere("Valabil pentru o luna");
        ab.setPret(123);


        abs.add(ab);

        when(abonamentRepository.findAll()).thenReturn(abs);
        List<Abonament> abs_list=abonamentService.findAll();
        assertEquals(abs_list.size(),1);
        verify(abonamentRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Find the abonament by id-HF")
    public void findById(){
        Abonament ab=new Abonament();
        ab.setId(1l);
        ab.setDenumire("BASIC1");
        ab.setDescriere("Valabil pentru o luna");
        ab.setPret(123);


        when(abonamentRepository.findById(ab.getId())).thenReturn(Optional.of(ab));

        Optional<Abonament> result= Optional.ofNullable(abonamentService.findById(ab.getId()));

        assertEquals(result.isPresent(),true);

    }

    @Test
    @DisplayName("Find the abonament by id-ERR")
    public void findByIdERR(){
        Abonament ab=new Abonament();
        ab.setId(1l);
        ab.setDenumire("BASIC1");
        ab.setDescriere("Valabil pentru o luna");
        ab.setPret(123);

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->abonamentService.findById(ab.getId()));

        assertEquals("Abonamentul nu exista!",result.getMessage());


    }


    @Test
    @DisplayName("Create a new ab.")
    public void newAb(){
        Abonament ab=new Abonament();
        ab.setId(1l);
        ab.setDenumire("BASIC1");
        ab.setDescriere("Valabil pentru o luna");
        ab.setPret(123);

        when(abonamentRepository.save(ab)).thenReturn(ab);
        Abonament result=abonamentService.save(ab);

        assertEquals(ab.getId(),result.getId());
        assertEquals(ab.getDenumire(),result.getDenumire());
        assertEquals(ab.getDescriere(),result.getDescriere());
        assertEquals(ab.getPret(),result.getPret());


        verify(abonamentRepository,times(1)).save(ab);
    }

    @Test
    @DisplayName("Delete ab by id")
    public void deleteById(){
        Abonament ab=new Abonament();
        ab.setId(1l);
        ab.setDenumire("BASIC1");
        ab.setDescriere("Valabil pentru o luna");
        ab.setPret(123);

        when(abonamentRepository.findById(ab.getId())).thenReturn(Optional.of(ab));
        abonamentService.deleteById(ab.getId());

        verify(abonamentRepository).deleteById(ab.getId());

    }


    @Test
    @DisplayName("Delete ab by id ERR")
    public void deleteByIdErr(){
        Abonament ab=new Abonament();
        ab.setId(1l);
        ab.setDenumire("BASIC1");
        ab.setDescriere("Valabil pentru o luna");
        ab.setPret(123);

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->abonamentService.findById(ab.getId()));

        assertEquals("Abonamentul nu exista!",result.getMessage());

    }








}
