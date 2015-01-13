package imgb.rulesystem.information.executioninfo;


import imgb.rulesystem.information.Information;

/**
 * Created by Daniel on 2014/7/26.
 */
public class ExecutionInfo extends Information {
    private final static String ERROR_INFO = "EXCUTED_EXCEPTION";
    private boolean beCorrectExcuted;

    public void addErrorInfo(Exception e) {
        super.setInfo("ERROR_INFO", e.getClass() + ":" + e.getMessage());
    }

    public boolean hasError() {
        return super.hasInfo("ERROR_INFO");
    }

    public String getErrorDescription() {
        return (String) (super.getInfo("ERROR_INFO"));
    }

}
