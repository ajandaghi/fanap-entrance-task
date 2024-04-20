package nationalcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.omg.CORBA.portable.ApplicationException;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class NationalCodeServiceTest {
  private static  NationalCode nationalCode=null;
  private static NationalCodeService nationalCodeService;
  private static List<NationalCode> trueNationalCodes;

  private static List<NationalCode> wrongNationalCodes;
@BeforeAll
public static void setMockAndObjects(){
    nationalCode=Mockito.mock(NationalCode.class);
    nationalCodeService=new NationalCodeService();
    trueNationalCodes= Arrays.asList(new NationalCode("0065860462"),new NationalCode("0014684810"),
            new NationalCode("0083034544"),new NationalCode("0480576531"),
            new NationalCode("0151561818"));

    wrongNationalCodes= Arrays.asList(new NationalCode("0065860412"),new NationalCode("0014684710"),
            new NationalCode("0083134544"),new NationalCode("0480576534"),
            new NationalCode("0151591818"));
}


    @Test
    public void checkValidateWithTrueNationalCode(){
    
      // Mockito.when(nationalCode.getValue()).thenReturn("0065860462");  //8999999999L*Math.random()+1000000000

        trueNationalCodes.stream().map(i->nationalCodeService.validateNationalCode(i)).forEach(Assertions::assertTrue);
    }

    @Test
    public void checkValidateDigitOfNationalCodeWithLetters(){
      Mockito.when(nationalCode.getValue()).thenReturn("a12345678");
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                nationalCodeService.validateDigitOfNationalCode(nationalCode);
        });


 }

   @Test
    public void checkValidateDigitOfNationalCodeWithNullAndEmpty(){
       Mockito.when(nationalCode.getValue()).thenReturn("");
       Assertions.assertEquals(nationalCodeService.validateDigitOfNationalCode(nationalCode),"");

       Mockito.when(nationalCode.getValue()).thenReturn(null);
       Assertions.assertEquals(nationalCodeService.validateDigitOfNationalCode(nationalCode),"");

   }

   @Test
    public void checkValidateDigitOfNationalCodeWithoutBeginningZero(){
       Mockito.when(nationalCode.getValue()).thenReturn("65860462");
       Assertions.assertEquals(nationalCodeService.validateDigitOfNationalCode(nationalCode),"0065860462");

       Mockito.when(nationalCode.getValue()).thenReturn("151561818");
       Assertions.assertEquals(nationalCodeService.validateDigitOfNationalCode(nationalCode),"0151561818");

   }

   @Test
    public void checkValidateDigitOfNationalCodeWithWrongNumberOfDigits() {
       Mockito.when(nationalCode.getValue()).thenReturn("4321");
       Assertions.assertEquals(nationalCodeService.validateDigitOfNationalCode(nationalCode), "");


       Mockito.when(nationalCode.getValue()).thenReturn("12345678910");
       Assertions.assertEquals(nationalCodeService.validateDigitOfNationalCode(nationalCode), "");
   }


   @Test
    public void checkGetStateCode(){
       Mockito.when(nationalCode.getValue()).thenReturn("0065860462");
       Assertions.assertEquals(nationalCodeService.getStateCode(nationalCode),"2");


   }

   @Test
    public void checkGetPersonUniqueCode(){
       Mockito.when(nationalCode.getValue()).thenReturn("0065860462");
       Assertions.assertEquals(nationalCodeService.getPersonUniqueCode(nationalCode),"586046");

   }

   @Test
    public void checkValidateWithWrongNationalCode(){
       wrongNationalCodes.stream().map(i->nationalCodeService.validateNationalCode(i)).forEach(Assertions::assertFalse);

   }

}