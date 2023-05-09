package service;

import bus.DetailReceivingRepo;
import model.ReceivedNoteDetail;

import java.util.List;

public class DetailReceiveService {
    public List<ReceivedNoteDetail> getAll(){
        DetailReceivingRepo detail = new DetailReceivingRepo();
        return detail.getAllDetailedReceiving();
    }

    public boolean addDetailNote(int materialId, int receivedNoteId, String name, int amount, int price){
        DetailReceivingRepo detail = new DetailReceivingRepo();
        return detail.createDetailedReceivedNote(materialId, receivedNoteId, name, amount, price) >= 1;
    }

    public boolean deleteDetailNote(int id){
        DetailReceivingRepo detail = new DetailReceivingRepo();
        return detail.deleteDetailReceivedNote(id) >= 1;
    }
    
    
}