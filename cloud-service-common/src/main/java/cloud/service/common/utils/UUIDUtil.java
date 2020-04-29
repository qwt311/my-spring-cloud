package cloud.service.common.utils;

import java.util.UUID;

/**
 * @Author wentao.qiao
 * @Date 2020/04/29  17:18
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获得32个长度的十六进制的UUID
     * @return UUID
     */
    public static String get32UUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[2]+idd[3]+idd[4];
    }

    /**
     * 获得16个长度的十六进制的UUID
     * @return UUID
     */
    public static String get16UUID(){

        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[2];
    }
}
