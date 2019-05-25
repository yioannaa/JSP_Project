package dao;


import java.util.List;

public interface Dao<T, U> {
        List<T> getAll();

        T get(long id);

        void save(U obj);

        void delete(T obj);

        void update(T obj);
}
