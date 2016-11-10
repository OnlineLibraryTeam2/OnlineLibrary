package dao;

import dao.interfaces.AuthorDao;
import dao.interfaces.BookDao;
import dao.interfaces.ClientDao;
import model.Client;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.NoResultException;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by student on 16.2.11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-test-context.xml")
public class ClientDaoImplTest {

    @Autowired
    private ClientDao clientDao;

    private static Client client;

    @Before
    public void setUp() throws Exception {
        client = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
    }

    @After
    public void tearDown() throws Exception {
        client = null;
    }

    @Test
    public void add() throws Exception {
        assertTrue(clientDao.add(client));
        Client clientFromDb = clientDao.findClientByMail(client.getLoginMail());
        assertEquals(client, clientFromDb);
        assertTrue(clientDao.delete(client));
    }

    @Test
    public void update() throws Exception {

        assertTrue(clientDao.add(client));
        client.setName("Oleg");
        client.setAge(33);
        assertTrue(clientDao.update(client));
        Client clientFromDb = clientDao.findClientByMail(client.getLoginMail());
        assertEquals(client, clientFromDb);
        assertTrue(clientDao.delete(client));
    }

    @Test(expected=NoResultException.class)
    public void delete() throws Exception {

        assertTrue(clientDao.add(client));
        assertTrue(clientDao.delete(client));
        clientDao.findClientByMail(client.getLoginMail());
    }

    @Test
    public void signIn() throws Exception {
        assertTrue(clientDao.add(client));
        assertEquals(client, clientDao.signIn(client.getLoginMail(),client.getPassword()));
        assertTrue(clientDao.delete(client));
    }

    @Test
    public void findClientByMail() throws Exception {

        assertTrue(clientDao.add(client));
        Client clientFromDb = clientDao.findClientByMail(client.getLoginMail());
        assertEquals(client, clientFromDb);
        assertTrue(clientDao.delete(client));
    }

    @Test
    public void showAllClients() throws Exception {

        Client client1 = new Client("Oleg", "Karpov", 23, "371", "mail2.com", "1234");
        List<Client> expected = new LinkedList<>();
        expected.add(client);
        expected.add(client1);

        for (Client current: expected) {
            assertTrue(clientDao.add(current));
        }

        assertEquals(expected, clientDao.showAllClients());
        for (Client current: expected) {
            assertTrue(clientDao.delete(current));
        }
    }

    @Test
    public void showBlacklist() throws Exception {

        Client client1 = new Client("Ivan", "Ivanov", 23, "371", "mail11.com", "1234");

        List<Client> expected = new LinkedList<>();
        expected.add(client);
        expected.add(client1);

        for (Client current: expected) {
            assertTrue(clientDao.add(current));
        }

        for (Client current: expected) {
            assertTrue(clientDao.addBlacklist(current));
        }

        client.setBlackList(false);
        client1.setBlackList(false);

        assertNotEquals(expected, clientDao.showBlacklist());
        assertTrue(clientDao.delete(client));
        assertTrue(clientDao.delete(client1));
    }

    @Test
    public void addBlacklist() throws Exception {

        assertTrue(clientDao.add(client));
        assertTrue(clientDao.addBlacklist(client));
        client = clientDao.findClientByMail(client.getLoginMail());
        assertTrue(client.isBlackList());
        assertTrue(clientDao.delete(client));

    }

    @Test
    public void deleteFromBlacklist() throws Exception {

        assertTrue(clientDao.add(client));
        assertTrue(clientDao.addBlacklist(client));
        client = clientDao.findClientByMail(client.getLoginMail());
        assertTrue(client.isBlackList());
        assertTrue(clientDao.deleteFromBlacklist(client));
        client = clientDao.findClientByMail(client.getLoginMail());
        assertFalse(client.isBlackList());
        assertTrue(clientDao.delete(client));
    }

}