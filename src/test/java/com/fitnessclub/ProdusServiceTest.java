package com.fitnessclub;

import com.fitnessclub.DOMAIN.Produs;
import com.fitnessclub.REPOSITORIES.ProdusRepository;
import com.fitnessclub.SERVICES.ProdusService;
import com.fitnessclub.SERVICES.ProdusServiceImpl;
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

public class ProdusServiceTest {

    ProdusService produsService;

    @Mock
    ProdusRepository produsRepository;

    @Rule
    public MockitoRule rule= MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception{
        produsService=new ProdusServiceImpl(produsRepository);
    }


    @Test
    @DisplayName("Find all!")
    public void findAll()
    {
        List<Produs> produses=new ArrayList<>();

        Produs produs=new Produs();

        produs.setId(1l);
        produs.setDescriere("Produsul este foarte bun");
        produs.setDenumire("Proteina");
        produs.setAroma("Ananas");
        produs.setGramaj(200);
        produs.setPret(55);


        produses.add(produs);

        when(produsRepository.findAll()).thenReturn(produses);
        List<Produs> produses1=produsService.findAll();
        assertEquals(produses.size(),1);
        verify(produsRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Find by id-HF")
    public void findById(){

        Produs produs=new Produs();

        produs.setId(1l);
        produs.setDescriere("Produsul este foarte bun");
        produs.setDenumire("Proteina");
        produs.setAroma("Ananas");
        produs.setGramaj(200);
        produs.setPret(55);


        when(produsRepository.findById(produs.getId())).thenReturn(Optional.of(produs));

        Optional<Produs> result= Optional.ofNullable(produsService.findById(produs.getId()));

        assertEquals(result.isPresent(),true);

    }

    @Test
    @DisplayName("Find by id-ERR")
    public void findByIdERR(){
        Produs produs=new Produs();

        produs.setId(1l);
        produs.setDescriere("Produsul este foarte bun");
        produs.setDenumire("Proteina");
        produs.setAroma("Ananas");
        produs.setGramaj(200);
        produs.setPret(55);

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->produsService.findById(produs.getId()));

        assertEquals("Produsul nu exista!",result.getMessage());


    }


    @Test
    @DisplayName("Create")
    public void newCreate(){
        Produs produs=new Produs();

        produs.setId(1l);
        produs.setDescriere("Produsul este foarte bun");
        produs.setDenumire("Proteina");
        produs.setAroma("Ananas");
        produs.setGramaj(200);
        produs.setPret(55);

        when(produsRepository.save(produs)).thenReturn(produs);
        Produs result=produsService.save(produs);

        assertEquals(produs.getId(),result.getId());
        assertEquals(produs.getDenumire(),result.getDenumire());
        assertEquals(produs.getDescriere(),result.getDescriere());
        assertEquals(produs.getAroma(),result.getAroma());
        assertEquals(produs.getPret(),result.getPret());
        assertEquals(produs.getGramaj(),result.getGramaj());




        verify(produsRepository,times(1)).save(produs);
    }

    @Test
    @DisplayName("Delete ab by id")
    public void deleteById(){
        Produs produs=new Produs();

        produs.setId(1l);
        produs.setDescriere("Produsul este foarte bun");
        produs.setDenumire("Proteina");
        produs.setAroma("Ananas");
        produs.setGramaj(200);
        produs.setPret(55);

        when(produsRepository.findById(produs.getId())).thenReturn(Optional.of(produs));
        produsService.deleteById(produs.getId());

        verify(produsRepository).deleteById(produs.getId());

    }


    @Test
    @DisplayName("Delete ab by id ERR")
    public void deleteByIdErr(){
        Produs produs=new Produs();

        produs.setId(1l);
        produs.setDescriere("Produsul este foarte bun");
        produs.setDenumire("Proteina");
        produs.setAroma("Ananas");
        produs.setGramaj(200);
        produs.setPret(55);

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->produsService.findById(produs.getId()));

        assertEquals("Produsul nu exista!",result.getMessage());

    }

}
