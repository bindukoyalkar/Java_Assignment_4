import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GruberHealthcareKYCFormCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=Integer.parseInt(sc.nextLine());
        int i;
        List<String> list=new ArrayList<>();
        for(i=0;i<n;i++){
            String[] inp = sc.nextLine().split(" ");
            list.add(checkValidateDates(inp));
        }
        System.out.println();
        for(String r:list)
            System.out.println(r);
    }

    //returns validate range of dates to fill up form
    public static String checkValidateDates(String []inp){
        LocalDate signUpDate = toDateFormat(inp[0]);
        LocalDate presentDate = toDateFormat(inp[1]);
        if(signUpDate.isAfter(presentDate)) {
            return "No range";
        }
        signUpDate=signUpDate.withYear(presentDate.getYear());
        LocalDate minusThirty=signUpDate.minusDays(30L);
        LocalDate plusThirty=signUpDate.plusDays(30L);
        String str;
        str=dayMonthYearFormatter(minusThirty);
        str+=" ";
        if(plusThirty.isAfter(presentDate))
            str+=dayMonthYearFormatter(presentDate);
        else
            str+=dayMonthYearFormatter(plusThirty);
        return str;
    }

    //convert from string to LocalDate format
    public static LocalDate toDateFormat(String s){
        String[] strSplit =s.split("-");
        int dayOfMonth;
        int month;
        int year;
        dayOfMonth=Integer.parseInt(strSplit[0]);
        month=Integer.parseInt(strSplit[1]);
        year=Integer.parseInt(strSplit[2]);
        return LocalDate.of(year,month,dayOfMonth);
    }

    //convert from LocalDate to "dd-MM-yyyy" format string
    public static String dayMonthYearFormatter(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return formatter.format(date).toString();
    }
}
