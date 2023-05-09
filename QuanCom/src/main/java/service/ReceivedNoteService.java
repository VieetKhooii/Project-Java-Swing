package service;

import bus.ReceivedNoteRepo;
import model.ReceivedNote;
import java.sql.Date;
import java.util.List;

public class ReceivedNoteService {
    public List<ReceivedNote> getAllReceiving(){
        ReceivedNoteRepo repo = new ReceivedNoteRepo();
        return repo.getAllReceiving();
    }

    public boolean addReceiving(int staffId, int supId, int totalPrice, Date date){
        ReceivedNoteRepo repo = new ReceivedNoteRepo();
        return repo.createReceivedNote(staffId, supId, totalPrice, date) >= 1;
    }

    public boolean deleteReceivedNote(int id){
        ReceivedNoteRepo repo = new ReceivedNoteRepo();
        return repo.deleteReceivedNote(id) >= 1;
    }

    public int getTotalPrice(int id){
        ReceivedNoteRepo repo = new ReceivedNoteRepo();
        return repo.getTotalPrice(id);
    }
    
    //Search list
    public List<ReceivedNote> getAllSearchResult(String searchTxt, String optSearch, String optSort, String priceFrom, String priceTo
    		,java.util.Date dateFrome, java.util.Date dateTo ){
    	ReceivedNoteRepo repo = new ReceivedNoteRepo();
        return repo.searchByOption(searchTxt, optSearch, optSort, priceFrom, priceTo, dateFrome, dateTo);
    }
}