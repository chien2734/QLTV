/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sqlite;

import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface DAOInterface<T> {
    public abstract int insert(T t);
    
    public abstract int update(T t);
    
    public abstract int delete(T t);
    
    public abstract ArrayList<T> selectAll();
    
    public abstract T selectById(T t);
    
    public ArrayList<T> selectByCondition(String condition);
}
