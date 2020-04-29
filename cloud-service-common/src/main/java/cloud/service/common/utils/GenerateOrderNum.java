package cloud.service.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 生成非重复订单号
 *
 * @author Administrator 2018-11-2 15:31:13
 */
public class GenerateOrderNum {
    /**
     * 锁对象，可以为任意对象
     */
    private static final Object LOCK_OBJ = "lockerOrder";
    /**
     * 订单号生成计数器
     */
    private static long orderNumCount = 0L;
    /**
     * 每毫秒生成订单号数量最大值
     */
    private static int maxPerMSECSize = 1000;
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    /**
     * 生成非重复订单号，理论上限1毫秒1000个，可扩展
     *
     * @param tname
     */
    public static synchronized String generate(String tname) {
        if (StringUtils.isEmpty(tname)) {
            tname = getItemID(3);
        }
        try {
            // 最终生成的订单号
            String finOrderNum;
            synchronized (LOCK_OBJ) {
                // 取系统当前时间作为订单号变量前半部分，精确到毫秒
                String nowLong = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                // 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万
                if (orderNumCount >= maxPerMSECSize) {
                    orderNumCount = 0L;
                }
                //组装订单号
                String countStr = maxPerMSECSize + orderNumCount + "";
                finOrderNum = nowLong + countStr.substring(1);
                orderNumCount++;
                return finOrderNum + tname;
            }
        } catch (Exception e) {
            return UUIDUtil.getUUID();
        }
    }

    public static String getItemID(int n) {
        StringBuilder val = new StringBuilder();
        for (int i = 0; i < n; i++) {
            boolean b = RANDOM.nextInt(2) % 2 == 0;
            if (b) {
                // 产生字母
                int nextInt = RANDOM.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (nextInt + RANDOM.nextInt(26)));
            } else {
                // 产生数字
                val.append(String.valueOf(RANDOM.nextInt(10)));
            }
        }
        return val.toString();
    }

}
