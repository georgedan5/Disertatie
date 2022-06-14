package com.fitnessclub;

import com.fitnessclub.DOMAIN.Cos;
import com.fitnessclub.REPOSITORIES.CosRepository;
import com.fitnessclub.SERVICES.CosService;
import com.fitnessclub.SERVICES.CosServiceImpl;
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
public class CosServiceTest {
    CosService cosService;

    @Mock
    CosRepository cosRepository;

    @Rule
    public MockitoRule rule= MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception{
        cosService=new CosServiceImpl(cosRepository);
    }


    @Test
    @DisplayName("Find all!")
    public void findAll()
    {
        List<Cos> cosList=new ArrayList<>();

        Cos cos=new Cos();
        cos.setId(1l);
        cos.setIdu(1l);
        cos.setDenumire("Proteina");
        cos.setGramaj(200);
        cos.setPret(34);
        cos.setAroma("Ananas");



        cosList.add(cos);

        when(cosRepository.findAll()).thenReturn(cosList);
        List<Cos> cosList1=cosService.findAll();
        assertEquals(cosList1.size(),1);
        verify(cosRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Find by id-HF")
    public void findById(){

        Cos cos=new Cos();
        cos.setId(1l);
        cos.setIdu(1l);
        cos.setDenumire("Proteina");
        cos.setGramaj(200);
        cos.setPret(34);
        cos.setAroma("Ananas");


        when(cosRepository.findById(cos.getId())).thenReturn(Optional.of(cos));

        Optional<Cos> result= Optional.ofNullable(cosService.findById(cos.getId()));

        assertEquals(result.isPresent(),true);

    }

    @Test
    @DisplayName("Find by id-ERR")
    public void findByIdERR(){

        Cos cos=new Cos();
        cos.setId(1l);
        cos.setIdu(1l);
        cos.setDenumire("Proteina");
        cos.setGramaj(200);
        cos.setPret(34);
        cos.setAroma("Ananas");

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->cosService.findById(cos.getId()));

        assertEquals("Cosul nu exista!",result.getMessage());


    }


    @Test
    @DisplayName("Create")
    public void newCreate(){
        Cos cos=new Cos();
        cos.setId(1l);
        cos.setIdu(1l);
        cos.setDenumire("Proteina");
        cos.setGramaj(200);
        cos.setPret(34);
        cos.setAroma("Ananas");

        when(cosRepository.save(cos)).thenReturn(cos);
        Cos result=cosService.save(cos);

        assertEquals(cos.getId(),result.getId());
        assertEquals(cos.getIdu(),result.getIdu());
        assertEquals(cos.getDenumire(),result.getDenumire());
        assertEquals(cos.getGramaj(),result.getGramaj());
        assertEquals(cos.getPret(),result.getPret());
        assertEquals(cos.getAroma(),result.getAroma());





        verify(cosRepository,times(1)).save(cos);
    }

    @Test
    @DisplayName("Delete ab by id")
    public void deleteById(){

        Cos cos=new Cos();
        cos.setId(1l);
        cos.setIdu(1l);
        cos.setDenumire("Proteina");
        cos.setGramaj(200);
        cos.setPret(34);
        cos.setAroma("Ananas");

        when(cosRepository.findById(cos.getId())).thenReturn(Optional.of(cos));
        cosService.deleteById(cos.getId());

        verify(cosRepository).deleteById(cos.getId());

    }


    @Test
    @DisplayName("Delete ab by id ERR")
    public void deleteByIdErr(){
        Cos cos=new Cos();
        cos.setId(1l);
        cos.setIdu(1l);
        cos.setDenumire("Proteina");
        cos.setGramaj(200);
        cos.setPret(34);
        cos.setAroma("Ananas");

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->cosService.findById(cos.getId()));

        assertEquals("Cosul nu exista!",result.getMessage());

    }

}
