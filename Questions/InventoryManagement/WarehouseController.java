package Questions.InventoryManagement;

import java.util.List;

public class WarehouseController {
    List<Warehouse> warehouseList;
    WarehouseSelectionStrategy warehouseSelectionStrategy = null;

    WarehouseController(List<Warehouse> warehouseList, WarehouseSelectionStrategy warehouseSelectionStrategy)
    {
        this.warehouseList = warehouseList;
        this.warehouseSelectionStrategy = warehouseSelectionStrategy;
    }

    void add_warehouse(Warehouse warehouse)
    {
        warehouseList.add(warehouse);
    }

    void remove_warehouse(Warehouse warehouse)
    {
       warehouseList.remove(warehouse);   
    }
    public Warehouse selectWarehouse(WarehouseSelectionStrategy selectionStrategy)
    {
        this.warehouseSelectionStrategy = selectionStrategy;
        return warehouseSelectionStrategy.selectWarehouse(warehouseList);
    }
}
