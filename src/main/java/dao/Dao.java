package dao;
import java.util.List;

public interface Dao<T> {
    T get(long id);

    List<T> getAll();

    void add(T t);

    void delete(long id);

    void update(T t);
}
