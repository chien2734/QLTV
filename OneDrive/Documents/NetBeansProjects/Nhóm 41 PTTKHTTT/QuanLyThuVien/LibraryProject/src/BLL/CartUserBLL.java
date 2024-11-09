/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.CartUserDAO;
import DTO.CartUserDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class CartUserBLL {
    public CartUserBLL() {
        
    }
    
    CartUserDAO cuDAL = new CartUserDAO();
    
    public ArrayList<CartUserDTO> getCartUserAll() {
        return cuDAL.selectAll();
    }
    
    public ArrayList<CartUserDTO> getCartUserByCondition(String condition) {
        return cuDAL.selectByCondition(condition);
    }
    
    public CartUserDTO getCartUserById(CartUserDTO t) {
        return cuDAL.selectById(t);
    }
    
    public int insertCartUser(CartUserDTO t) {
        return cuDAL.insert(t);
    }
    
    public int updateCartUser(CartUserDTO t) {
        return cuDAL.update(t);
    }
    
    public int deleteCartUser(CartUserDTO t) {
        return cuDAL.delete(t);
    }
    
    public int deleteCartUser1(CartUserDTO t) {
        return cuDAL.deleteSach(t);
    }
}
