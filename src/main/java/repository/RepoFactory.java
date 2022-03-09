package repository;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class RepoFactory {
    private static final Map<Class<?>, Repository<?>> map = new LinkedHashMap<>();
    private final Connection connector;

    public RepoFactory(Connection connection){
        connector = connection;
    }


}
//public class RepoFactory {
//    private static final Map<Class<?>, Repository<?>> map = new LinkedHashMap<>();
//    private String URL;
//    //private Connection connector;
//
//    //static {
//    //    map.put(Auto.class, AutoRepository.getInstance());
//    //    map.put(User.class, UserRepository.getInstance());
//    //    map.put(Orders.class, OrdersRepository.getInstance());
//    //}
//
//    public RepoFactory(String URL) throws SQLException{
//        if (URL == null){
//            throw new SQLException("Null URL");
//        }
//        this.URL = URL;
//        Connection connector = DriverManager.getConnection(URL);
//        //map.values().forEach(i -> i.connect(URL));
//        map.put(Auto.class, new AutoRepository(connector));
//    }
//
//    public static void putRepository(Class<?> clazz, Repository<?> repo){
//        map.put(clazz, repo);
//    }
//
//    public static Repository<?> getRepository(Object object){
//        return map.getOrDefault(object.getClass(), null);
//    }
//
//    public static Repository<?> getRepository(Class<?> clazz){
//        return map.getOrDefault(clazz, null);
//    }
//}
