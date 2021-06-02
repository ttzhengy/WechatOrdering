package com.yat.wechatorderingsystem.controller;

import com.yat.wechatorderingsystem.Util.ResultVOUtil;
import com.yat.wechatorderingsystem.VO.CategoryVO;
import com.yat.wechatorderingsystem.VO.ProductVO;
import com.yat.wechatorderingsystem.VO.ResultVO;
import com.yat.wechatorderingsystem.entity.ProductCategory;
import com.yat.wechatorderingsystem.entity.ProductInfo;
import com.yat.wechatorderingsystem.enums.ResultEnum;
import com.yat.wechatorderingsystem.exception.SellException;
import com.yat.wechatorderingsystem.service.CategoryService;
import com.yat.wechatorderingsystem.service.Impl.CategoryServiceImpl;
import com.yat.wechatorderingsystem.service.Impl.ProductServiceImpl;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/list")
    public ResultVO list(){
        // 查询所有上架商品
        List<ProductInfo> productOnSale = productService.selectAllOnSale();

        // 根据商品列表得到商品类目，再根据类目查询类目信息
        List<Integer> categoryTypeList = productOnSale.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        List<ProductCategory> productCategories = categoryService.selectByTypeIn(categoryTypeList);

        // 将数据拼装到视图对象中
        // 类目VO
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategories) {
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(productCategory,categoryVO);

            // 商品VO
            List<ProductVO> productVOList = new ArrayList<>();
            for (ProductInfo product : productOnSale) {
                // 当商品类目与类目相同，才添加到类目列表
                if (product.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductVO productVO = new ProductVO();
                    BeanUtils.copyProperties(product,productVO);
                    productVOList.add(productVO);
                }
            }
            categoryVO.setProductVOList(productVOList);
            categoryVOList.add(categoryVO);
        }

        return ResultVOUtil.success(categoryVOList);
    }
}
