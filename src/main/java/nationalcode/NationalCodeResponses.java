package nationalcode;

import java.util.Date;

public class NationalCodeResponses {
    private String nationalCode;
    private Boolean isValid;
    private Date date;

    public NationalCodeResponses(String nationalCode, Boolean isValid, Date date) {
        this.nationalCode = nationalCode;
        this.isValid = isValid;
        this.date = date;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return " {" +
                "nationalCode: "+nationalCode+
                ", isValid=" + isValid +
                ", date=" + date +
                '}';
    }
}
