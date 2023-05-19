package dao;

import java.util.List;
import java.util.Optional;

public interface DAO <T> {
    void create(T t);
    Optional<T> read(int id);

}
