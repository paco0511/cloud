package com.deepexi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.domain.eo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by donh on 2018/7/24.
 */
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> selectPageVo(@Param("price") Integer price);
}
