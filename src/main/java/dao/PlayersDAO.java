package dao;

import model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class PlayersDAO {
    public Player addPlayer(Player player) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "FROM Player WHERE name= :name";
        Query<Player> query = session.createQuery(hql, Player.class);
        query.setParameter("name", player.getName());
        Player presentedPlayer = query.uniqueResult();
        if (presentedPlayer == null) {
            session.persist(player);
        }
        session.getTransaction().commit();
        return player;
    }
}
