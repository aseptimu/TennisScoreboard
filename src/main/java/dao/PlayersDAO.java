package dao;

import model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class PlayersDAO {
    public Player addPlayer(String name) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "FROM Player WHERE name= :name";
        Query<Player> query = session.createQuery(hql, Player.class);
        query.setParameter("name", name);
        Player player = query.uniqueResult();
        if (player == null) {
            player = new Player();
            player.setName(name);
            session.persist(player);//TODO: save in the end of match
        }
        session.getTransaction().commit();
        return player;
    }
}
