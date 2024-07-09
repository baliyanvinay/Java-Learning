package Utilities;

public class ArrayUtil {
    public static byte[] concatenateByteArray(byte[] arrayOne, byte[] arrayTwo) {
        byte[] resultArray = new byte[arrayOne.length + arrayTwo.length];
        System.arraycopy(arrayOne, 0, resultArray, 0, arrayOne.length);
        System.arraycopy(arrayTwo, 0, resultArray, arrayOne.length, arrayTwo.length);
        return resultArray;
    }
}
