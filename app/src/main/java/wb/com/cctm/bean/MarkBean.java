package wb.com.cctm.bean;

/**
 * Created by wb on 2018/4/29.
 */

public class MarkBean {
    String TOTAL_MONEY;
    String BUSINESS_COUNT;
    String BUSINESS_PRICE;
    String CREATE_TIME;
    String TRADE_ID;

    public String getTOTAL_MONEY() {
        return TOTAL_MONEY;
    }

    public void setTOTAL_MONEY(String TOTAL_MONEY) {
        this.TOTAL_MONEY = TOTAL_MONEY;
    }

    public String getBUSINESS_COUNT() {
        return BUSINESS_COUNT;
    }

    public void setBUSINESS_COUNT(String BUSINESS_COUNT) {
        this.BUSINESS_COUNT = BUSINESS_COUNT;
    }

    public String getBUSINESS_PRICE() {
        return BUSINESS_PRICE;
    }

    public void setBUSINESS_PRICE(String BUSINESS_PRICE) {
        this.BUSINESS_PRICE = BUSINESS_PRICE;
    }

    public String getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(String CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public String getTRADE_ID() {
        return TRADE_ID;
    }

    public void setTRADE_ID(String TRADE_ID) {
        this.TRADE_ID = TRADE_ID;
    }
}
