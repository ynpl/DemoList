package com.neufmer.ygfstore.bean;

/**
 * Created by WangXing on 2019/6/13.
 */
public class LoginBean {


    private String token;
    private RoleClass role;
    private int id;
    private Number expire;
    private boolean isPrimary;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RoleClass getRole() {
        return role;
    }

    public void setRole(RoleClass role) {
        this.role = role;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Number getExpire() {
        return expire;
    }

    public void setExpire(Number expire) {
        this.expire = expire;
    }

    public static class RoleClass{
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
