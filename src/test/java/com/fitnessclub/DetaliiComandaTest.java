package com.fitnessclub;
import com.fitnessclub.DOMAIN.Comanda;
import com.fitnessclub.DOMAIN.Detalii_comanda;
import com.fitnessclub.DOMAIN.Produs;
import com.fitnessclub.REPOSITORIES.DetaliiComandaRepository;
import com.fitnessclub.SERVICES.DetaliiComandaService;
import com.fitnessclub.SERVICES.DetaliiComandaServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class DetaliiComandaTest {

    DetaliiComandaService detaliiComandaService;

    @Mock
    DetaliiComandaRepository detaliiComandaRepository;

    @Rule
    public MockitoRule rule= MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception{
        detaliiComandaService=new DetaliiComandaServiceImpl(detaliiComandaRepository);
    }


    @Test
    @DisplayName("Find all!")
    public void findAll()
    {
        List<Detalii_comanda> detalii_comandas=new ArrayList<>();

        Detalii_comanda detalii_comanda=new Detalii_comanda();
        detalii_comanda.setId(1l);
        Comanda comanda=new Comanda();
        comanda.setId(1l);

        detalii_comanda.setComanda(comanda);

        Produs produs=new Produs();
        produs.setId(1l);

        detalii_comanda.setProdus(produs);




        detalii_comandas.add(detalii_comanda);

        when(detaliiComandaRepository.findAll()).thenReturn(detalii_comandas);
        List<Detalii_comanda> detalii_comandas1=detaliiComandaService.findAll();
        assertEquals(detalii_comandas1.size(),1);
        verify(detaliiComandaRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Find by id-HF")
    public void findById(){

        Detalii_comanda detalii_comanda=new Detalii_comanda();
        detalii_comanda.setId(1l);
        Comanda comanda=new Comanda();
        comanda.setId(1l);

        detalii_comanda.setComanda(comanda);

        Produs produs=new Produs();
        produs.setId(1l);

        detalii_comanda.setProdus(produs);


        when(detaliiComandaRepository.findById(detalii_comanda.getId())).thenReturn(Optional.of(detalii_comanda));

        Optional<Detalii_comanda> result= Optional.ofNullable(detaliiComandaService.findById(detalii_comanda.getId()));

        assertEquals(result.isPresent(),true);

    }

    @Test
    @DisplayName("Find by id-ERR")
    public void findByIdERR(){

        Detalii_comanda detalii_comanda=new Detalii_comanda();
        detalii_comanda.setId(1l);
        Comanda comanda=new Comanda();
        comanda.setId(1l);

        detalii_comanda.setComanda(comanda);

        Produs produs=new Produs();
        produs.setId(1l);

        detalii_comanda.setProdus(produs);
        RuntimeException result=assertThrows(RuntimeException.class,
                ()->detaliiComandaService.findById(detalii_comanda.getId()));

        assertEquals("Comanda nu exista!",result.getMessage());


    }


    @Test
    @DisplayName("Create")
    public void newCreate(){

        Detalii_comanda detalii_comanda=new Detalii_comanda();
        detalii_comanda.setId(1l);
        Comanda comanda=new Comanda();
        comanda.setId(1l);

        detalii_comanda.setComanda(comanda);

        Produs produs=new Produs();
        produs.setId(1l);

        detalii_comanda.setProdus(produs);

        when(detaliiComandaRepository.save(detalii_comanda)).thenReturn(detalii_comanda);
        Detalii_comanda result=detaliiComandaService.save(detalii_comanda);

        assertEquals(detalii_comanda.getId(),result.getId());
        assertEquals(detalii_comanda.getComanda(),result.getComanda());
        assertEquals(detalii_comanda.getProdus(),result.getProdus());




        verify(detaliiComandaRepository,times(1)).save(detalii_comanda);
    }

    @Test
    @DisplayName("Delete ab by id")
    public void deleteById(){
        Detalii_comanda detalii_comanda=new Detalii_comanda();
        detalii_comanda.setId(1l);
        Comanda comanda=new Comanda();
        comanda.setId(1l);

        detalii_comanda.setComanda(comanda);

        Produs produs=new Produs();
        produs.setId(1l);

        detalii_comanda.setProdus(produs);

        when(detaliiComandaRepository.findById(detalii_comanda.getId())).thenReturn(Optional.of(detalii_comanda));
        detaliiComandaService.deleteById(detalii_comanda.getId());

        verify(detaliiComandaRepository).deleteById(detalii_comanda.getId());

    }


    @Test
    @DisplayName("Delete ab by id ERR")
    public void deleteByIdErr(){
        Detalii_comanda detalii_comanda=new Detalii_comanda();
        detalii_comanda.setId(1l);
        Comanda comanda=new Comanda();
        comanda.setId(1l);

        detalii_comanda.setComanda(comanda);

        Produs produs=new Produs();
        produs.setId(1l);

        detalii_comanda.setProdus(produs);

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->detaliiComandaService.findById(detalii_comanda.getId()));

        assertEquals("Comanda nu exista!",result.getMessage());

    }

}
