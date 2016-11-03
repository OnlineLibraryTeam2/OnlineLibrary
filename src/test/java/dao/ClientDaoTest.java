package dao;

import model.Client;
import org.junit.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by student on 16.2.11.
 */
public class ClientDaoTest {
    private static EntityManagerFactory factory;
    private static ClientDao clientDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        factory = Persistence.createEntityManagerFactory("hibernate-unit");
        clientDao  = new ClientDao(factory);

    }


    @Test
    public void signIn() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        assertTrue(clientDao.add(client));
        assertEquals(client, clientDao.signIn(client.getLoginMail(),client.getPassword()));
        assertTrue(clientDao.delete(client));
    }

    @Test
    public void findClientByMail() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        assertTrue(clientDao.add(client));
        Client clients = clientDao.findClientByMail(client.getLoginMail());
        assertEquals(client, clients);
        assertTrue(clientDao.delete(client));
    }

    @Test
    public void showAllClients() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail1.com", "1234");
        Client client1 = new Client("Ivan", "Ivanov", 23, "371", "mail11.com", "1234");
        List<Client> clients = new LinkedList<>();
        clients.add(client);
        clients.add(client1);
        assertTrue(clientDao.add(client));
        assertTrue(clientDao.add(client1));
        assertEquals(clients, clientDao.showAllClients());
        assertTrue(clientDao.delete(client));
        assertTrue(clientDao.delete(client1));
    }

    @Test
    public void showBlacklist() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail1.com", "1234");
        Client client1 = new Client("Ivan", "Ivanov", 23, "371", "mail11.com", "1234");

        List<Client> clients = new LinkedList<>();
        clients.add(client);
        clients.add(client1);

        assertTrue(clientDao.add(client));
        assertTrue(clientDao.add(client1));
        assertTrue(clientDao.addBlacklist(client));
        assertTrue(clientDao.addBlacklist(client1));
        assertNotEquals(clients, clientDao.showBlacklist());
        assertTrue(clientDao.delete(client));
        assertTrue(clientDao.delete(client1));
    }

    @Test
    public void addBlacklist() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        assertTrue(clientDao.add(client));
        assertTrue(clientDao.addBlacklist(client));
        Client clients = clientDao.findClientByMail(client.getLoginMail());
        assertTrue(clients.isBlackList());
        assertTrue(clientDao.delete(client));

    }

    @Test
    public void deleteFromBlacklist() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        assertTrue(clientDao.add(client));
        assertTrue(clientDao.addBlacklist(client));
        Client clients = clientDao.findClientByMail(client.getLoginMail());
        assertTrue(clients.isBlackList());
        assertTrue(clientDao.deleteFromBlacklist(client));
        clients = clientDao.findClientByMail(client.getLoginMail());
        assertFalse(clients.isBlackList());
        assertTrue(clientDao.delete(client));
    }

    @Test
    public void add() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        assertTrue(clientDao.add(client));
        Client clients = clientDao.findClientByMail(client.getLoginMail());
        assertEquals(client, clients);
        assertTrue(clientDao.delete(client));
    }

    @Test
    public void update() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        assertTrue(clientDao.add(client));
        client.setName("Ivan1");
        client.setAge(33);
        assertTrue(clientDao.update(client));
        Client clients = clientDao.findClientByMail(client.getLoginMail());
        assertEquals(client, clients);
        assertTrue(clientDao.delete(client));
    }

    @Test(expected=NoResultException.class)
    public void delete() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        assertTrue(clientDao.add(client));
        assertTrue(clientDao.delete(client));
        Client clients = clientDao.findClientByMail(client.getLoginMail());
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        factory.close();
        clientDao = null;
    }


}