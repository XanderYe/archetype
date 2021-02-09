#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.base;

import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * 通用Service
 * @author XanderYe
 * @date 2019-03-19
 */
public interface BaseService<T> {

    /**
     *  根据id查询实体
     * @param id : id
     * @return : T
     */
    T queryById(Object id);

    /**
     *  查询所有
     * @return : List<T>
     */
    List<T> queryAll();

    /**
     *  条件查询
     * @param param : T
     * @return : 查询结果集
     */
    List<T> queryListByWhere(T param);

    /**
     *  查询记录数
     * @param param : T
     * @return : 查询数量
     */
    Integer queryCount(T param);

    /**
     *  分页查询
     * @param param : T
     * @param page : 页码
     * @param rows : 行数
     * @return : 返回结果集
     */
    PageInfo<T> queryPageListByWhere(T param, Integer page, Integer rows);

    /**
     *  分页倒序查询
     * @param param : T
     * @param page : 页码
     * @param rows : 行数
     * @return : 返回结果集
     */
    public PageInfo<T> queryPageListByWhereDesc(Class<T> clazz, T param, Integer page, Integer rows);

    /**
     *  查询一条记录
     * @param param : t
     * @return : T
     */
    T queryOne(T param);

    /**
     *  插入
     * @param param : T
     * @return : 保存数量
     */
    Integer insert(T param);

    /**
     *  新增非空字段
     * @param param : T
     * @return : 新增数量
     */
    Integer insertSelective(T param);

    /**
     * 批量插入
     * @param list : list<T>
     * @return : 插入数量
     */
    Integer saveList(List<T> list);

    /**
     *  根据主键更新
     * @param param : T
     * @return : 更新数量
     */
    Integer update(T param);

    /**
     *  根据主键更新非空字段
     * @param param : 实体类
     * @return : 更新个数
     */
    Integer updateSelective(T param);

    /**
     * 根据条件更新
     * @param param : 实体类
     * @param example : example
     * @return : 更新个数
     */
    Integer updateByExample(T param, Example example);

    /**
     *  根据主键删除
     * @param id : id
     * @return : 删除个数
     */
    Integer deleteById(Object id);

    /**
     * 条件删除
     * @param param : 对象
     * @return :
     */
    Integer delete(T param);

    /**
     *  批量删除
     * @param clazz : class
     * @param values : List<Object>
     * @return : 个数
     */
    Integer deleteByIds(Class<T> clazz, List<Object> values);

    /**
     * 根据where和order by查询集合
     * @param clz : T 的class
     * @param orderBy : map<表字段，排序> 正序用true, 倒序用false
     * @param where : 实体类，需要过滤的条件
     * @return : list<T>
     */
    List<T> queryByWhereAndOrderBy(Class<T> clz, Map<String, Boolean> orderBy, Object where);

    /**
     * 根据条件 和模糊调价你查询
     * @param clz : 实体类的class
     * @param like : map<数据库字段名对应的实体类字段, 需要模糊查询的值>
     * @param where : 实体类(需要过滤的条件)
     * @return : list集合
     */
    List<T> queryByLike(Class<T> clz, Map<String, Object> like, Object where);

    /**
     * 根据条件 和模糊调价你查询，分页
     * @param clz : 实体类的class
     * @param like : map<数据库字段名对应的实体类字段, 需要模糊查询的值>
     * @param where : 实体类(需要过滤的条件)
     * @param page : 起始页面
     * @param rows : 每页条数
     * @return
     */
    PageInfo<T> queryByLike(Class<T> clz, Map<String, Object> like, Object where, Integer page, Integer rows);
}
