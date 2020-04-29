package cloud.service.common.utils;

import cloud.service.common.validator.AbstractAssert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *  业务主键生成规则
 * @auther wentao.qiao
 * @date 2020/04/29.
 */
public class PrimaryKeyUtils {

    private static final int DEFAULT_NUM_COUNT = 6;
    private static final char[] NUMBERS = {'0','1','2','3','4','5','6','7','8','9'};
    private static final char[] STRINGS = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q',
            'R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i',
            'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private static final char[] MIX_NUM_STR = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q',
            'R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i',
            'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7',
            '8','9'};

    private static final ThreadLocal<DateFormat> THREAD_LOCAL = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmmssSSS"));

    /**
     *  获取业务主键 时间戳 + 四位随机数字或字母
     * @return      primaryKey
     */
    public static String getPrimaryKey(){
        StringBuilder sb = new StringBuilder();
        sb.append(getCurrentTime());
        sb.append(getRandomNumWithStr(4));
        return sb.toString();
    }

    /**
     *  根据指定前缀和指定数量后缀数字生成业务主键
     * @param prefix        指定前缀
     * @param numCount      指定数量数字后缀
     * @return  业务主键
     */
    public static String getNatureKey(String prefix,int numCount){
        AbstractAssert.isBlank(prefix,"业务前缀不能为空");
        return prefix + getTime() + getRandomNum(numCount);
    }

    /**
     *  根据指定前缀和指定数量后缀数字生成业务主键
     * @param prefix        指定前缀
     * @return  业务主键
     */
    public static String getNatureKey(String prefix){
        AbstractAssert.isBlank(prefix,"业务前缀不能为空");
        return getNatureKey(prefix,DEFAULT_NUM_COUNT);
    }

    /**
     *  获取当前时间格式化的毫秒值
     * @return
     */
    public static String getTime(){
        return THREAD_LOCAL.get().format(new Date());
    }

    /**
     *  获取当前时间戳毫秒
     * @return      当前毫秒数
     */
    public static Long getCurrentTime(){
        return System.currentTimeMillis();
    }

    /**
     *  获取指定数量的随机数字
     * @param num       指定数量
     * @return          随机数字
     */
    public static String getRandomNum(int num){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            sb.append(NUMBERS[random.nextInt(NUMBERS.length)]);
        }
        return sb.toString();
    }

    /**
     *  获取指定数量的随机字母，包含大小写
     * @param num       指定数量
     * @return          随机字母
     */
    public static String getRandomStr(int num){
        if(num <= 0){
            throw new IllegalArgumentException("Illegal num: " + num);
        }
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < num; i++){
            sb.append(STRINGS[random.nextInt(STRINGS.length)]);
        }
        return sb.toString();
    }

    /**
     *  获取指定数量的随机数字和字母的混合
     * @param num       指定数量
     * @return          随机数字和字母的混合
     */
    public static String getRandomNumWithStr(int num){
        if(num < 0){
            throw new IllegalArgumentException("Illegal num: " + num);
        }
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < num; i++){
            sb.append(MIX_NUM_STR[random.nextInt(MIX_NUM_STR.length)]);
        }
        return sb.toString();
    }

}
