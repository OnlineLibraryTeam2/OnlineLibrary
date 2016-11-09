package dao.interfaces;

import model.Client;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alexp on 16.9.11.
 */
public interface ClientDao extends IDao<Client> {

    Client signIn(String loginMail, String password);

    Client findClientByMail(String mailClient);

    List<Client> showAllClients();

    List<Client> showBlacklist();

    boolean addBlacklist(Client client);

    boolean deleteFromBlacklist(Client client);
}
