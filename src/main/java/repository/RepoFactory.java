package repository;

import model.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class RepoFactory {
    private static final Map<Class<?>, Repository<?>> map = new LinkedHashMap<>();
    private String URL;

    static {
        map.put(Auto.class, AutoRepository.getInstance());
        map.put(User.class, UserRepository.getInstance());
        map.put(Orders.class, OrdersRepository.getInstance());
    }

    public RepoFactory(String URL){
        this.URL = URL;
        map.values().forEach(i -> i.connect(URL));
    }

    public static void putRepository(Class<?> clazz, Repository<?> repo){
        map.put(clazz, repo);
    }

    public static Repository<?> getRepository(Object object){
        return map.getOrDefault(object.getClass(), null);
    }

    public static Repository<?> getRepository(Class<?> clazz){
        return map.getOrDefault(clazz, null);
    }
}
