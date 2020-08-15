#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.base;

import lombok.Data;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.io.Serializable;

/**
 * 通用Mapper
 * @author XanderYe
 * @date 2019-01-18
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
