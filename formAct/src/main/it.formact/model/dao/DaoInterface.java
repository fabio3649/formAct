package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public interface DaoInterface<T> {
	
	public int doSave(T bean) throws SQLException;
	public boolean doDelete(int id) throws SQLException;
	public T doRetrieveByKey(int id) throws SQLException;
	public List<T> doRetrieveAll() throws SQLException;
	public boolean update(int id) throws SQLException;
    
}
