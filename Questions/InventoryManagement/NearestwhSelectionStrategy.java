package Questions.InventoryManagement;


import java.util.List;
public class NearestwhSelectionStrategy extends WarehouseSelectionStrategy {
    @Override 
    public Warehouse selectWarehouse(List<Warehouse> warehouseList)
    {
        return warehouseList.get(0);
    }
}
