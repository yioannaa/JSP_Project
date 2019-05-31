package dao;

import entity.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;
import io.vavr.collection.List;



public class UserDaoJPA implements Dao<User, NewUser> {


    private final EntityManager em;


    public UserDaoJPA(EntityManager em) {
        this.em = em;
    }



    @Override
    public List<User> getAll() {
        em.getTransaction().begin();
        Query q = em.createQuery("From UserEntity");
        List<User> result = List.ofAll(q.getResultStream().map(e->new User((UserEntity) e)));
        em.getTransaction().commit();
        return result;
    }

    @Override
    public Optional<User> get(long id) {
        return getEntity(id).map(user -> new User(user));
    }

    @Override
    public void save(NewUser obj) {
        em.getTransaction().begin();
        em.persist(new UserEntity(obj));
        em.getTransaction().commit();

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(User obj) {

    }

    private Optional<UserEntity> getEntity (long id)
    {
        try {
            return Optional.of(em.createQuery("Select a from UserEntity a where id = :id", UserEntity.class)
                    .setParameter("id", id)
                    .getSingleResult());
        }catch (Exception e){
            return Optional.empty();
        }

    }

    @Override
    public List<User> getLimited(int from, int to) {
        return null;
    }
}
