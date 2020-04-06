package com.example.emmproject.core.bean.order;

/**
 * 说明：
 * 作者：
 * 添加时间：
 */
public interface Food {



    public String getFoodName() ;
    public void setFoodName(String foodName) ;

    public int getQuantity() ;

    public void setQuantity(int quantity);


    public double getOriginSum() ;

    public void setOriginSum(double originSum);

    public double getFinalSum();

    public void setFinalSum(double finalSum) ;

    public String getOptionInfo();

    public void setOptionInfo(String optionInfo);

    public String getMainImage();

    public void setMainImage(String mainImage);
}
