package cloud.service.common.enums;

/**
 *  spring cloud config 管理相关枚举
 */
public enum CloudConfigManageEnum {
    /**
     * 网关模块配置
     */
    GATEWAY_MODULE(1,"gateway-module","网关模块配置");

    private int code;

    private String name;

    private String desc;

    CloudConfigManageEnum(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    /**
     *  根据参数获取相关模块的初始化状态值
     * @param element
     * @return
     */
    public static int getStatusWithModuleEnum(CloudConfigManageEnum element) {
        if(element.name.equals(CloudConfigManageEnum.GATEWAY_MODULE.name)){
            //网关模块配置
            return GatewayProcessStatusEnum.GATEWAY_PROCESS_STATUS_READY.getStatus();
        }
        throw new IllegalArgumentException("错误的枚举参数");
    }

    /**
     *  根据模块名称和相应的状态值获取对应的描述
     * @param moduleName        模块名称
     * @param status            状态值
     * @return                  对应的状态描述
     */
    public static String getDescWithModuleNameAndStatus(String moduleName, int status){
        if(CloudConfigManageEnum.GATEWAY_MODULE.name.equals(moduleName)){
            //网关模块配置
            return GatewayProcessStatusEnum.getGatewayProcessDescWithStatus(status);
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    /**
     *  网关配置操作状态枚举
     */
    public enum GatewayProcessStatusEnum{
        /**
         *  在此状态下，可以新增、修改、删除路由，且同步完成后也即恢复到此状态
         */
        GATEWAY_PROCESS_STATUS_READY(0,"就绪状态"),
        /**
         *  在此状态下，可以刷新数据库中路由信息到redis 中
         *  恢复到状态，新增、修改、删除任意操作都会讲状态回归到这个状态
         */
        GATEWAY_PROCESS_STATUS_WAITING_REFRESH(1,"待刷新状态"),
        /**
         *  此状态下：会选择一个节点进行同步redis中路由信息到本地缓存
         */
        GATEWAY_PROCESS_STATUS_WAITING_VERIFY(2,"待验证状态"),
        /**
         * 此状态下：会同步所有的网关节点，更新redis中路由信息到本地缓存，操作成功后状态回归到就绪状态
         */
        GATEWAY_PROCESS_STATUS_WAITING_SYNCHRONIZE(3,"待同步状态");

        private int status;

        private String desc;

        GatewayProcessStatusEnum(int status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        public int getStatus() {
            return status;
        }

        public String getDesc() {
            return desc;
        }

        /**
         *  根据状态值获取对应的网关状态描述
         * @param status        状态值
         * @return              描述
         */
        public static String getGatewayProcessDescWithStatus(int status){
            GatewayProcessStatusEnum[] values = GatewayProcessStatusEnum.values();
            for(GatewayProcessStatusEnum element : values){
                if(element.status == status){
                    return element.getDesc();
                }
            }
            return null;
        }

    }
}