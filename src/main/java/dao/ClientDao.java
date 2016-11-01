package dao;

import model.Client;

import java.util.List;

/**
 * Created by student on 11/1/16.
 */
public class ClientDao implements IDao {

    @Override
    public boolean add(Object obj) {
        return false;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }

    public boolean signIn(String loginMail, String password) {

        return false;
    }

    public Client findClientByMail(String mailClient) {

        return null;
    }

    public List<Client> showAllClients() {

        return null;
    }

    public List<Client> showBlacklist() {

        return null;
    }

    public boolean addBlacklist(Client client) {

        return false;
    }

    public boolean deleteFromBlacklist(Client client) {

        return false;
    }

}
