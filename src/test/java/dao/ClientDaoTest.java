package dao;

import model.Client;
import org.junit.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

    }

    @Test
    public void showAllClients() throws Exception {

    }

    @Test
    public void showBlacklist() throws Exception {

    }

    @Test
    public void addBlacklist() throws Exception {

    }

    @Test
    public void deleteFromBlacklist() throws Exception {

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

    @Test
    public void delete() throws Exception {
        Client client = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        assertTrue(clientDao.add(client));
        assertTrue(clientDao.delete(client));
        Client clients = clientDao.findClientByMail(client.getLoginMail());
        assertNotEquals(client, clients);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        factory.close();
        clientDao = null;
    }


}