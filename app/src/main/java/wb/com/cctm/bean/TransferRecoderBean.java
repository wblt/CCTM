package wb.com.cctm.bean;

/**
 * Created by wb on 2018/4/24.
 */

public class TransferRecoderBean {
    String W_ADDRESS;
    String SEND_MONEY;
    String ID;
    String CREATE_TIME;

    public String getW_ADDRESS() {
        return W_ADDRESS;
    }

    public void setW_ADDRESS(String w_ADDRESS) {
        W_ADDRESS = w_ADDRESS;
    }

    public String getSEND_MONEY() {
        return SEND_MONEY;
    }

    public void setSEND_MONEY(String SEND_MONEY) {
        this.SEND_MONEY = SEND_MONEY;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(String CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }
}
