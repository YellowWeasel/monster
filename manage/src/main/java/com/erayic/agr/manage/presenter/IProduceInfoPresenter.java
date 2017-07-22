package com.erayic.agr.manage.presenter;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：产品详情
 */

public interface IProduceInfoPresenter {

    /**
     * 得到产品详情
     */
    void getProductDetail(String proID);

    /**
     * 更新产品信息
     */
    void updateProduct(String proID, String productName, int classicID, String descript);

    /**
     * 删除一个产品
     */
    void deleteProduct(String proID);

    /**
     * 更新产品标题图片
     */
    void updateProductIcon(String proID, String icon);

    /**
     * 增加一个产品照片
     */
    void addProductPhoto(String proID, String photo);

    /**
     * 删除一个产品照片
     */
    void delProductPhoto(String imgID, String imgPath);

}
