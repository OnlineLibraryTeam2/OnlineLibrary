package dao;

import dao.interfaces.ClientDao;
import model.Client;
import org.junit.*;

import javax.persistence.NoResultException;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by student on 16.2.11.
 */
public class ClientDaoImplTest {

    private static ClientDao clientDaoImpl;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        clientDaoImpl = new ClientDaoImpl();
    }


    @Test
    public void signIn() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        assertTrue(clientDaoImpl.add(client));
        assertEquals(client, clientDaoImpl.signIn(client.getLoginMail(),client.getPassword()));
        assertTrue(clientDaoImpl.delete(client));
    }

    @Test
    public void findClientByMail() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        assertTrue(clientDaoImpl.add(client));
        Client clients = clientDaoImpl.findClientByMail(client.getLoginMail());
        assertEquals(client, clients);
        assertTrue(clientDaoImpl.delete(client));
    }

    @Test
    public void showAllClients() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail1.com", "1234");
        Client client1 = new Client("Oleg", "Karpov", 23, "371", "mail2.com", "1234");
        List<Client> clients = new LinkedList<>();
        clients.add(client);
        clients.add(client1);
        assertTrue(clientDaoImpl.add(client));
        assertTrue(clientDaoImpl.add(client1));
        assertEquals(clients, clientDaoImpl.showAllClients());
        assertTrue(clientDaoImpl.delete(client));
        assertTrue(clientDaoImpl.delete(client1));
    }

    @Test
    public void showBlacklist() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail1.com", "1234");
        Client client1 = new Client("Ivan", "Ivanov", 23, "371", "mail11.com", "1234");

        List<Client> clients = new LinkedList<>();
        clients.add(client);
        clients.add(client1);

        assertTrue(clientDaoImpl.add(client));
        assertTrue(clientDaoImpl.add(client1));
        assertTrue(clientDaoImpl.addBlacklist(client));
        assertTrue(clientDaoImpl.addBlacklist(client1));
        assertNotEquals(clients, clientDaoImpl.showBlacklist());
        assertTrue(clientDaoImpl.delete(client));
        assertTrue(clientDaoImpl.delete(client1));
    }

    @Test
    public void addBlacklist() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        assertTrue(clientDaoImpl.add(client));
        assertTrue(clientDaoImpl.addBlacklist(client));
        Client clients = clientDaoImpl.findClientByMail(client.getLoginMail());
        assertTrue(clients.isBlackList());
        assertTrue(clientDaoImpl.delete(client));

    }

    @Test
    public void deleteFromBlacklist() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        assertTrue(clientDaoImpl.add(client));
        assertTrue(clientDaoImpl.addBlacklist(client));
        Client clients = clientDaoImpl.findClientByMail(client.getLoginMail());
        assertTrue(clients.isBlackList());
        assertTrue(clientDaoImpl.deleteFromBlacklist(client));
        clients = clientDaoImpl.findClientByMail(client.getLoginMail());
        assertFalse(clients.isBlackList());
        assertTrue(clientDaoImpl.delete(client));
    }

    @Test
    public void add() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        assertTrue(clientDaoImpl.add(client));
        Client clients = clientDaoImpl.findClientByMail(client.getLoginMail());
        assertEquals(client, clients);
        assertTrue(clientDaoImpl.delete(client));
    }

    @Test
    public void update() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        assertTrue(clientDaoImpl.add(client));
        client.setName("Ivan1");
        client.setAge(33);
        assertTrue(clientDaoImpl.add(client));
        Client clients = clientDaoImpl.findClientByMail(client.getLoginMail());
        assertEquals(client, clients);
        assertTrue(clientDaoImpl.delete(client));
    }

    @Test(expected=NoResultException.class)
    public void delete() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        assertTrue(clientDaoImpl.add(client));
        assertTrue(clientDaoImpl.delete(client));
        Client clients = clientDaoImpl.findClientByMail(client.getLoginMail());
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        clientDaoImpl = null;
    }


}