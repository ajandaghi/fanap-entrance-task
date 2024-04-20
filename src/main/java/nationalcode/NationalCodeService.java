package nationalcode;

import java.util.Arrays;

public class NationalCodeService implements NationalCodeBusiness{
    @Override
    public Boolean validateNationalCode(NationalCode nationalCode) {
        String code = validateDigitOfNationalCode(nationalCode);

        if (code.isEmpty()) {
           // throw new IllegalArgumentException("Insert national code with 10 digit");
            return false;
        }


        char[] ncChars = code.toCharArray();
        boolean allSame = true;
        for (int i = 0; i < 9; i++) {
            if (ncChars[i + 1] != ncChars[i]) {
                allSame = false;
                break;
            }

        }
        if(allSame){
          //  throw new IllegalArgumentException("Invalid NationalCode");
            return false;
        }
        int sum=0;
        for (int i = 8; i >= 0; i--) {
            sum += (10 - i) * Integer.parseInt(code.substring(i, i + 1));

        }
        if(sum%11<2){
            return sum % 11 == Integer.parseInt(getStateCode(nationalCode));
        }else{
            return (11 - sum % 11) == Integer.parseInt(getStateCode(nationalCode));
        }
    }

    public String validateDigitOfNationalCode(NationalCode nationalCode){
        //Regex Can Be Used Simply
       if( nationalCode.getValue() == null || nationalCode.getValue().isEmpty()){
           return "";
       }

        String code=nationalCode.getValue();

        try {
            Long checkInt = Long.parseLong(code);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid type input");
        }

        int digit=code.length();
        if(digit!=10 && digit!=9 && digit!=8 ){
           // throw new IllegalArgumentException("you inserted "+digit+"digit national code is 10 digit");
            return "";

        } else if(digit==8){
            code="00"+code;

        } else if(digit==9){
            code="0"+code;

        }

        return code;
    }
    @Override
    public String getStateCode(NationalCode nationalCode) {
        String code = validateDigitOfNationalCode(nationalCode);
        return code.substring(9);
    }

    @Override
    public String getPersonUniqueCode(NationalCode nationalCode) {
        String code = validateDigitOfNationalCode(nationalCode);
        return code.substring(3,9);    }
}
