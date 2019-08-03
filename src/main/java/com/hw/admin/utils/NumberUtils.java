package com.hw.admin.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {

    /**
     * 用于生成数字转换用到的字符，随机打乱不让随便猜出来
     * ===* 请不要随意修改下列字符列表 *===
     */
    private final static char[] SYMBOLS = {'b', 'P', 'x', '0', 's', 'X', 'd', 'E', 'q', 'W',
            'Z', 'G', 'I', 'g', 'B', 'w', 'i', '4', 'R', 'Q', 'y', '-', 'K', 'j', 'v', '8', '_',
            'e', 'H', 'c', '9', '2', 'n', 'u', 'Y', 'a', 't', 'J', 'C', '(', 'M', 'k', 'F', 'r',
            'A', 'p', '1', 'l', 'T', 'D', 'L', 'N', 'S', 'U', '7', 'z', 'V', '5', '6', 'f', 'm',
            'h', ')', '3'};

    /**
     * 多版本使用解密方式
     * 用于生成数字转换用到的字符，随机打乱不让随便猜出来
     * ===> 请不要随意修改下列字符列表 <===
     */
    private final static char[] SYMBOLS_MUCH = {'v', 'w', 'y', 'x', '2', '0', 'G', 'h', 'f', 'n', 'U',
            '-', 'z', '1', 'J', 'm', '_', 'A', 'F', 'C', '5', 'T', 'P', '(', 'E', 'H', 's', 'W', '6',
            '9', 'R', 'X', '8', 'b', 'N', 'I', 'Q', 'e', 'd', 'a', 'c', 'D', '7', ')', 'K', 'V', 'q',
            '3', 'l', 'p', 'S', 'k', 'Y', 'Z', '4', 'L', 'u', 'i', 'r', 't', 'B', 'g', 'M', 'j'};

    // 不足指定位数，补齐字符
    private static final char PAD_CHAR = 'o';
    /**
     * Null == value
     *
     * @param value 需要检查的数字
     * @return 检查的数据为 NULL
     */
    public static boolean isNull(Number value) {
        return null == value;
    }

    /**
     * Null != value
     *
     * @param value 需要检查的数字
     * @return 检查的数据不是 NULL
     */
    public static boolean isNotNull(Number value) {
        return null != value;
    }

    /**
     * Null != value && value > 0
     *
     * @param value
     * @return
     */
    public static boolean isPositive(Number value) {
        if (isNotNull(value)) {
            if (value instanceof Integer) {
                return value.intValue() > 0;
            } else if (value instanceof Long) {
                return value.longValue() > 0;
            } else if (value instanceof Byte) {
                return value.byteValue() > 0;
            } else if (value instanceof Double) {
                return value.doubleValue() > 0;
            } else if (value instanceof Float) {
                return value.floatValue() > 0;
            } else if (value instanceof Short) {
                return value.shortValue() > 0;
            }
        }
        return false;
    }

    /**
     * Null == value || value < 1
     *
     * @param value
     * @return
     */
    public static boolean isNotPositive(Number value) {
        if (isNull(value)) {
            return true;
        }
        if (value instanceof Integer) {
            return value.intValue() < 1;
        } else if (value instanceof Long) {
            return value.longValue() < 1;
        } else if (value instanceof Byte) {
            return value.byteValue() < 1;
        } else if (value instanceof Double) {
            return value.doubleValue() < 1;
        } else if (value instanceof Float) {
            return value.floatValue() < 1;
        } else if (value instanceof Short) {
            return value.shortValue() < 1;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(NumberUtils.compressTP(101L));
        System.out.println(NumberUtils.compressTP(102L));
        System.out.println(NumberUtils.compressTP(103L));
        System.out.println(NumberUtils.compressTP(104L));
        System.out.println(NumberUtils.compressTP(105L));
        System.out.println(NumberUtils.compressTP(135835465L));
        System.out.println(NumberUtils.compressTP(436959L));
    }
/***********************************************************************************************************************/
    /**
     * 将数字转换成64进制
     * 
     * @param number 数字
     * @return 64进制数字
     */
    public static String compressTP(Long number) {
        if (isPositive(number)) {
            return compressTP(number, 6);
        }
        return null;
    }
    // 不足指定位数，补齐字符
    private static final int DEFAULT_RADIX = 64;

    private static final double coefficient = 5.3;
    /**
     * 将数字转换成64进制
     * 
     * @param number 数字
     * @param length 长度
     * @return 64进制数字
     */
    public static String compressTP(long number, int length) {
        char[] buf = new char[DEFAULT_RADIX];
        int charPos = DEFAULT_RADIX;
        int radix = 1 << 6;
        long mask = radix - 1;
        do {
            buf[--charPos] = SYMBOLS[(int) (number & mask)];
            number >>>= 6;
        } while (number != 0);

        String results = new String(buf, charPos, (DEFAULT_RADIX - charPos));
        results = getRedundancyCode(results) + results;

        if (0 == length || Integer.MAX_VALUE == length) {
            return results;
        }
        return results.length() < length
                ? StringUtils.leftPad(results, length, PAD_CHAR)
                : results;
    }

    /**
     * 生成冗余验证码
     *
     * @param compressed 压缩过的数字
     * @return 验证码的值
     */
    private static char getRedundancyCode(String compressed) {
        compressed = compressed.toLowerCase();
        int sum = 0;
        for (int i = 0; i < compressed.length(); i++) {
            sum += compressed.charAt(i);
        }
        return SYMBOLS[sum % SYMBOLS.length];
    }
/********************************************平惠汇***************************************************************************/
    /**
     * 将64进制数转换成正常数字
     *
     * @param compressed 64进制数
     * @return 正常数字
     */
    public static long uncompress(String compressed) {
        compressed = compressed.trim();
        int padIndex = compressed.lastIndexOf(PAD_CHAR);
        char checkCode;
        try {
            if (padIndex < 0) {
                checkCode = compressed.charAt(0);
                compressed = compressed.substring(1);
            } else {
                checkCode = compressed.charAt(padIndex + 1);
                compressed = compressed.substring(padIndex + 2);
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("compressed number is not correct");
        }

        ConcurrentHashMap map = getRedundancyCodeMuch(checkCode, compressed);
        return uncompressValue(compressed, (char[]) map.get("symbols"));
    }


    /**
     * 兼容多版本 使用加密方式
     *
     * @param compressed
     * @param chr
     * @return
     */
    private static long uncompressValue(String compressed, char[] chr) {
        long result = 0;
        for (int i = compressed.length() - 1; i >= 0; i--) {
            for (int j = 0; j < chr.length; j++) {
                if (compressed.charAt(i) == chr[j]) {
                    result += ((long) j) << 6 * (compressed.length() - 1 - i);
                }
            }
        }
        return result;
    }



    /**
     * <p>  兼用多版本方式   </p>
     * 生成冗余验证码
     *
     * @param compressed 压缩过的数字
     * @return 验证码的值
     */
    private static ConcurrentHashMap getRedundancyCodeMuch(char checkCode, String compressed) {
        char car;
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("symbols", SYMBOLS);
        compressed = compressed.toLowerCase();
        int sum = 0;
        for (int i = 0; i < compressed.length(); i++) {
            sum += compressed.charAt(i);
        }
        car = SYMBOLS[sum % SYMBOLS.length];
        if (checkCode != car) {
            car = SYMBOLS_MUCH[sum % SYMBOLS_MUCH.length];
            concurrentHashMap.put("symbols", SYMBOLS_MUCH);
            if (checkCode != car) {
                throw new NumberFormatException("taiping check code does not match value={}" + compressed);
            }
        }
        return concurrentHashMap;
    }

}
