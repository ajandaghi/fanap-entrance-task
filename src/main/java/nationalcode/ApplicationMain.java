package nationalcode;

import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import javax.xml.crypto.Data;
import java.util.*;

public class ApplicationMain {
    private static Scanner scanner = new Scanner(System.in);

    private static List<NationalCodeResponses> result=new ArrayList<>();


    public static void main(String[] args) {
        String in="";
        while(!in.equals("exit")) {
            System.out.println("enter national code:");
            in=scanner.nextLine();
            if(!in.equals("exit")) {
                try {
                    Long checkInt = Long.parseLong(in);

                    NationalCode nationalCode = new NationalCode(in);
                    NationalCodeService nationalCodeService = new NationalCodeService();
                    NationalCodeResponses nationalCodeResponses=new NationalCodeResponses(in,nationalCodeService.validateNationalCode(nationalCode),new Date());
                    System.out.println(nationalCodeService.validateNationalCode(nationalCode));

                    result.add(nationalCodeResponses);
                } catch (Exception e) {
                    System.out.println("Invalid type input"); //Suffocated Exception in frontend to continue
                                                               //for receiving next numbers
                }
            }


        }


           result.stream().sorted(Comparator.comparing(i->Long.parseLong(i.getNationalCode()),(i,j)->{
             return  i.compareTo(j);
           })).forEach(System.out::println);
    }
}
