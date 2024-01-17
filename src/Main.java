public class Main {
    public static void main(String[] args) {
        System.out.println(licensePlate(111));
        System.out.println(licensePlate(123456));
        System.out.println(licensePlate(1234567));
        System.out.println(licensePlate(3615111));
        System.out.println(licensePlate(36151110));
    }

    public static String numbers() {
        return "0123456789";
    }
    public static String letters() {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }

    public static String addZeros(int count) {
        String result = "";
        for (int i = 0; i < count; i++) {
            result += "0";
        }

        return result;
    }
    public static int countDigits(long number) {
        return String.valueOf(number).length();
    }

    private static String licensePlate(long index) {
        if (index < 0) {
            return "Out of range";
        }
        String plate = "";
        long currentLetterIndex = index;

        for(int i = 5; i >= 0 ; i--) {
            if (currentLetterIndex < Math.pow(numbers().length(), i + 1)) {
                plate = addZeros(6 - plate.length() - countDigits(currentLetterIndex) )  + currentLetterIndex  + plate;
                break;
            }
            else {
                currentLetterIndex -= Math.pow(numbers().length(), i+1);
                int currentIndex = (int) (currentLetterIndex / Math.pow(numbers().length(), i ));
                if (currentIndex > letters().length() -1 ) {
                    currentIndex = 25;
                }
                plate = letters().charAt(currentIndex) + plate;
                currentLetterIndex -= currentIndex * Math.pow(numbers().length(), i );
                if (currentLetterIndex > 0 && i == 0) {
                    return "Out of range";
                }
            }
        }

        return plate;
    }


}