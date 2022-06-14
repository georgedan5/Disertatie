package com.fitnessclub;

import com.fitnessclub.DOMAIN.Utilizator;
import com.fitnessclub.REPOSITORIES.UtilizatorRepository;
import com.fitnessclub.SERVICES.UtilizatorService;
import com.fitnessclub.SERVICES.UtilizatorServiceImpl;
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

public class UtilizatorServiceTest {

    UtilizatorService utilizatorService;

    @Mock
    UtilizatorRepository utilizatorRepository;

    @Rule
    public MockitoRule rule= MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception{
        utilizatorService=new UtilizatorServiceImpl(utilizatorRepository);
    }


    @Test
    @DisplayName("Find all!")
    public void findAll()
    {
        List<Utilizator> utilizators=new ArrayList<>();

        Utilizator utilizator=new Utilizator();
        utilizator.setId(1l);
        utilizator.setParola("asd");
        utilizator.setUsername("Marcel@yahoo.com");
        utilizator.setStatus(1l);


        utilizators.add(utilizator);

        when(utilizatorRepository.findAll()).thenReturn(utilizators);
        List<Utilizator> utilizators1=utilizatorService.findAll();
        assertEquals(utilizators.size(),1);
        verify(utilizatorRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Find by id-HF")
    public void findById(){

        Utilizator utilizator=new Utilizator();
        utilizator.setId(1l);
        utilizator.setParola("asd");
        utilizator.setUsername("Marcel@yahoo.com");
        utilizator.setStatus(1l);


        when(utilizatorRepository.findById(utilizator.getId())).thenReturn(Optional.of(utilizator));

        Optional<Utilizator> result= Optional.ofNullable(utilizatorService.findById(utilizator.getId()));

        assertEquals(result.isPresent(),true);

    }

    @Test
    @DisplayName("Find by id-ERR")
    public void findByIdERR(){

        Utilizator utilizator=new Utilizator();
        utilizator.setId(1l);
        utilizator.setParola("asd");
        utilizator.setUsername("Marcel@yahoo.com");
        utilizator.setStatus(1l);

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->utilizatorService.findById(utilizator.getId()));

        assertEquals("Utilizatorul nu exista!",result.getMessage());


    }


    @Test
    @DisplayName("Create")
    public void newCreate(){

        Utilizator utilizator=new Utilizator();
        utilizator.setId(1l);
        utilizator.setParola("asd");
        utilizator.setUsername("Marcel@yahoo.com");
        utilizator.setStatus(1l);


        when(utilizatorRepository.save(utilizator)).thenReturn(utilizator);
        Utilizator result=utilizatorService.save(utilizator);

        assertEquals(utilizator.getId(),result.getId());
        assertEquals(utilizator.getParola(),result.getParola());
        assertEquals(utilizator.getUsername(),result.getUsername());
        assertEquals(utilizator.getStatus(),result.getStatus());

        verify(utilizatorRepository,times(1)).save(utilizator);
    }

    @Test
    @DisplayName("Delete ab by id")
    public void deleteById(){

        Utilizator utilizator=new Utilizator();
        utilizator.setId(1l);
        utilizator.setParola("asd");
        utilizator.setUsername("Marcel@yahoo.com");
        utilizator.setStatus(1l);

        when(utilizatorRepository.findById(utilizator.getId())).thenReturn(Optional.of(utilizator));
        utilizatorService.deleteById(utilizator.getId());

        verify(utilizatorRepository).deleteById(utilizator.getId());

    }


    @Test
    @DisplayName("Delete ab by id ERR")
    public void deleteByIdErr(){

        Utilizator utilizator=new Utilizator();
        utilizator.setId(1l);
        utilizator.setParola("asd");
        utilizator.setUsername("Marcel@yahoo.com");
        utilizator.setStatus(1l);

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->utilizatorService.findById(utilizator.getId()));

        assertEquals("Utilizatorul nu exista!",result.getMessage());

    }

}
