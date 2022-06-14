package com.fitnessclub;

import com.fitnessclub.DOMAIN.Client;
import com.fitnessclub.DOMAIN.Istoric_client;
import com.fitnessclub.REPOSITORIES.IstoricClientRepository;
import com.fitnessclub.SERVICES.IstoricClientService;
import com.fitnessclub.SERVICES.IstoricClientServiceImpl;
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

public class IstoricClientServiceTest {

    IstoricClientService istoricClientService;

    @Mock
    IstoricClientRepository istoricClientRepository;

    @Rule
    public MockitoRule rule= MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception{
        istoricClientService=new IstoricClientServiceImpl(istoricClientRepository);
    }


    @Test
    @DisplayName("Find all!")
    public void findAll()
    {
        List<Istoric_client> istoric_clients=new ArrayList<>();

        Istoric_client istoric_client=new Istoric_client();
        istoric_client.setId(1l);
        istoric_client.setIdc(1l);
        istoric_client.setVarsta(26);
        Client client=new Client();
        client.setId(1l);
        istoric_client.setClients(client);

        istoric_clients.add(istoric_client);

        when(istoricClientRepository.findAll()).thenReturn(istoric_clients);
        List<Istoric_client> istoricClients=istoricClientService.findAll();
        assertEquals(istoricClients.size(),1);
        verify(istoricClientRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Find by id-HF")
    public void findById(){

        Istoric_client istoric_client=new Istoric_client();
        istoric_client.setId(1l);
        istoric_client.setIdc(1l);
        istoric_client.setVarsta(26);
        Client client=new Client();
        client.setId(1l);
        istoric_client.setClients(client);


        when(istoricClientRepository.findById(istoric_client.getId())).thenReturn(Optional.of(istoric_client));

        List<Istoric_client> result= istoricClientService.findById(istoric_client.getId());

        assertEquals(result.isEmpty(),false);

    }

    @Test
    @DisplayName("Find by id-ERR")
    public void findByIdERR(){
        Istoric_client istoric_client=new Istoric_client();
        istoric_client.setId(1l);
        istoric_client.setIdc(1l);
        istoric_client.setVarsta(26);
        Client client=new Client();
        client.setId(1l);
        istoric_client.setClients(client);
        RuntimeException result=assertThrows(RuntimeException.class,
                ()->istoricClientService.findById(istoric_client.getId()));

        assertEquals("Istoricul pentru acest client nu exista!",result.getMessage());


    }


    @Test
    @DisplayName("Create")
    public void newCreate(){
        Istoric_client istoric_client=new Istoric_client();
        istoric_client.setId(1l);
        istoric_client.setIdc(1l);
        istoric_client.setVarsta(26);
        Client client=new Client();
        client.setId(1l);
        istoric_client.setClients(client);

        when(istoricClientRepository.save(istoric_client)).thenReturn(istoric_client);
        Istoric_client result=istoricClientService.save(istoric_client);

        assertEquals(istoric_client.getId(),result.getId());
        assertEquals(istoric_client.getIdc(),result.getIdc());
        assertEquals(istoric_client.getVarsta(),result.getVarsta());
        assertEquals(istoric_client.getClients(),result.getClients());




        verify(istoricClientRepository,times(1)).save(istoric_client);
    }

    @Test
    @DisplayName("Delete ab by id")
    public void deleteById(){

    }


    @Test
    @DisplayName("Delete ab by id ERR")
    public void deleteByIdErr(){
        Istoric_client istoric_client=new Istoric_client();
        istoric_client.setId(1l);
        istoric_client.setIdc(1l);
        istoric_client.setVarsta(26);
        Client client=new Client();
        client.setId(1l);
        istoric_client.setClients(client);

        RuntimeException result=assertThrows(RuntimeException.class,
                ()->istoricClientService.findById(istoric_client.getId()));

        assertEquals("Istoricul pentru acest client nu exista!",result.getMessage());

    }

}
