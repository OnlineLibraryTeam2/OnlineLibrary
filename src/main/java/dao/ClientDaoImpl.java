package dao;

import dao.interfaces.ClientDao;
import model.Client;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component("clientDao")
@Transactional
public class ClientDaoImpl implements ClientDao {


    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean add(Client client) {
        manager.persist(client);
        return true;
    }

    @Override
    public boolean update(Client client) {
        manager.merge(client);
        return true;
    }

    @Override
    public boolean delete(Client client) {
        client = manager.find(Client.class, client.getId());
        manager.remove(client);
        return true;
    }

    @Override
    public Client signIn(String loginMail, String password) {
        TypedQuery<Client> query = manager.createQuery(
                "Select c FROM Client c WHERE c.loginMail =:login AND c.password =:pass", Client.class);

        query.setParameter("login", loginMail);
        query.setParameter("pass", password);

        return query.getSingleResult();

    }

    @Override
    public Client findClientByMail(String mailClient) {

        TypedQuery<Client> query = manager.createQuery(
                "Select c FROM Client c WHERE c.loginMail =:login", Client.class);

        query.setParameter("login", mailClient);

        return query.getSingleResult();
    }

    @Override
    public List<Client> showAllClients() {
        TypedQuery<Client> query = manager.createQuery(
                "Select c FROM Client c", Client.class);

        return query.getResultList();
    }

    @Override
    public List<Client> showBlacklist() {
        TypedQuery<Client> query = manager.createQuery(
                "Select c FROM Client c WHERE c. blackList =:true", Client.class);

        query.setParameter("true", true);

        return query.getResultList();
    }

    @Override
    public boolean addBlacklist(Client client) {
        client.setBlackList(true);
        return update(client);
    }

    @Override
    public boolean deleteFromBlacklist(Client client) {
        client.setBlackList(false);
        return update(client);
    }
}
