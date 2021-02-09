#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用Service实现
 *
 * @author XanderYe
 * @date 2019-03-19
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    protected BaseMapper<T> baseMapper;

    @Override
    public T queryById(Object id) {
        return this.baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> queryAll() {
        return this.baseMapper.select(null);
    }

    @Override
    public List<T> queryListByWhere(T param) {
        return this.baseMapper.select(param);
    }

    @Override
    public Integer queryCount(T param) {
        return this.baseMapper.selectCount(param);
    }

    @Override
    public PageInfo<T> queryPageListByWhere(T param, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<T> list = this.queryListByWhere(param);
        return new PageInfo<>(list);

    }

    @Override
    public PageInfo<T> queryPageListByWhereDesc(Class<T> clazz, T param, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        Map<String, Boolean> order = new HashMap<>();
        order.put("id", false);
        List<T> list = this.queryByWhereAndOrderBy(clazz, order, param);
        return new PageInfo<>(list);

    }

    @Override
    public T queryOne(T param) {
        return this.baseMapper.selectOne(param);
    }

    @Override
    public Integer insert(T param) {
        return this.baseMapper.insert(param);
    }

    @Override
    public Integer insertSelective(T param) {
        return this.baseMapper.insertSelective(param);
    }

    @Override
    public Integer saveList(List<T> list) {

        if (list == null || list.isEmpty()) {
            return null;
        }

        return baseMapper.insertList(list);
    }

    @Override
    public Integer update(T param) {
        return this.baseMapper.updateByPrimaryKey(param);
    }

    @Override
    public Integer updateSelective(T param) {
        return this.baseMapper.updateByPrimaryKeySelective(param);
    }

    @Override
    public Integer updateByExample(T param, Example example) {
        return baseMapper.updateByExampleSelective(param, example);
    }

    @Override
    public Integer deleteById(Object id) {

        if (id == null) {
            return null;
        }

        return this.baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer delete(T param) {

        if (param == null) {
            return null;
        }

        return baseMapper.delete(param);
    }

    @Override
    public Integer deleteByIds(Class<T> clazz, List<Object> values) {

        if (values == null || values.isEmpty()) {
            return null;
        }

        Example example = new Example(clazz);
        example.createCriteria().andIn("id", values);
        return this.baseMapper.deleteByExample(example);
    }

    @Override
    public List<T> queryByWhereAndOrderBy(Class<T> clz, Map<String, Boolean> orderBy, Object where) {
        Example example = new Example(clz);
        StringBuilder sb = new StringBuilder();
        for (String s : orderBy.keySet()) {
            sb.append(s).append(" ").append(orderBy.get(s).booleanValue() ? "ASC" : "DESC").append(",");
        }
        example.setOrderByClause(sb.deleteCharAt(sb.length() - 1).toString());
        example.createCriteria().andEqualTo(where);
        return baseMapper.selectByExample(example);
    }

    @Override
    public List<T> queryByLike(Class<T> clz, Map<String, Object> like, Object where) {

        Example example = new Example(clz);
        Example.Criteria criteria = example.createCriteria();
        for (String s : like.keySet()) {
            criteria.andLike(s, "%" + like.get(s).toString().trim() + "%");
        }
        criteria.andEqualTo(where);
        return baseMapper.selectByExample(example);

    }

    @Override
    public PageInfo<T> queryByLike(Class<T> clz, Map<String, Object> like, Object where, Integer page, Integer rows) {
        Example example = new Example(clz);
        Example.Criteria criteria = example.createCriteria();
        for (String s : like.keySet()) {
            criteria.andLike(s, "%" + like.get(s).toString().trim() + "%");
        }
        criteria.andEqualTo(where);
        PageHelper.startPage(page, rows);
        List<T> ts = baseMapper.selectByExample(example);
        return new PageInfo<>(ts);
    }
}

