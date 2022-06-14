package com.fitnessclub;

import com.fitnessclub.DOMAIN.Client;
import com.fitnessclub.REPOSITORIES.ClientRepository;
import com.fitnessclub.SERVICES.ClientService;
import com.fitnessclub.SERVICES.ClientServiceImpl;
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

public class ClientServiceTest {

    ClientService clientService;

    @Mock
    ClientRepository clientRepository;

    @Rule
    public MockitoRule rule= MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception{
        clientService=new ClientServiceImpl(clientRepository);
    }


    @Test
    @DisplayName("Find all!")
    public void findAll()
    {
        List<Client> clients=new ArrayList<>();

        Client client=new Client();
        client.setId(1l);
        client.setEmail("a@yahoo.com");
        client.setNume("Ion");
        client.setPrenume("Marcel");
        client.setTelefon("09234273");
        client.setSex("Masculin");


        clients.add(client);

        when(clientRepository.findAll()).thenReturn(clients);
        List<Client> clientList=clientService.findAll();
        assertEquals(clientList.size(),1);
        verify(clientRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Find by id-HF")
    public void findById(){

        Client client=new Client();
        client.setId(1l);
        client.setEmail("a@yahoo.com");
        client.setNume("Ion");
        client.setPrenume("Marcel");
        client.setTelefon("09234273");
        client.setSex("Masculin");


        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

        Optional<Client> result= Optional.ofNullable(clientService.findById(client.getId()));

        assertEquals(result.isPresent(),true);

    }

    @Test
    @DisplayName("Find by id-ERR")
    public void findByIdERR(){

        Client client=new Client();
        client.setId(1l);
        client.setEmail("a@yahoo.com");
        client.setNume("Ion");
        client.setPrenume("Marcel");
        client.setTelefon("09234273");
        client.setSex("Masculin");

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->clientService.findById(client.getId()));

        assertEquals("Clientul nu exista!",result.getMessage());


    }


    @Test
    @DisplayName("Create")
    public void newCreate(){

        Client client=new Client();
        client.setId(1l);
        client.setEmail("a@yahoo.com");
        client.setNume("Ion");
        client.setPrenume("Marcel");
        client.setTelefon("09234273");
        client.setSex("Masculin");

        when(clientRepository.save(client)).thenReturn(client);
        Client result=clientService.save(client);

        assertEquals(client.getId(),result.getId());
        assertEquals(client.getNume(),result.getNume());
        assertEquals(client.getPrenume(),result.getPrenume());
        assertEquals(client.getEmail(),result.getEmail());
        assertEquals(client.getSex(),result.getSex());
        assertEquals(client.getTelefon(),result.getTelefon());



        verify(clientRepository,times(1)).save(client);
    }

    @Test
    @DisplayName("Delete ab by id")
    public void deleteById(){

        Client client=new Client();
        client.setId(1l);
        client.setEmail("a@yahoo.com");
        client.setNume("Ion");
        client.setPrenume("Marcel");
        client.setTelefon("09234273");
        client.setSex("Masculin");

        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
        clientService.deleteById(client.getId());

        verify(clientRepository).deleteById(client.getId());

    }


    @Test
    @DisplayName("Delete ab by id ERR")
    public void deleteByIdErr(){
        Client client=new Client();
        client.setId(1l);
        client.setEmail("a@yahoo.com");
        client.setNume("Ion");
        client.setPrenume("Marcel");
        client.setTelefon("09234273");
        client.setSex("Masculin");

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->clientService.findById(client.getId()));

        assertEquals("Clientul nu exista!",result.getMessage());

    }

}
