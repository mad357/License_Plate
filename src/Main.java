public class Main {
    public static void main(String[] args) {
        System.out.println(licensePlate(0));
        System.out.println(licensePlate(-5));
        System.out.println(licensePlate(111));
        System.out.println(licensePlate(123456));
        System.out.println(licensePlate(1204567));
        System.out.println(licensePlate(3500000));
        System.out.println(licensePlate(3599999));
        System.out.println(licensePlate(3600000));
        System.out.println(licensePlate(3860000));
        System.out.println(licensePlate(4119999));
        System.out.println(licensePlate(4120000));
        System.out.println(licensePlate(10350000));
        System.out.println(licensePlate(10360000));
        System.out.println(licensePlate(190360000));
        System.out.println(licensePlate(192447359));
        System.out.println(licensePlate(192447360));
        System.out.println(licensePlate(392447360));
        System.out.println(licensePlate(501363135));
        System.out.println(licensePlate(501363136));
        System.out.println(licensePlate(999999999));
    }

    public static String numbers() {
        return "0123456789";
    }
    public static String letters() {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }

    public static String toAlphabetic(int i) {
        int quot = i/26;
        int rem = i%26;
        char letter = (char)((int)'A' + rem);
        if( quot == 0 ) {
            return ""+letter;
        } else {
            return toAlphabetic(quot) + letter;
        }
    }

    private static String licensePlate(long index) {
        if (index < 0) {
            return "Out of range";
        }
        String plate = "";
        int lettersNumber = 0;
        int lettersDecimalValue = 0;

        for (int i = 6; i > 0; i--) {
            if (Math.pow(numbers().length(), i) <= index) {
                lettersDecimalValue = 0;
                index -= Math.pow(numbers().length(), i);
                lettersNumber++;
                for (int j = 0; j < Math.pow(letters().length(), lettersNumber) - 1; j++) {
                    if (Math.pow(numbers().length(), i-1) <= index) {
                        index -= Math.pow(numbers().length(), i-1);
                        lettersDecimalValue++;
                    }
                    else {
                        break;
                    }
                }
            }
            else {
                break;
            }
        }
        if (lettersNumber > 0) {
            plate = toAlphabetic(lettersDecimalValue);
        }

        while (plate.length() < lettersNumber) {
            plate = "A" + plate;
        }
        if (index > 0) {
            plate = index + plate;
        }
        if (plate.length() > 6) {
            return "Out of range";
        }
        while (plate.length() < 6) {
            plate = "0" + plate;
        }
            return plate;
        }
}