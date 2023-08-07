package com.lcm.dao.base;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.lcm.utils.spring.PageEntity;
import com.lcm.utils.spring.PagingResult;

/** 
 * baseDAO的实现基类 
 */  
public class BaseDao<T, PK extends Serializable> extends SqlSessionDaoSupport implements IBaseDAO<T, PK> {  
	// mapper.xml中的namespace  
	private String namespace;  

	// sqlmap.xml定义文件中对应的sqlid  
	public static final String SQLID_INSERT = "insert";  
	public static final String SQLID_INSERT_BATCH = "insertBatch";  
	public static final String SQLID_UPDATE = "update";  
	public static final String SQLID_UPDATE_PARAM = "updateParam";  
	public static final String SQLID_UPDATE_BATCH = "updateBatch";  
	public static final String SQLID_DELETE = "delete";  
	public static final String SQLID_DELETE_PARAM = "deleteParam";  
	public static final String SQLID_DELETE_BATCH = "deleteBatch";  
	public static final String SQLID_TRUNCATE = "truncate";  
	public static final String SQLID_SELECT = "select";  
	public static final String SQLID_SELECT_PK = "selectPk";  
	public static final String SQLID_SELECT_PARAM = "selectParam";  
	public static final String SQLID_SELECT_FK = "selectFk";  
	public static final String SQLID_COUNT = "count";  
	public static final String SQLID_COUNT_PARAM = "countParam";  
	
	@Resource(name = "sqlSessionTemplate")  
	public void setSuperSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {  
		super.setSqlSessionTemplate(sqlSessionTemplate);  
	}  
	
	public String getNamespace() {  
		return namespace;  
	}  
	
	public void setNamespace(String namespace) {  
		this.namespace = namespace;  
	}  
	
	  
	public int insert(T entity) {  
		int rows = 0;  
		try {  
		    rows = getSqlSession().insert(getNamespace() + "." + SQLID_INSERT,  
		            entity);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  
		return rows;  
	}  
	
	  
	public int update(T entity) {  
		int rows = 0;  
		try {  
		    rows = getSqlSession().update(getNamespace() + "." + SQLID_UPDATE,  
		            entity);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  
		return rows;  
	}  
	
	  
	public int updateParam(Map param) {  
		int rows = 0;  
		try {  
		    getSqlSession().update(getNamespace() + "." + SQLID_UPDATE_PARAM,  
		            param);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  
		return rows;  
	}  
	
	  
	public int delete(PK primaryKey) {  
		int rows = 0;  
		try {  
		    rows = getSqlSession().delete(getNamespace() + "." + SQLID_DELETE,  
		            primaryKey);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  
		return rows;  
	}  
	
	  
	public int deleteParam(Map param) {  
		int rows = 0;  
		try {  
		    rows = getSqlSession().delete(getNamespace() + "." + SQLID_DELETE_PARAM,  
		            param);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  
		return rows;  
	}  
	
	  
	public int truncate() {  
		int rows = 0;  
		try {  
		    rows = getSqlSession().delete(getNamespace() + "." + SQLID_TRUNCATE);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  
		return rows;  
	}  
	
	  
	public int count() {  
		int result = 0;  
		try {  
		    result = getSqlSession().selectOne(getNamespace() + "." + SQLID_COUNT);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  
		return result;  
	}  
	
	  
	public int count(Object param) {  
		int result = 0;  
		try {  
		    result = getSqlSession().selectOne(getNamespace() + "." + SQLID_COUNT_PARAM,param);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  
		return result;  
	}  
	
	  
	public T get(PK primaryKey) {  
		try {  
		    return getSqlSession().selectOne(getNamespace() + "." + SQLID_SELECT_PK,primaryKey);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		    return null;  
		}  
	}  
	
	  
	public List<T> select() {  
		try {  
		    return getSqlSession().selectList(getNamespace() + "." + SQLID_SELECT);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		    return null;  
		}  
	  
	}  
	
	  
	public List<T> selectParam(Map param) {  
		try {  
		    return getSqlSession().selectList(getNamespace() + "." + SQLID_SELECT_PARAM,param);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		    return null;  
		}  
	}  
	
	@SuppressWarnings("unchecked")
	  
	public PagingResult<T> selectPagination(PageEntity pageEntity) {  
		try {  
		    int page = pageEntity.getPage() == null || "".equals(pageEntity.getPage()) ? 1 : pageEntity.getPage(); //默认为第一页  
		    int size = pageEntity.getSize() == null || "".equals(pageEntity.getSize()) ? 15 : pageEntity.getSize();; //默认每页15个  
		      
		    RowBounds rowBounds = new RowBounds((page-1)*size, size);  
		      
		    Map param = pageEntity.getParams();  
		    if (param != null) {  
		        param.put("orderColumn", pageEntity.getOrderColumn());  
		        param.put("orderTurn", pageEntity.getOrderTurn());  
		    }else {  
		        param = new HashMap();  
		        param.put("orderColumn", pageEntity.getOrderColumn());  
		        param.put("orderTurn", pageEntity.getOrderTurn());  
		    }  
		      
		    List<T> resultList = getSqlSession().selectList(getNamespace() + "." + SQLID_SELECT_PARAM,param,rowBounds);  
		    int totalSize = count(pageEntity.getParams());  
		      
		    PagingResult<T> pagingResult = new PagingResult<T>();  
		    pagingResult.setCurrentPage(page);  
		    pagingResult.setTotalSize(totalSize);  
		    pagingResult.setResultList(resultList);  
		    return pagingResult;  
		      
		} catch (Exception e) {  
		    e.printStackTrace();  
		    return null;  
		}  
	}  
	
	
	  
	public int insertBatch(List<T> list) {  
		try {  
		    return getSqlSession().insert(getNamespace() + "." + SQLID_INSERT_BATCH,list);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		    return 0;  
		}  
	}  
	
	  
	public int updateBatch(List<T> list) {  
		int rows = 0;  
		try {  
		    for (T t : list) {  
		        rows = rows + getSqlSession().update(getNamespace() + "." + SQLID_UPDATE, t);  
		    }  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  
		return rows;  
	
	}  
	
	  
	public int deleteBatch(List<PK> list) {  
		try {  
		    return getSqlSession().delete(getNamespace() + "." + SQLID_DELETE_BATCH,list);  
		} catch (Exception e) {  
		    e.printStackTrace();  
		    return 0;  
		}  
	
	}  
	
	/** 
	* 日志打印 
	* @param sqlId 
	* @param param 
	*/  
	public void printLog(String sqlId,Object param){  
		Configuration configuration = getSqlSession().getConfiguration();  
		//sqlId为配置文件中的sqlid  
		MappedStatement mappedStatement = configuration.getMappedStatement(sqlId);  
		//param为传入到sql语句中的参数  
		BoundSql boundSql = mappedStatement.getBoundSql(param);  
		//得到sql语句  
		String sql = boundSql.getSql().trim();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//		System.out.println("info-sql: "+sdf.format(new Date())+"  "+sql);  
	}  
}  
