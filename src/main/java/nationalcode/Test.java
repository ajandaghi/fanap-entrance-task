package nationalcode;

public class Test {
    public static void main(String[] args) {
        NationalCode nationalCode=new NationalCode("0065860462");
        NationalCodeService nationalCodeService =new NationalCodeService();
        System.out.println(nationalCodeService.getPersonUniqueCode(nationalCode));
        System.out.println(nationalCodeService.validateNationalCode(nationalCode));

    }
}
