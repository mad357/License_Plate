public class Main {
    public static void main(String[] args) {
        System.out.println(licensePlate(-5));
        System.out.println(licensePlate(0));
        System.out.println(licensePlate(111));
        System.out.println(licensePlate(123456));
        System.out.println(licensePlate(1000000));
        System.out.println(licensePlate(1000001));
        System.out.println(licensePlate(1104567));
        System.out.println(licensePlate(3500000));
        System.out.println(licensePlate(3599999));
        System.out.println(licensePlate(3600000));
        System.out.println(licensePlate(3610000));
        System.out.println(licensePlate(3850000));
        System.out.println(licensePlate(3860000));
        System.out.println(licensePlate(4110023));
        System.out.println(licensePlate(4120023));
        System.out.println(licensePlate(10350000));
        System.out.println(licensePlate(10360000));
        System.out.println(licensePlate(27935000));
        System.out.println(licensePlate(27936000));
        System.out.println(licensePlate(73633500));
        System.out.println(licensePlate(73633600));
        System.out.println(licensePlate(192447350));
        System.out.println(licensePlate(192447360));
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
        if (i < 0) {
            return "-" + toAlphabetic(-i - 1);
        }

        int quot = i / letters().length();
        int rem = i % letters().length();
        char letter = (char) ((int) 'A' + rem);

        if (quot == 0) {
            return "" + letter;
        } else {
            return toAlphabetic(quot) + letter;
        }
    }
    public static int removeLeadingDigits(int number, int digitsToRemove) {
        if (number == 0) {
            return 0;
        }
        if (getNumberOfDigits(number) == digitsToRemove) {
            return number;
        }

        digitsToRemove = Math.min(digitsToRemove, getNumberOfDigits(number));
        int divisor = (int) Math.pow(numbers().length(), digitsToRemove);

        return number % divisor;
    }

    public static int getNumberOfDigits(int number) {
        return (int) Math.log10(number) + 1;
    }
    private static String licensePlate(int index) {
        if (index < 0) {
            return "Out of range";
        }
        String plate = "";
        int lettersNumber = 0;
        int maxAvailableValue = 0;
        for (int i = 1; i <= 7; i++) {
            maxAvailableValue += (Math.pow(numbers().length(), 7 - i) * Math.pow(letters().length(), i - 1));
            if (i < 7 && maxAvailableValue - 1 < index) {
                lettersNumber++;
            }
            else {
                break;
            }

        }
        if (maxAvailableValue - 1 < index) {
            return "Out of range";
        }
        for (int i = lettersNumber; i > 0; i--) {
            int temp = (int) (Math.pow(numbers().length(), 7 - i) * Math.pow(letters().length(), i - 1));
            index -= temp;
        }
        int decimalIndex = removeLeadingDigits(index, 6 - lettersNumber);
        index -= decimalIndex;
        index /= Math.pow(numbers().length(), 6 - lettersNumber);
        if (lettersNumber > 0) {
            plate = toAlphabetic(index);
        }
        while (plate.length() < lettersNumber) {
            plate = "A" + plate;
        }
        if (decimalIndex != 0) {
            plate = decimalIndex + plate;
        }
        while (plate.length() < 6) {
            plate = "0" + plate;
        }
        return plate;
    }
}