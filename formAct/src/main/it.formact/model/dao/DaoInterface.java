package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;



public interface DaoInterface {
	
	public void doSave(Object bean) throws SQLException;
	public boolean doDelete(int id) throws SQLException;
	public Object doRetrieveByKey(int id) throws SQLException;
	public Object doRetrieveAll() throws SQLException;
    
}
