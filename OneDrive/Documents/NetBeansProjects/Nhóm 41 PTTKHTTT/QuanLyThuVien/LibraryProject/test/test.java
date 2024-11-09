
import BLL.CT_PhieuTraBLL;
import BLL.PhieuMuonBLL;
import BLL.PhieuTraBLL;
import DTO.PhieuMuonDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

public class test {
    private static PhieuMuonBLL pmBLL = new PhieuMuonBLL();
    private static PhieuTraBLL ptBLL = new PhieuTraBLL();
    private static int numPT;
     public static void main(String[] args) throws ParseException {
          /*int tongTien = 0;
          Calendar currentDate = Calendar.getInstance();
          Date ngaytra = new Date(currentDate.getTimeInMillis());
          Date hantra = new Date();
          
          if (ngaytra.after(hantra)) {            
                long millisecondsLate = hantra.getTime() - ngaytra.getTime();
                long ngayTre = millisecondsLate / (24 * 60 * 60 * 1000);
                
                tongTien += ngayTre*5000; 
          } 
          System.out.println(tongTien);
          System.out.println(ngaytra);
          System.out.println(hantra);*/
          
//          int tongTien = 0;
//          Calendar currentDate = Calendar.getInstance();
//          PhieuMuonDTO pm = pmBLL.getBorrowById(new PhieuMuonDTO("PM0004"));
//            Date hantra = pm.getHanTra();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            String ngayTra = dateFormat.format(currentDate.getTimeInMillis());
//            Date ngaytra = java.sql.Date.valueOf(ngayTra);
//            long ngayTre =0;
//            if (hantra != null) {
//                if (ngaytra.after(hantra)) {            
//                    long millisecondsLate = ngaytra.getTime() - hantra.getTime();
//                    ngayTre = millisecondsLate / (24 * 60 * 60 * 1000);
//                    tongTien += ngayTre*5000; 
//                } 
//            }
//          
//            System.out.println(ngaytra);
//            System.out.println(hantra);
//            System.out.println(tongTien);
//            System.out.println(ngayTre);

        
//        numPT = ptBLL.selectAll().size();
//        System.out.println(++numPT);
        
        
            
        /*  Date dueDate = new Date(2024, 5, 10); // Hạn trả (năm: 2024, tháng: 5, ngày: 10)
        Date returnDate = new Date(2024, 5, 12); // Ngày trả (năm: 2024, tháng: 5, ngày: 12)
        
        if (returnDate.before(dueDate)) {
            System.out.println("Ngày trả đúng hạn!");
        } else if (returnDate.equals(dueDate)) {
            System.out.println("Ngày trả đúng hạn!");
        } else {
            long millisecondsLate = returnDate.getTime() - dueDate.getTime();
            long daysLate = millisecondsLate / (24 * 60 * 60 * 1000);
            System.out.println("Ngày trả trễ " + daysLate + " ngày.");
            tongTien += daysLate*5000; 
        }
//        Calendar currentDate = Calendar.getInstance();
//        Date ngaytra = new Date(currentDate.getTimeInMillis());
        System.out.println(dueDate);
        System.out.println(tongTien);
        String date = "2024-05-06";
        System.out.println(java.sql.Date.valueOf(date));
        */
    }
}
