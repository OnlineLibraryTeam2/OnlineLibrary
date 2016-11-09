package dao;

import model.Client;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ClientDao implements IDao<Client> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean add(Client client) {
        sessionFactory.getCurrentSession().save(client);
        return true;
    }


    @Override
    @Transactional
    public boolean delete(Client client) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Client.class);
        criteria.add(Restrictions.eq("id", client.getId()));
        sessionFactory.getCurrentSession().delete(client);
        return true;
    }

    @Transactional
    public Client signIn(String loginMail, String password) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Client.class);
        criteria.add(Restrictions.eq("loginMail", loginMail));
        criteria.add(Restrictions.eq("password", password));

        return (Client) criteria.uniqueResult();

    }

    @Transactional
    public Client findClientByMail(String mailClient) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Client.class);
        criteria.add(Restrictions.eq("loginMail", mailClient));

        return (Client) criteria.uniqueResult();
    }

    @Transactional
    public List<Client> showAllClients() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Client.class);
        return criteria.list();
    }

    @Transactional
    public List<Client> showBlacklist() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Client.class);
        criteria.add(Restrictions.eq("blackList", true));

        return criteria.list();
    }

    @Transactional
    public boolean addBlacklist(Client client) {
        client.setBlackList(true);
        return add(client);
    }

    @Transactional
    public boolean deleteFromBlacklist(Client client) {
        client.setBlackList(false);
        return add(client);
    }



}
