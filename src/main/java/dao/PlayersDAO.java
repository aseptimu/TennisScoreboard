package dao;

import entities.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class PlayersDAO implements DAO<Player>{
    public void create(Player player) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
//        String hql = "FROM Player WHERE name= :name";
//        Query<Player> query = session.createQuery(hql, Player.class);
//        query.setParameter("name", player.getName());
//        Player presentedPlayer = query.uniqueResult();
//        if (presentedPlayer == null) {
            session.merge(player);
//        }
        session.getTransaction().commit();
    }

    @Override
    public Optional<Player> read(int id) {
        return Optional.empty();
    }
}
