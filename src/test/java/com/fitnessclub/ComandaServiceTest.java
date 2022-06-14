package com.fitnessclub;

import com.fitnessclub.DOMAIN.Comanda;
import com.fitnessclub.REPOSITORIES.ComandaRepository;
import com.fitnessclub.SERVICES.ComandaService;
import com.fitnessclub.SERVICES.ComandaServiceImpl;
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

public class ComandaServiceTest {
    ComandaService comandaService;

    @Mock
    ComandaRepository comandaRepository;

    @Rule
    public MockitoRule rule= MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception{
        comandaService=new ComandaServiceImpl(comandaRepository);
    }


    @Test
    @DisplayName("Find all!")
    public void findAll()
    {
        List<Comanda> comandaList=new ArrayList<>();

        Comanda comanda=new Comanda();
        comanda.setId(1l);
        comanda.setId_client(1l);
        comanda.setObservatie("-");
        comanda.setAdresa("RP");
        comanda.setStatus("Inregistrata");
        comanda.setTelefon("098652323");
        comanda.setNume("Ion");
        comanda.setPrenume("Mihai");
        comanda.setEmail("a@yahoo.com");
        comanda.setTotalC(8866);


        comandaList.add(comanda);

        when(comandaRepository.findAll()).thenReturn(comandaList);
        List<Comanda> comandas=comandaService.findAll();
        assertEquals(comandas.size(),1);
        verify(comandaRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Find by id-HF")
    public void findById(){

        Comanda comanda=new Comanda();
        comanda.setId(1l);
        comanda.setId_client(1l);
        comanda.setObservatie("-");
        comanda.setAdresa("RP");
        comanda.setStatus("Inregistrata");
        comanda.setTelefon("098652323");
        comanda.setNume("Ion");
        comanda.setPrenume("Mihai");
        comanda.setEmail("a@yahoo.com");
        comanda.setTotalC(8866);


        when(comandaRepository.findById(comanda.getId())).thenReturn(Optional.of(comanda));

        Optional<Comanda> result= Optional.ofNullable(comandaService.findById(comanda.getId()));

        assertEquals(result.isPresent(),true);

    }

    @Test
    @DisplayName("Find by id-ERR")
    public void findByIdERR(){

        Comanda comanda=new Comanda();
        comanda.setId(1l);
        comanda.setId_client(1l);
        comanda.setObservatie("-");
        comanda.setAdresa("RP");
        comanda.setStatus("Inregistrata");
        comanda.setTelefon("098652323");
        comanda.setNume("Ion");
        comanda.setPrenume("Mihai");
        comanda.setEmail("a@yahoo.com");
        comanda.setTotalC(8866);

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->comandaService.findById(comanda.getId()));

        assertEquals("Comanda nu exista!",result.getMessage());


    }


    @Test
    @DisplayName("Create")
    public void newCreate(){

        Comanda comanda=new Comanda();
        comanda.setId(1l);
        comanda.setId_client(1l);
        comanda.setObservatie("-");
        comanda.setAdresa("RP");
        comanda.setStatus("Inregistrata");
        comanda.setTelefon("098652323");
        comanda.setNume("Ion");
        comanda.setPrenume("Mihai");
        comanda.setEmail("a@yahoo.com");
        comanda.setTotalC(8866);

        when(comandaRepository.save(comanda)).thenReturn(comanda);
        Comanda result=comandaService.save(comanda);

        assertEquals(comanda.getId(),result.getId());
        assertEquals(comanda.getNume(),result.getNume());
        assertEquals(comanda.getPrenume(),result.getPrenume());
        assertEquals(comanda.getEmail(),result.getEmail());
        assertEquals(comanda.getTelefon(),result.getTelefon());
        assertEquals(comanda.getId_client(),result.getId_client());
        assertEquals(comanda.getAdresa(),result.getAdresa());
        assertEquals(comanda.getObservatie(),result.getObservatie());



        verify(comandaRepository,times(1)).save(comanda);
    }

    @Test
    @DisplayName("Delete ab by id")
    public void deleteById(){

        Comanda comanda=new Comanda();
        comanda.setId(1l);
        comanda.setId_client(1l);
        comanda.setObservatie("-");
        comanda.setAdresa("RP");
        comanda.setStatus("Inregistrata");
        comanda.setTelefon("098652323");
        comanda.setNume("Ion");
        comanda.setPrenume("Mihai");
        comanda.setEmail("a@yahoo.com");
        comanda.setTotalC(8866);

        when(comandaRepository.findById(comanda.getId())).thenReturn(Optional.of(comanda));
        comandaService.deleteById(comanda.getId());

        verify(comandaRepository).deleteById(comanda.getId());

    }


    @Test
    @DisplayName("Delete ab by id ERR")
    public void deleteByIdErr(){

        Comanda comanda=new Comanda();
        comanda.setId(1l);
        comanda.setId_client(1l);
        comanda.setObservatie("-");
        comanda.setAdresa("RP");
        comanda.setStatus("Inregistrata");
        comanda.setTelefon("098652323");
        comanda.setNume("Ion");
        comanda.setPrenume("Mihai");
        comanda.setEmail("a@yahoo.com");
        comanda.setTotalC(8866);

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->comandaService.findById(comanda.getId()));

        assertEquals("Comanda nu exista!",result.getMessage());

    }

}
