
import java.util.*;


public class StringEncodeDecode {
    private static final String str_reference_tbl = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
    private static final int str_reference_tbl_Len = str_reference_tbl.length();

    public StringEncodeDecode() {

    }

    public String encode(String plainText) {
        int len = plainText.length();

        char chbuf[] = new char[len - 1];

        char offsetchar = plainText.charAt(0);
        int offsetchar_tblindex = str_reference_tbl.indexOf(offsetchar);

        for (int i = 1; i < len; i++) {

            char currentchar = plainText.charAt(i);
            int currentchar_tblindex = str_reference_tbl.indexOf(currentchar);

            if (currentchar_tblindex == -1) {    //this character in plaintext is not found in reference table
                chbuf[i - 1] = currentchar;                //map back to same character
            } else {
                if (currentchar_tblindex - offsetchar_tblindex < 0) {
                    chbuf[i - 1] = str_reference_tbl.charAt(str_reference_tbl_Len - (offsetchar_tblindex - currentchar_tblindex));
                } else {
                    chbuf[i - 1] = (char) (currentchar_tblindex - offsetchar_tblindex + 'A');
                }
            }

        }
        String encodedStr = "" + offsetchar + new String(chbuf);
        return encodedStr;


    }

    public String decode(String encodedText) {

        int len = encodedText.length();

        char chbuf[] = new char[len - 1];

        char offsetchar = encodedText.charAt(0);
        int offsetchar_tblindex = str_reference_tbl.indexOf(offsetchar);

        for (int i = 1; i < len; i++) {

            char currentchar = encodedText.charAt(i);
            int currentchar_tblindex = str_reference_tbl.indexOf(currentchar);

            if (currentchar_tblindex == -1) {    //this character in plaintext is not found in reference table
                chbuf[i - 1] = currentchar;                //map back to same character
            } else {
                if (currentchar_tblindex + offsetchar_tblindex < str_reference_tbl_Len) {
                    chbuf[i - 1] = str_reference_tbl.charAt(currentchar_tblindex + offsetchar_tblindex);
                } else {
                    chbuf[i - 1] = str_reference_tbl.charAt(offsetchar_tblindex - (str_reference_tbl_Len - currentchar_tblindex));
                }

            }
        }

        String decodedStr = new String(chbuf);


        return decodedStr;

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StringEncodeDecode obj = new StringEncodeDecode();

        System.out.print("Enter plaintext string :");
        String plainText = scanner.nextLine();

        System.out.println("Enter offset character :");
        char offsetchar = scanner.nextLine().charAt(0);
        String offsetstr = "" + offsetchar;
        plainText = offsetstr + plainText;

        String EncodedStr = obj.encode(plainText);

        System.out.printf("Encoded string : %s %n", EncodedStr);

        String DecodedStr = obj.decode(EncodedStr);


        System.out.printf("Decoded string : %s %n", DecodedStr);


    }
}