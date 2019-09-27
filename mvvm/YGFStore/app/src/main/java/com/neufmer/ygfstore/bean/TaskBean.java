package com.neufmer.ygfstore.bean;

import android.app.Application;

import com.wangxing.code.mvvm.utils.StringUtils;

import java.util.List;

/**
 * Created by WangXing on 2019/7/4.
 */
public class TaskBean {
    /**
     * id : 2
     * status : STARTED
     * startDate : 2019-06-30T16:00:00.000Z
     * finishDate : 2020-01-29T16:00:00.000Z
     * publishDate : 2019-06-19T16:00:00.000Z
     * targetCompleteTimes : 1
     * completedTimes : 0
     * targetStore : {"id":2,"legalRepresentative":"李江","code":"0111","address":{"country":"中国","admin1":"上海市","admin2":"黄浦区","street":"世纪大道128号"},"longitude":230.1,"latitude":100.2}
     * assignees : [{"id":2,"name":"Member B"}]
     * operator: { "id":1,"name": "Member A"}
     */

    private int id;
    private String status;
    private String startDate;
    private String finishDate;
    private String publishDate;
    private int targetCompleteTimes;
    private int completedTimes;
    private TargetStoreBean targetStore;
    private List<AssigneesBean> assignees;

    private OperatorBean operator;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }


    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }


    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }


    public int getTargetCompleteTimes() {
        return targetCompleteTimes;
    }

    public void setTargetCompleteTimes(int targetCompleteTimes) {
        this.targetCompleteTimes = targetCompleteTimes;
    }


    public int getCompletedTimes() {
        return completedTimes;
    }

    public void setCompletedTimes(int completedTimes) {
        this.completedTimes = completedTimes;
    }


    public TargetStoreBean getTargetStore() {
        return targetStore;
    }

    public void setTargetStore(TargetStoreBean targetStore) {
        this.targetStore = targetStore;
    }


    public List<AssigneesBean> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<AssigneesBean> assignees) {
        this.assignees = assignees;
    }


    public OperatorBean getOperator() {
        return operator;
    }

    public void setOperator(OperatorBean operator) {
        this.operator = operator;
    }


    public static class TargetStoreBean {
        /**
         * id : 2
         * legalRepresentative : 李江
         * code : 0111
         * address : {  "country":"中国",
         *              "admin1":"上海市",
         *              "admin2":"黄浦区",
         *              "street":"世纪大道128号"
         *           }
         * longitude : 230.1
         * latitude : 100.2
         */

        private int id;
        private String legalRepresentative;
        private String code;
        private AddressBean address;
        private double longitude;
        private double latitude;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLegalRepresentative() {
            return legalRepresentative;
        }

        public void setLegalRepresentative(String legalRepresentative) {
            this.legalRepresentative = legalRepresentative;
        }


        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }


        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }


        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }


        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }


        public static class AddressBean {
            /**
             * country : 中国
             * admin1 : 上海市
             * admin2 : 黄浦区
             * street : 世纪大道128号
             */

            private String country;
            private String admin1;
            private String admin2;
            private String admin3;
            private String admin4;
            private String street;


            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }


            public String getAdmin1() {
                return admin1;
            }

            public void setAdmin1(String admin1) {
                this.admin1 = admin1;
            }

            public String getAdmin2() {
                return admin2;
            }

            public void setAdmin2(String admin2) {
                this.admin2 = admin2;
            }


            public String getAdmin3() {
                return admin3;
            }

            public void setAdmin3(String admin3) {
                this.admin3 = admin3;
            }

            public String getAdmin4() {
                return admin4;
            }

            public void setAdmin4(String admin4) {
                this.admin4 = admin4;
            }


            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }


        }
    }

    public static class AssigneesBean {
        /**
         * id : 2
         * name : Member B
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


    public static class OperatorBean {
        /**
         * id : 1
         * name : Member A
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


    public String getAssigneesStr(){
        if(this.assignees == null || this.assignees.size() == 0){
            return "数据异常";
        }else{
            StringBuilder sb = new StringBuilder();
            for(AssigneesBean a : assignees){
                sb.append(a.name + "、");
            }
            String result = sb.toString();
            return result.substring(0,sb.toString().length());
        }
    }

    public String getTimeStr(){
        if(StringUtils.isEmpty(this.startDate) || StringUtils.isEmpty(this.finishDate) ){
            return "数据异常";
        }else{
            String result = startDate+"～"+finishDate;
            return result;
        }
    }

}
