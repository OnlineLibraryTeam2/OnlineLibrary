package dao;

import model.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 11/1/16.
 * Client DAO implementation.
 */
public class ClientDao implements IDao<Client> {

    private EntityManagerFactory factory;

    public ClientDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public boolean signIn(String loginMail, String password) {
        if (!loginMail.equals("") && !password.equals("")) {
            EntityManager entityManager = factory.createEntityManager();

            try {
                TypedQuery<Client> typedQuery = entityManager.createQuery("SELECT client FROM Client client WHERE client.loginMail = loginMail AND client.password = password", Client.class);

                if (typedQuery.getSingleResult().getLoginMail().equals(loginMail) && typedQuery.getSingleResult().getPassword().equals(password)) {

                    return true;
                }
            } finally {
                entityManager.close();
            }
        }
        return false;
    }

    public Client findClientByMail(String mailClient) {
        if (!mailClient.equals("")) {
            EntityManager entityManager = factory.createEntityManager();

            try {
                TypedQuery<Client> typedQuery = entityManager.createQuery("SELECT client FROM Client client WHERE client.loginMail = mailClient", Client.class);

                return typedQuery.getSingleResult();
            } finally {
                entityManager.close();
            }
        }
        return null;
    }

    public List<Client> showAllClients() {
        EntityManager entityManager = factory.createEntityManager();

        try {
            TypedQuery<Client> typedQuery = entityManager.createQuery("SELECT client FROM Client client", Client.class);

            return typedQuery.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public List<Client> showBlacklist() {
        EntityManager entityManager = factory.createEntityManager();

        try {
            TypedQuery<Client> typedQuery = entityManager.createQuery("SELECT client from Client client", Client.class);
            List<Client> clientsList = typedQuery.getResultList();
            List<Client> blacklistClients = new ArrayList<>();

            clientsList.stream().forEach(client -> {
                if (client.isBlackList()) {
                    blacklistClients.add(client);
                }
            });

            return blacklistClients;
        } finally {
            entityManager.close();
        }
    }

    public boolean addBlacklist(Client client) {
        if (client != null) {
            EntityManager entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            client = entityManager.find(Client.class, client.getId());

            try {
                entityTransaction.begin();
                client.setBlackList(true);
                entityTransaction.commit();

                return true;
            } catch (Exception e) {
                entityTransaction.rollback();

                return false;
            } finally {
                entityManager.close();
            }
        }

        return false;
    }

    public boolean deleteFromBlacklist(Client client) {
        if (client != null) {
            EntityManager entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            client = entityManager.find(Client.class, client.getId());

            try {
                entityTransaction.begin();
                client.setBlackList(false);
                entityTransaction.commit();

                return true;
            } catch (Exception e) {
                entityTransaction.rollback();

                return false;
            } finally {
                entityManager.close();
            }
        }

        return false;
    }

    @Override
    public boolean add(Client client) {
        if (client != null) {
            EntityManager entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            try {
                entityTransaction.begin();
                entityManager.persist(client);
                entityTransaction.commit();

                return true;
            } catch (Exception e) {
                entityTransaction.rollback();

                return false;
            } finally {
                entityManager.close();
            }
        }
        return false;
    }

    @Override
    public boolean update(Client client) {
        if (client != null) {
            EntityManager entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            Client lookupClient = entityManager.find(Client.class, client.getId());

            try {
                entityTransaction.begin();
                lookupClient.setName(client.getName());
                lookupClient.setSurname(client.getSurname());
                lookupClient.setAge(client.getAge());
                lookupClient.setPhoneNumber(client.getPhoneNumber());
                lookupClient.setLoginMail(client.getLoginMail());
                lookupClient.setPassword(client.getPassword());
                lookupClient.setBlackList(client.isBlackList());
                lookupClient.setHistory(client.getHistory());
                lookupClient.setTakenBooks(client.getTakenBooks());
                //lookupClient.setReservationBooks(client.getReservationBooks());
                entityManager.merge(client);
                entityTransaction.commit();

                return true;
            } catch (Exception e) {
                entityTransaction.rollback();

                return false;
            } finally {
                entityManager.close();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Client client) {
        if (client != null) {
            EntityManager entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            client = entityManager.find(Client.class, client.getId());

            try {
                entityTransaction.begin();
                entityManager.remove(client);
                entityTransaction.commit();

                return true;
            } catch (Exception e) {
                entityTransaction.rollback();

                return false;
            } finally {
                entityManager.close();
            }
        }
        return false;
    }

}
