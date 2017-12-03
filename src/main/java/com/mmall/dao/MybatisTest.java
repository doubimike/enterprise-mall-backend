package com.mmall.dao;

import java.io.IOException;
        import java.io.InputStream;
        import java.util.List;

        import org.apache.ibatis.io.Resources;
        import org.apache.ibatis.session.SqlSession;
        import org.apache.ibatis.session.SqlSessionFactory;
        import org.apache.ibatis.session.SqlSessionFactoryBuilder;

        import com.mmall.dao.MmallCartMapper;
        import com.mmall.dao.MmallCart;

public class MybatisTest {
    private static SqlSessionFactory sqlSessionFactory;

    public static void main(String[] args) {
        // Mybatis 配置文件
        String resource = "mybatis.cfg.xml";

        // 得到配置文件流
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建会话工厂，传入 MyBatis 的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

         selectCartById();

    }


    // 根据id查询用户信息
    private static void selectCartById() {

        SqlSession session = sqlSessionFactory.openSession();

        MmallCartMapper mapper = session.getMapper(MmallCartMapper.class);
        try {
            MmallCart user = mapper.selectCartById(126);
            session.commit();
            System.out.println(user.getId() + " ");
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }

        session.close();
    }
}
