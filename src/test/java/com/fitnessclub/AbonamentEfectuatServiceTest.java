package com.fitnessclub;

import com.fitnessclub.DOMAIN.Abonament;
import com.fitnessclub.DOMAIN.Abonament_efectuat;
import com.fitnessclub.DOMAIN.Client;
import com.fitnessclub.REPOSITORIES.AbonamentEfectuatRepository;
import com.fitnessclub.SERVICES.AbonamentEfectuatService;
import com.fitnessclub.SERVICES.AbonamentEfectuatServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class AbonamentEfectuatServiceTest {

    AbonamentEfectuatService abonamentEfectuatService;

    @Mock
    AbonamentEfectuatRepository abonamentEfectuatRepository;

    @Rule
    public MockitoRule rule= MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception{
        abonamentEfectuatService=new AbonamentEfectuatServiceImpl(abonamentEfectuatRepository);
    }

    @Test
    @DisplayName("Find all abonament_efectuat!")
    public void findAbs()
    {
        List<Abonament_efectuat> abs=new ArrayList<>();
        Abonament_efectuat abonament_efectuat=new Abonament_efectuat();
        abonament_efectuat.setId(1L);
        Abonament ab=new Abonament();
        ab.setId(1l);
        abonament_efectuat.setAbonament(ab);

       // abonament_efectuat.setData_inceput(d);
        //abonament_efectuat.setData_expirare(d);
        Client cli=new Client();
        cli.setId(1l);
        abonament_efectuat.setClient(cli);

        abs.add(abonament_efectuat);

        when(abonamentEfectuatRepository.findAll()).thenReturn(abs);
        List<Abonament_efectuat> abs_list=abonamentEfectuatService.findAll();
        assertEquals(abs_list.size(),1);
        verify(abonamentEfectuatRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Find the abonament by id-HF")
    public void findById(){
        Abonament_efectuat abonament_efectuat=new Abonament_efectuat();
        abonament_efectuat.setId(1L);
        Abonament ab=new Abonament();
        ab.setId(1l);
        abonament_efectuat.setAbonament(ab);

        // abonament_efectuat.setData_inceput(d);
        //abonament_efectuat.setData_expirare(d);
        Client cli=new Client();
        cli.setId(1l);
        abonament_efectuat.setClient(cli);


        when(abonamentEfectuatRepository.findById(abonament_efectuat.getId())).thenReturn(Optional.of(abonament_efectuat));

        Optional<Abonament_efectuat> result= Optional.ofNullable(abonamentEfectuatService.findById(abonament_efectuat.getId()));

        assertEquals(result.isPresent(),true);

    }

    @Test
    @DisplayName("Find the abonament by id-ERR")
    public void findByIdERR(){
        Abonament_efectuat abonament_efectuat=new Abonament_efectuat();
        abonament_efectuat.setId(1L);
        Abonament ab=new Abonament();
        ab.setId(1l);
        abonament_efectuat.setAbonament(ab);

        // abonament_efectuat.setData_inceput(d);
        //abonament_efectuat.setData_expirare(d);
        Client cli=new Client();
        cli.setId(1l);
        abonament_efectuat.setClient(cli);

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->abonamentEfectuatService.findById(abonament_efectuat.getId()));

        assertEquals("Abonamentul nu exista!",result.getMessage());


    }


    @Test
    @DisplayName("Create a new ab.")
    public void newAb(){
        Abonament_efectuat abonament_efectuat=new Abonament_efectuat();
        abonament_efectuat.setId(1L);
        Abonament ab=new Abonament();
        ab.setId(1l);
        abonament_efectuat.setAbonament(ab);

        // abonament_efectuat.setData_inceput(d);
        //abonament_efectuat.setData_expirare(d);
        Client cli=new Client();
        cli.setId(1l);
        abonament_efectuat.setClient(cli);

        when(abonamentEfectuatRepository.save(abonament_efectuat)).thenReturn(abonament_efectuat);
        Abonament_efectuat result=abonamentEfectuatService.save(abonament_efectuat);

        assertEquals(abonament_efectuat.getId(),result.getId());
        assertEquals(abonament_efectuat.getAbonament(),result.getAbonament());
        assertEquals(abonament_efectuat.getClient(),result.getClient());

        verify(abonamentEfectuatRepository,times(1)).save(abonament_efectuat);
    }

    @Test
    @DisplayName("Delete ab by id")
    public void deleteById(){
        Abonament_efectuat abonament_efectuat=new Abonament_efectuat();
        abonament_efectuat.setId(1L);
        Abonament ab=new Abonament();
        ab.setId(1l);
        abonament_efectuat.setAbonament(ab);

        // abonament_efectuat.setData_inceput(d);
        //abonament_efectuat.setData_expirare(d);
        Client cli=new Client();
        cli.setId(1l);
        abonament_efectuat.setClient(cli);

        when(abonamentEfectuatRepository.findById(abonament_efectuat.getId())).thenReturn(Optional.of(abonament_efectuat));
        abonamentEfectuatService.deleteById(abonament_efectuat.getId());

        verify(abonamentEfectuatRepository).deleteById(abonament_efectuat.getId());

    }


    @Test
    @DisplayName("Delete ab by id ERR")
    public void deleteByIdErr(){
        Abonament_efectuat abonament_efectuat=new Abonament_efectuat();
        abonament_efectuat.setId(1L);
        Abonament ab=new Abonament();
        ab.setId(1l);
        abonament_efectuat.setAbonament(ab);

        // abonament_efectuat.setData_inceput(d);
        //abonament_efectuat.setData_expirare(d);
        Client cli=new Client();
        cli.setId(1l);
        abonament_efectuat.setClient(cli);

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->abonamentEfectuatService.findById(abonament_efectuat.getId()));

        assertEquals("Abonamentul nu exista!",result.getMessage());

    }




}
