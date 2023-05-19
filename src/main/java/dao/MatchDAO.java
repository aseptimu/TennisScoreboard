package dao;

import dto.Match;
import dto.score.Scorer;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import entities.Matches;
import entities.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class MatchDAO implements DAO<Match> {

    public void create(Match match) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "FROM Player WHERE name= :name";

        Query<Player> query1 = session.createQuery(hql, Player.class);
        query1.setParameter("name", match.getPlayer1().getName());
        Player player1 = query1.uniqueResult();
        if (player1 == null) {
            player1 = session.merge(match.getPlayer1());
        }

        Query<Player> query2 = session.createQuery(hql, Player.class);
        query2.setParameter("name", match.getPlayer2().getName());
        Player player2 = query2.uniqueResult();
        if (player2 == null) {
            player2 = session.merge(match.getPlayer2());
        }

        Matches matches = new Matches(player1, player2);
        Player winner = match.getWinner() == Scorer.FIRST_PLAYER ? player1 : player2;
        matches.setWinner(winner);
        session.persist(matches);
        session.getTransaction().commit();
    }

    public Optional<Match> read(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
//        Player player = session.get();
//        String hql = "FROM Player WHERE name= :name";
//        Query<Player> query = session.createQuery(hql, Player.class);
//        query.setParameter("name", player.getName());
//        Player presentedPlayer = query.uniqueResult();
//        if (presentedPlayer == null) {
//            session.persist(player);
//        }
        session.getTransaction().commit();
        return Optional.empty();
    }

    public List<Matches> getAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
//        session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Matches> cq = cb.createQuery(Matches.class);
        Root<Matches> rootEntry = cq.from(Matches.class);
        CriteriaQuery<Matches> all = cq.select(rootEntry);

        TypedQuery<Matches> allQuery = session.createQuery(all);
        List<Matches> resultList = allQuery.getResultList();
//        session.getTransaction().commit();
        return resultList;
    }
}
