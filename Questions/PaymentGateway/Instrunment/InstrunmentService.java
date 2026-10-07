package PaymentGateway.Instrunment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class InstrunmentService {
    // 1 user can have multiple instrunments

    public static Map<Integer, List<Instrunment>> userVsInstance = new HashMap<>();
    public abstract InstrunmentDO addInstrunment(InstrunmentDO instrunmentDO);
    public abstract List<InstrunmentDO> getInstrunmentsByUserID(int userID);

    // there can be bank specific logic to add a bank, there are diffrernt logics

}

class BankService extends InstrunmentService {

    @Override 
    public InstrunmentDO addInstrunment(InstrunmentDO instrunmentDO)
    {
        BankInstrunment bankInstrunment = new BankInstrunment();
        bankInstrunment.instrunmentID = new Random().nextInt(100-10)+10;
        bankInstrunment.bankAccountNumber = instrunmentDO.bankAccountNumber;
        bankInstrunment.ifscCode = instrunmentDO.ifsc;
        bankInstrunment.type = InstrunmentType.BANK;
        bankInstrunment.userID = instrunmentDO.userID;
    }
}
