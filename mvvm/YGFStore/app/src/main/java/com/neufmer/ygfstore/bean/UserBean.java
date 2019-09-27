package com.neufmer.ygfstore.bean;

import java.util.List;

/**
 * Created by WangXing on 2019/7/12.
 */
public class UserBean {
        /**
         * id : 1
         * code : 001
         * name : aa
         * mobile : 111
         * status : 1
         * isPrimary : true
         * createdAt : 2019-07-12T01:40:43.871Z
         * roles : [{"id":1,"name":"R1"},{"id":2,"name":"R2"}]
         * groups : [{"id":1,"name":"G1"},{"id":2,"name":"G2"}]
         */

        private int id;
        private String code;
        private String name;
        private String mobile;
        private int status;
        private boolean isPrimary;
        private String createdAt;
        private List<RolesBean> roles;
        private List<GroupsBean> groups;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public boolean isIsPrimary() {
            return isPrimary;
        }

        public void setIsPrimary(boolean isPrimary) {
            this.isPrimary = isPrimary;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public List<RolesBean> getRoles() {
            return roles;
        }

        public void setRoles(List<RolesBean> roles) {
            this.roles = roles;
        }

        public List<GroupsBean> getGroups() {
            return groups;
        }

        public void setGroups(List<GroupsBean> groups) {
            this.groups = groups;
        }

        public static class RolesBean {
            /**
             * id : 1
             * name : R1
             */

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

        public static class GroupsBean {
            /**
             * id : 1
             * name : G1
             */

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
