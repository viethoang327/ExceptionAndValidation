package IntefaceAndFunciton;

import java.util.List;

public interface IManagement<T> {
    public void add(T t);
    public T getDataById(List<T> t, long id);
    public List<T> getAllData();
}
