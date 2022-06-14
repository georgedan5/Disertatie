package com.fitnessclub;

import com.fitnessclub.DOMAIN.Mesaj;
import com.fitnessclub.REPOSITORIES.MesajRepository;
import com.fitnessclub.SERVICES.MesajSericeImpl;
import com.fitnessclub.SERVICES.MesajService;
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

public class MesajServiceTest {

    MesajService mesajService;

    @Mock
    MesajRepository mesajRepository;

    @Rule
    public MockitoRule rule= MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception{
        mesajService=new MesajSericeImpl(mesajRepository);
    }


    @Test
    @DisplayName("Find all!")
    public void findAll()
    {
        List<Mesaj> mesajs=new ArrayList<>();

        Mesaj mesaj=new Mesaj();
        mesaj.setId(1l);
        mesaj.setNume("Ion");
        mesaj.setEmail("ion@yahoo.com");
        mesaj.setMsg("Salutari");


        mesajs.add(mesaj);

        when(mesajRepository.findAll()).thenReturn(mesajs);
        List<Mesaj> mesajList=mesajService.findAll();
        assertEquals(mesajList.size(),1);
        verify(mesajRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Find by id-HF")
    public void findById(){

        Mesaj mesaj=new Mesaj();
        mesaj.setId(1l);
        mesaj.setNume("Ion");
        mesaj.setEmail("ion@yahoo.com");
        mesaj.setMsg("Salutari");


        when(mesajRepository.findById(mesaj.getId())).thenReturn(Optional.of(mesaj));

        Optional<Mesaj> result= Optional.ofNullable(mesajService.findById(mesaj.getId()));

        assertEquals(result.isPresent(),true);

    }

    @Test
    @DisplayName("Find by id-ERR")
    public void findByIdERR(){
        Mesaj mesaj=new Mesaj();
        mesaj.setId(1l);
        mesaj.setNume("Ion");
        mesaj.setEmail("ion@yahoo.com");
        mesaj.setMsg("Salutari");
        RuntimeException result=assertThrows(RuntimeException.class,
                ()->mesajService.findById(mesaj.getId()));

        assertEquals("Mesajul nu exista!",result.getMessage());


    }


    @Test
    @DisplayName("Create")
    public void newCreate(){
        Mesaj mesaj=new Mesaj();
        mesaj.setId(1l);
        mesaj.setNume("Ion");
        mesaj.setEmail("ion@yahoo.com");
        mesaj.setMsg("Salutari");

        when(mesajRepository.save(mesaj)).thenReturn(mesaj);
        Mesaj result=mesajService.save(mesaj);

        assertEquals(mesaj.getId(),result.getId());
        assertEquals(mesaj.getNume(),result.getNume());
        assertEquals(mesaj.getEmail(),result.getEmail());
        assertEquals(mesaj.getMsg(),result.getMsg());




        verify(mesajRepository,times(1)).save(mesaj);
    }

    @Test
    @DisplayName("Delete ab by id")
    public void deleteById(){
        Mesaj mesaj=new Mesaj();
        mesaj.setId(1l);
        mesaj.setNume("Ion");
        mesaj.setEmail("ion@yahoo.com");
        mesaj.setMsg("Salutari");

        when(mesajRepository.findById(mesaj.getId())).thenReturn(Optional.of(mesaj));
        mesajService.deleteById(mesaj.getId());

        verify(mesajRepository).deleteById(mesaj.getId());

    }


    @Test
    @DisplayName("Delete ab by id ERR")
    public void deleteByIdErr(){
        Mesaj mesaj=new Mesaj();
        mesaj.setId(1l);
        mesaj.setNume("Ion");
        mesaj.setEmail("ion@yahoo.com");
        mesaj.setMsg("Salutari");

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->mesajService.findById(mesaj.getId()));

        assertEquals("Mesajul nu exista!",result.getMessage());

    }

}
