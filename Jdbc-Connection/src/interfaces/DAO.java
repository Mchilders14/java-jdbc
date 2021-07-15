package interfaces;

import java.util.List;

public interface DAO<T> {	// <T> Declares generic
	
	T get(int id);
	
	List<T> getAll();
	
	boolean add(T t);	// Uppercase is TYPE and lowercase is INSTANCE
	
	boolean update(T t);
	
	boolean delete(T t);
	

}
