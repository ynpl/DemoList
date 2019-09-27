package com.neufmer.ygfstore.bean;

import java.util.List;

/**
 * Created by WangXing on 2019/7/9.
 */
public class InspectionsheetResultBean {

    private List<FailreasonsBean> failreasons;
    private List<InspectionQuestionsBean> inspectionQuestions;

    public List<FailreasonsBean> getFailreasons() {
        return failreasons;
    }

    public void setFailreasons(List<FailreasonsBean> failreasons) {
        this.failreasons = failreasons;
    }

    public List<InspectionQuestionsBean> getInspectionQuestions() {
        return inspectionQuestions;
    }

    public void setInspectionQuestions(List<InspectionQuestionsBean> inspectionQuestions) {
        this.inspectionQuestions = inspectionQuestions;
    }

    public static class FailreasonsBean {
        /**
         * groupId : 1
         * groupName : 原则问题
         * childs : [{"id":2,"name":"混合经营"},{"id":3,"name":"私自配置辅料"}]
         * grouName : 统一规范
         */

        private int groupId;
        private String groupName;
        private String grouName;
        private List<ChildsBean> childs;

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getGrouName() {
            return grouName;
        }

        public void setGrouName(String grouName) {
            this.grouName = grouName;
        }

        public List<ChildsBean> getChilds() {
            return childs;
        }

        public void setChilds(List<ChildsBean> childs) {
            this.childs = childs;
        }

        public static class ChildsBean {
            /**
             * id : 2
             * name : 混合经营
             */

            private int id;
            private String name;
            private int groupId;

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

            public int getGroupId() {
                return groupId;
            }

            public void setGroupId(int groupId) {
                this.groupId = groupId;
            }
        }
    }

    public static class InspectionQuestionsBean {
        /**
         * groupId : 1
         * groupName : 店面信息
         * details : [{"id":1,"type":"FillInBlank","isNeedAttachImage":false,"imageSizeMax":0,"isImageRequired":false,"isRequired":true,"question":{"title":"门店法人","description":"","text":"","style":[{"wordCount":20,"dataType":"char","isDate":false,"isTime":false}],"options":[{"option":"3代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"3.5代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"4代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"其他","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false}]}},{"id":2,"type":"FillInBlank","isNeedAttachImage":false,"imageSizeMax":0,"isImageRequired":false,"isRequired":true,"question":{"title":"客户编码","description":"","text":"","style":[{"wordCount":25,"dataType":"number","isDate":false,"isTime":false}],"options":[{"option":"3代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"3.5代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"4代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"其他","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false}]}},{"id":4,"type":"FillInBlank","isNeedAttachImage":false,"imageSizeMax":0,"isImageRequired":false,"isRequired":true,"question":{"title":"经营时间","description":"","text":"","style":[{"wordCount":100,"dataType":"char","isDate":true,"isTime":false}],"options":[{"option":"3代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"3.5代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"4代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"其他","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false}]}},{"id":6,"type":"SingleChoice","isNeedAttachImage":true,"imageSizeMax":10,"isImageRequired":false,"isRequired":true,"question":{"title":"装修风格","description":"","text":"","options":[{"option":"3代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"3.5代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"4代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"其他","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false}],"style":[{"wordCount":25,"dataType":"number","isDate":false,"isTime":false}]}}]
         */

        private int groupId;
        private String groupName;
        private List<DetailsBean> details;

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public static class DetailsBean {
            /**
             * id : 1
             * type : FillInBlank
             * isNeedAttachImage : false
             * imageSizeMax : 0
             * isImageRequired : false
             * isRequired : true
             * question : {"title":"门店法人","description":"","text":"","style":[{"wordCount":20,"dataType":"char","isDate":false,"isTime":false}],"options":[{"option":"3代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"3.5代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"4代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"其他","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false}]}
             */

            private int id;
            private String type;
            private boolean isNeedAttachImage;
            private int imageSizeMax;
            private boolean isImageRequired;
            private boolean isRequired;
            private boolean isNeedMultirow;
            private QuestionBean question;

            public boolean isNeedMultirow() {
                return isNeedMultirow;
            }

            public void setNeedMultirow(boolean needMultirow) {
                isNeedMultirow = needMultirow;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public boolean isIsNeedAttachImage() {
                return isNeedAttachImage;
            }

            public void setIsNeedAttachImage(boolean isNeedAttachImage) {
                this.isNeedAttachImage = isNeedAttachImage;
            }

            public int getImageSizeMax() {
                return imageSizeMax;
            }

            public void setImageSizeMax(int imageSizeMax) {
                this.imageSizeMax = imageSizeMax;
            }

            public boolean isIsImageRequired() {
                return isImageRequired;
            }

            public void setIsImageRequired(boolean isImageRequired) {
                this.isImageRequired = isImageRequired;
            }

            public boolean isIsRequired() {
                return isRequired;
            }

            public void setIsRequired(boolean isRequired) {
                this.isRequired = isRequired;
            }

            public QuestionBean getQuestion() {
                return question;
            }

            public void setQuestion(QuestionBean question) {
                this.question = question;
            }

            public static class QuestionBean {
                /**
                 * title : 门店法人
                 * description :
                 * text :
                 * style : [{"wordCount":20,"dataType":"char","isDate":false,"isTime":false}]
                 * options : [{"option":"3代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"3.5代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"4代店","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false},{"option":"其他","isNeedChoiceFailReason":false,"isFailReasonRequired":false,"isFailReasonMultiple":false,"isImageRequired":false}]
                 */

                private String title;
                private String description;
                private String text;
                private List<StyleBean> style;
                private List<OptionsBean> options;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public List<StyleBean> getStyle() {
                    return style;
                }

                public void setStyle(List<StyleBean> style) {
                    this.style = style;
                }

                public List<OptionsBean> getOptions() {
                    return options;
                }

                public void setOptions(List<OptionsBean> options) {
                    this.options = options;
                }

                public static class StyleBean {
                    /**
                     * wordCount : 20
                     * dataType : char
                     * isDate : false
                     * isTime : false
                     */

                    private int wordCount;
                    private String dataType;
                    private boolean isDate;
                    private boolean isTime;

                    public int getWordCount() {
                        return wordCount;
                    }

                    public void setWordCount(int wordCount) {
                        this.wordCount = wordCount;
                    }

                    public String getDataType() {
                        return dataType;
                    }

                    public void setDataType(String dataType) {
                        this.dataType = dataType;
                    }

                    public boolean isIsDate() {
                        return isDate;
                    }

                    public void setIsDate(boolean isDate) {
                        this.isDate = isDate;
                    }

                    public boolean isIsTime() {
                        return isTime;
                    }

                    public void setIsTime(boolean isTime) {
                        this.isTime = isTime;
                    }
                }

                public static class OptionsBean {
                    /**
                     * option : 3代店
                     * isNeedChoiceFailReason : false
                     * isFailReasonRequired : false
                     * isFailReasonMultiple : false
                     * isImageRequired : false
                     */

                    private String option;
                    private boolean isNeedChoiceFailReason;
                    private boolean isFailReasonRequired;
                    private boolean isFailReasonMultiple;
                    private boolean isImageRequired;

                    public String getOption() {
                        return option;
                    }

                    public void setOption(String option) {
                        this.option = option;
                    }

                    public boolean isIsNeedChoiceFailReason() {
                        return isNeedChoiceFailReason;
                    }

                    public void setIsNeedChoiceFailReason(boolean isNeedChoiceFailReason) {
                        this.isNeedChoiceFailReason = isNeedChoiceFailReason;
                    }

                    public boolean isIsFailReasonRequired() {
                        return isFailReasonRequired;
                    }

                    public void setIsFailReasonRequired(boolean isFailReasonRequired) {
                        this.isFailReasonRequired = isFailReasonRequired;
                    }

                    public boolean isIsFailReasonMultiple() {
                        return isFailReasonMultiple;
                    }

                    public void setIsFailReasonMultiple(boolean isFailReasonMultiple) {
                        this.isFailReasonMultiple = isFailReasonMultiple;
                    }

                    public boolean isIsImageRequired() {
                        return isImageRequired;
                    }

                    public void setIsImageRequired(boolean isImageRequired) {
                        this.isImageRequired = isImageRequired;
                    }
                }
            }
        }
    }

}
