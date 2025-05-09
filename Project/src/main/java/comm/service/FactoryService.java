package comm.service;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class FactoryService {
    private static SqlSessionFactory factory;

    static {
        try {
            Reader r = Resources.getResourceAsReader("mybatis/config/config.xml");
            factory = new SqlSessionFactoryBuilder().build(r);
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getFactory() {
        if (factory == null) {
            System.err.println("❌ SqlSessionFactory is NULL! MyBatis 설정을 확인하세요.");
        }
        return factory;
    }
}



