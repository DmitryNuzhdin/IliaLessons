package project.models;

/**
 * @author Ilia Moskalenko
 */
public interface Model<T> {
    T create(T t) throws Exception;
    T update(T t) throws Exception;
    T getAll(T t);
}
