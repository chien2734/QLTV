/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.CardDAO;
import DTO.CardDTO;
import java.util.ArrayList;

/**
 *
 * @author HUY LONG
 */
public class CardBUS {

    private CardDAO cardDAO = new CardDAO();

    public ArrayList<CardDTO> getAllCards() {
        return cardDAO.getAllCards();
    }
    
    public ArrayList<CardDTO> getAllActivatedCards() {
        return cardDAO.getAllActivatedCards();
    }
    
    public String addCard(CardDTO card){
        if(cardDAO.addCard(card)) {
            return "Đã lưu thông tin thành công, vui lòng chờ thủ thư kích hoạt thẻ !";
        }
        
        return "Lưu thông tin không thành công, vì vậy thủ thư không thể kích hoạt thẻ được !";
    }
    
    public String activatedCard(CardDTO card){
        if(cardDAO.activatedCard(card)){
            return "Kích hoạt thành công !";
        }
        
        return "Kích hoạt thất bại !";
    }
    
    public CardDTO getCardsByIdMaDG(CardDTO t) {
        return cardDAO.selectByIdMaDG(t);
    }
    
    public CardDTO getCardsByIdTM(String mathe) {
        return cardDAO.selectByIdTM(mathe);
    }

    public ArrayList<CardDTO> getCardsBycondition(String condition) {
        return cardDAO.getCardsByCondition(condition);
    }
}
