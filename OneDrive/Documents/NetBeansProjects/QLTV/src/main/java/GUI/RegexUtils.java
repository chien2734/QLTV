package GUI;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RegexUtils {

    // Regex cho số điện thoại (10 chữ số)
    private static final String PHONE_NUMBER_REGEX = "^\\d{10}$";
    // Regex cho căn cước công dân (12 chữ số)
    private static final String CCCD_REGEX = "^\\d{12}$";

    // Kiểm tra số điện thoại hợp lệ
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return phoneNumber.matches(PHONE_NUMBER_REGEX);
    }

    // Kiểm tra căn cước công dân hợp lệ
    public static boolean isValidCCCD(String cccd) {
        if (cccd == null) {
            return false;
        }
        return cccd.matches(CCCD_REGEX);
    }
    
    public static void CheckRegex(String cccd, String sdt, JPanel root){
        if (!isValidPhoneNumber(sdt)) {
            JOptionPane.showMessageDialog(root, "Số điện thoại phải đúng 10 chữ số! VD: 0123456789", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        if (!isValidCCCD(cccd)) {
            JOptionPane.showMessageDialog(root, "CCCD phải đúng 12 chữ số! VD: 060204000564", "Error Message", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    // Hàm tiện ích kiểm tra chuỗi bất kỳ với regex bất kỳ
    public static boolean matchesRegex(String input, String regex) {
        if (input == null || regex == null) {
            return false;
        }
        return input.matches(regex);
    }
}


