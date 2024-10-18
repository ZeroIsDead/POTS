/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JONATHAN
 */
public class POCollection extends ItemCollection{

    @Override
    public Object create(List Details) {
        PO newPO = new PO();
        newPO.setDetail(Details);
        return newPO;
    }

    @Override
    public Object search(String ID) {
        List<PO> POList = this.getAll();
        for (PO  : POList) {
            if (true) {
                return Item;
            }
        }
        return new PO();
    }
    
    
    public Object
        public List<Type> sort (String Field) {
        List<Type> ItemList = this.getAll();
        List<Type> SortedItemList = new ArrayList<>();
        Type FirstItem = ItemList.removeFirst();
        SortedItemList.add(FirstItem);
                
                
        for (Type currentItem : ItemList) {
            List<String> currentPODetail;
            currentPODetail = currentItem.getDetail();
            int WantedFieldIndex = currentItem.getFieldName().indexOf(Field);
            for (int i = 0; i < SortedPOList.size(); i++) {
                PO CurrentSortedPO = SortedPOList.get(i);
                String FieldValueOfCurrentSortedPO = CurrentSortedPO.getDetail().get(WantedFieldIndex);
                String FieldValueOfCurrentPO = currentPODetail.get(WantedFieldIndex);
                
//                Sorting
                if (FieldValueOfCurrentPO.compareTo(FieldValueOfCurrentSortedPO) < 0) {
                    SortedPOList.add(i, currentPO);
                }
            }
        }
        
        
        return SortedPOList;
    }
    
}
