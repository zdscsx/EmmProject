package com.example.emmproject.core.bean.order;

import android.os.AsyncTask;

import androidx.annotation.Nullable;

import com.example.emmproject.core.bean.ElemeGroupedItem;
import com.google.gson.Gson;

import java.util.List;

public class StoreFoodBean {

    /**
     * storeId : 3
     * storeInfo : {"storeId":3,"name":"公告","address":" 213","introduction":"..","phone":"12341342344","mainImage":"https://kpengidrive.com/charging-order/static/image/store/669192189731848192.png","open":true,"imageList":["https://kpengidrive.com/charging-order/static/image/store/669192189731848192.png"]}
     * foodList : [{"foodId":14,"storeId":3,"name":"日常防疫","introduction":"尽量避免出门，出门尽量带上口罩。不要聚集，不要接触生的家禽","mainImage":"https://kpengidrive.com/charging-order/static/image/food/669192189731848192.png","price":"0.00","stock":null,"status":1,"foodOption":null,"imageList":["https://kpengidrive.com/charging-order/static/image/food/674744774035423234.png"]},{"foodId":15,"storeId":3,"name":"取餐须知","introduction":"尽量避免出门，出门尽量带上口罩。不要聚集，不要接触生的家禽","mainImage":"https://kpengidrive.com/charging-order/static/image/food/669192189731848192.png","price":"0.00","stock":null,"status":1,"foodOption":null,"imageList":["https://kpengidrive.com/charging-order/static/image/food/669192189731848192.png"]},{"foodId":13,"storeId":3,"name":"取餐须知","introduction":"取餐后凭取餐码到充电站工作人员处取餐","mainImage":"https://kpengidrive.com/charging-order/static/image/food/669192189731848192.png","price":"0.00","stock":null,"status":1,"foodOption":null,"imageList":["https://kpengidrive.com/charging-order/static/image/food/674744774035423234.png"]}]
     */

    private int storeId;
    private StoreInfoBean storeInfo;
    private List<FoodListBean> foodList;

    public static StoreFoodBean objectFromData(String str) {

        return new Gson().fromJson(str, StoreFoodBean.class);
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public StoreInfoBean getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(StoreInfoBean storeInfo) {
        this.storeInfo = storeInfo;
    }

    public List<FoodListBean> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodListBean> foodList) {
        this.foodList = foodList;
    }

    public static class StoreInfoBean {
        /**
         * storeId : 3
         * name : 公告
         * address :  213
         * introduction : ..
         * phone : 12341342344
         * mainImage : https://kpengidrive.com/charging-order/static/image/store/669192189731848192.png
         * open : true
         * imageList : ["https://kpengidrive.com/charging-order/static/image/store/669192189731848192.png"]
         */

        private int storeId;
        private String name;
        private String address;
        private String introduction;
        private String phone;
        private String mainImage;
        private boolean open;
        private List<String> imageList;

        public static StoreInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, StoreInfoBean.class);
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMainImage() {
            return mainImage;
        }

        public void setMainImage(String mainImage) {
            this.mainImage = mainImage;
        }

        public boolean isOpen() {
            return open;
        }

        public void setOpen(boolean open) {
            this.open = open;
        }

        public List<String> getImageList() {
            return imageList;
        }

        public void setImageList(List<String> imageList) {
            this.imageList = imageList;
        }
    }

    public static class FoodListBean {

        /**
         * foodId : 22
         * storeId : 4
         * name : 牛肉炒饭
         * introduction : 好吃又实惠的牛肉炒饭
         * mainImage : https://kpengidrive.com/charging-order/static/image/food/679653809243209728.jpeg
         * price : 13.00
         * stock : null
         * status : 1
         * foodOption : {"optionNum":1,"optionList":[{"optionId":1,"optionName":"份量","require":true,"multiSelect":false,"selectionNum":2,"selections":[{"selectId":1,"selectName":"小份","price":13},{"selectId":2,"selectName":"大份","price":15}]}]}
         * imageList : ["https://kpengidrive.com/charging-order/static/image/food/679653809243209728.jpeg"]
         */

        private int foodId;

        private int storeId;
        private String name;
        private String introduction;
        private String mainImage;
        private String price;
        private Object stock;
        private int quantity;
        private int status;
        private FoodOptionBean foodOption;
        private List<String> imageList;

        public static FoodListBean objectFromData(String str) {

            return new Gson().fromJson(str, FoodListBean.class);
        }

        public int getFoodId() {
            return foodId;
        }

        public void setFoodId(int foodId) {
            this.foodId = foodId;
        }

        public int getStoreId() {
            return storeId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getMainImage() {
            return mainImage;
        }

        public void setMainImage(String mainImage) {
            this.mainImage = mainImage;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public Object getStock() {
            return stock;
        }

        public void setStock(Object stock) {
            this.stock = stock;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public FoodOptionBean getFoodOption() {
            return foodOption;
        }

        public void setFoodOption(FoodOptionBean foodOption) {
            this.foodOption = foodOption;
        }

        public List<String> getImageList() {
            return imageList;
        }

        public void setImageList(List<String> imageList) {
            this.imageList = imageList;
        }

        public static class FoodOptionBean {
            /**
             * optionNum : 1
             * optionList : [{"optionId":1,"optionName":"份量","require":true,"multiSelect":false,"selectionNum":2,"selections":[{"selectId":1,"selectName":"小份","price":13},{"selectId":2,"selectName":"大份","price":15}]}]
             */

            private int optionNum;
            private List<OptionListBean> optionList;

            public static FoodOptionBean objectFromData(String str) {

                return new Gson().fromJson(str, FoodOptionBean.class);
            }

            public int getOptionNum() {
                return optionNum;
            }

            public void setOptionNum(int optionNum) {
                this.optionNum = optionNum;
            }

            public List<OptionListBean> getOptionList() {
                return optionList;
            }

            public void setOptionList(List<OptionListBean> optionList) {
                this.optionList = optionList;
            }

            public static class OptionListBean {
                /**
                 * optionId : 1
                 * optionName : 份量
                 * require : true
                 * multiSelect : false
                 * selectionNum : 2
                 * selections : [{"selectId":1,"selectName":"小份","price":13},{"selectId":2,"selectName":"大份","price":15}]
                 */

                private int optionId;
                private String optionName;
                private boolean require;
                private boolean multiSelect;
                private int selectionNum;
                private List<SelectionsBean> selections;

                public static OptionListBean objectFromData(String str) {

                    return new Gson().fromJson(str, OptionListBean.class);
                }

                public int getOptionId() {
                    return optionId;
                }

                public void setOptionId(int optionId) {
                    this.optionId = optionId;
                }

                public String getOptionName() {
                    return optionName;
                }

                public void setOptionName(String optionName) {
                    this.optionName = optionName;
                }

                public boolean isRequire() {
                    return require;
                }

                public void setRequire(boolean require) {
                    this.require = require;
                }

                public boolean isMultiSelect() {
                    return multiSelect;
                }

                public void setMultiSelect(boolean multiSelect) {
                    this.multiSelect = multiSelect;
                }

                public int getSelectionNum() {
                    return selectionNum;
                }

                public void setSelectionNum(int selectionNum) {
                    this.selectionNum = selectionNum;
                }

                public List<SelectionsBean> getSelections() {
                    return selections;
                }

                public void setSelections(List<SelectionsBean> selections) {
                    this.selections = selections;
                }

                public static class SelectionsBean {

                    /**
                     * selectId : 1
                     * selectName : 小份
                     * price : 13.0
                     */

                    private int selectId;
                    private String selectName;
                    private double price;

                    public static SelectionsBean objectFromData(String str) {

                        return new Gson().fromJson(str, SelectionsBean.class);
                    }

                    public int getSelectId() {
                        return selectId;
                    }

                    public void setSelectId(int selectId) {
                        this.selectId = selectId;
                    }

                    public String getSelectName() {
                        return selectName;
                    }

                    public void setSelectName(String selectName) {
                        this.selectName = selectName;
                    }

                    public double getPrice() {
                        return price;
                    }

                    public void setPrice(double price) {
                        this.price = price;
                    }

                    @Override
                    public boolean equals(@Nullable Object obj) {
                        if (obj==this)
                            return true;
                        if (obj==null)
                            return false;
                        if (obj.getClass()!=getClass())
                            return false;
                        SelectionsBean item=(SelectionsBean) obj;
                        if (item.selectId!=getSelectId()||!item.selectName.equals(getSelectName()))
                            return false;
                        return true;

                    }
                }

            }


        }
    }
}
