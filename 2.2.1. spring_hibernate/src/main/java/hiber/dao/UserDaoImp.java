package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    public List<User> getUserByModelAndSeries(String model, int series) {
        String hql = "FROM Car where model = :parammodel and series = :paramseries";
//        TypedQuery<Car> query = sessionFactory.getCurrentSession()
//                .createQuery(hql, Car.class)
//                .setParameter("parammodel", model)
//                .setParameter("paramseries", series);
       List<User> users_list = sessionFactory.getCurrentSession()
               .createQuery("select u from User u where u.car.model = :model and u.car.series = :series", User.class)
               .setParameter("model", model)
               .setParameter("series", series)
               .list();
        return users_list;
    }

}
