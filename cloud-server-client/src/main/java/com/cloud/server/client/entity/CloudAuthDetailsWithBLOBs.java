package com.cloud.server.client.entity;

public class CloudAuthDetailsWithBLOBs extends CloudAuthDetails {
    /**
     * 用户请求报文
     */
    private String userReqJson;

    /**
     * 鉴权请求报文
     */
    private String authReqJson;

    /**
     * 鉴权响应报文
     */
    private String authResJson;

    /**
     * 用户请求报文
     * @return user_req_json 用户请求报文
     */
    public String getUserReqJson() {
        return userReqJson;
    }

    /**
     * 用户请求报文
     * @param userReqJson 用户请求报文
     */
    public void setUserReqJson(String userReqJson) {
        this.userReqJson = userReqJson == null ? null : userReqJson.trim();
    }

    /**
     * 鉴权请求报文
     * @return auth_req_json 鉴权请求报文
     */
    public String getAuthReqJson() {
        return authReqJson;
    }

    /**
     * 鉴权请求报文
     * @param authReqJson 鉴权请求报文
     */
    public void setAuthReqJson(String authReqJson) {
        this.authReqJson = authReqJson == null ? null : authReqJson.trim();
    }

    /**
     * 鉴权响应报文
     * @return auth_res_json 鉴权响应报文
     */
    public String getAuthResJson() {
        return authResJson;
    }

    /**
     * 鉴权响应报文
     * @param authResJson 鉴权响应报文
     */
    public void setAuthResJson(String authResJson) {
        this.authResJson = authResJson == null ? null : authResJson.trim();
    }
}