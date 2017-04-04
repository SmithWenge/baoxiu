package nanqu.djtu.pojo;

/**
 * Created by Administrator on 2017/4/4.
 */
public class Printer {

    private String printerId;
    private String printerZHCNName;
    private String printerNumber;
    private String printIp;

    public String getHiddenPrintIp() {
        return hiddenPrintIp;
    }

    public void setHiddenPrintIp(String hiddenPrintIp) {
        this.hiddenPrintIp = hiddenPrintIp;
    }

    private String hiddenPrintIp;

    public String getHiddenPrinterNumber() {
        return hiddenPrinterNumber;
    }

    public void setHiddenPrinterNumber(String hiddenPrinterNumber) {
        this.hiddenPrinterNumber = hiddenPrinterNumber;
    }

    private String hiddenPrinterNumber;

    public String getPrinterNumber() {
        return printerNumber;
    }

    public void setPrinterNumber(String printerNumber) {
        this.printerNumber = printerNumber;
    }

    public String getPrinterZHCNName() {
        return printerZHCNName;
    }

    public void setPrinterZHCNName(String printerZHCNName) {
        this.printerZHCNName = printerZHCNName;
    }

    public String getPrinterId() {
        return printerId;
    }

    public void setPrinterId(String printerId) {
        this.printerId = printerId;
    }

    public String getPrintIp() {
        return printIp;
    }

    public void setPrintIp(String printIp) {
        this.printIp = printIp;
    }




}
