package com.fitnessclub;

import com.fitnessclub.DOMAIN.Rol;
import com.fitnessclub.REPOSITORIES.RolRepository;
import com.fitnessclub.SERVICES.RolService;
import com.fitnessclub.SERVICES.RolServiceImpl;
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

public class RolServiceTest {

    RolService rolService;

    @Mock
    RolRepository rolRepository;

    @Rule
    public MockitoRule rule= MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception{
        rolService=new RolServiceImpl(rolRepository);
    }


    @Test
    @DisplayName("Find all!")
    public void findAll()
    {
        List<Rol> rolList=new ArrayList<>();

        Rol rol=new Rol();
        rol.setId(1l);
        rol.setRol("ANG");
        rol.setUsername("george@yahoo.com");


        rolList.add(rol);

        when(rolRepository.findAll()).thenReturn(rolList);
        List<Rol> rolList1=rolService.findAll();
        assertEquals(rolList1.size(),1);
        verify(rolRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Find by id-HF")
    public void findById(){

        Rol rol=new Rol();
        rol.setId(1l);
        rol.setRol("ANG");
        rol.setUsername("george@yahoo.com");


        when(rolRepository.findById(rol.getId())).thenReturn(Optional.of(rol));

        Optional<Rol> result= Optional.ofNullable(rolService.findById(rol.getId()));

        assertEquals(result.isPresent(),true);

    }

    @Test
    @DisplayName("Find by id-ERR")
    public void findByIdERR(){

        Rol rol=new Rol();
        rol.setId(1l);
        rol.setRol("ANG");
        rol.setUsername("george@yahoo.com");

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->rolService.findById(rol.getId()));

        assertEquals("Rolul nu exista!",result.getMessage());


    }


    @Test
    @DisplayName("Create")
    public void newCreate(){
        Rol rol=new Rol();
        rol.setId(1l);
        rol.setRol("ANG");
        rol.setUsername("george@yahoo.com");

        when(rolRepository.save(rol)).thenReturn(rol);
        Rol result=rolService.save(rol);

        assertEquals(rol.getId(),result.getId());
        assertEquals(rol.getRol(),result.getRol());
        assertEquals(rol.getUsername(),result.getUsername());



        verify(rolRepository,times(1)).save(rol);
    }

    @Test
    @DisplayName("Delete ab by id")
    public void deleteById(){
        Rol rol=new Rol();
        rol.setId(1l);
        rol.setRol("ANG");
        rol.setUsername("george@yahoo.com");

        when(rolRepository.findById(rol.getId())).thenReturn(Optional.of(rol));
        rolService.deleteById(rol.getId());

        verify(rolRepository).deleteById(rol.getId());

    }


    @Test
    @DisplayName("Delete ab by id ERR")
    public void deleteByIdErr(){
        Rol rol=new Rol();
        rol.setId(1l);
        rol.setRol("ANG");
        rol.setUsername("george@yahoo.com");

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->rolService.findById(rol.getId()));

        assertEquals("Rolul nu exista!",result.getMessage());

    }


}
