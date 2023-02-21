package com.example.bishe.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author huaye
 * @since 2023-02-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Pottery对象", description="")
public class Pottery implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String potteryName;

    private String potteryDescribe;

    private String potteryImage;

    private String potteryProv;

    private String potteryBirth;

    private String potteryFrom;

    private String potteryCountry;
}
