package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO: database access object
 * 封装数据表的通用的操作，考虑事务（使用dbutils）
 *
 */
@SuppressWarnings("unchecked")
public abstract class BaseDAO<T> {
    private QueryRunner runner = new QueryRunner();
    private Class<T> clazz;

    {
        //获取当前BaseDAO的子类继承的父类中的泛型的具体类型
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;

        Type[] typeArguments = paramType.getActualTypeArguments(); //获取了父类的泛型参数
        clazz = (Class<T>) typeArguments[0]; //泛型的第一个参数
    }

    //通用的增删改操作
    public int updateUniversal(Connection connection, String sql, Object... args) throws SQLException {
        return runner.update(connection, sql, args);
    }

    //查询一条记录
    public T searchSingleRecord(Connection connection, String sql, Object... args) throws SQLException {
        BeanHandler<T> handler = new BeanHandler<>(clazz);
        return runner.query(connection, sql, handler, args);
    }

    //查询多条记录
    public List<T> searchMutipleRecord(Connection connection, String sql, Object... args) throws SQLException {
        BeanListHandler<T> handler = new BeanListHandler<>(clazz);
        return runner.query(connection, sql, handler, args);
    }

    //获取特殊值
    public <E> E getValue(Connection connection, String sql, Object... args) throws SQLException {
        ScalarHandler<E> handler = new ScalarHandler<>();
        return runner.query(connection, sql, handler, args);
    }
}
