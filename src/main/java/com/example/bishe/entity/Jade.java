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
 * @since 2023-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Jade对象", description="")
public class Jade implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String jadeName;

    private String jadeDescribe;

    private String jadeImage;

    private String jadeProv;

    private String jadeBirth;

    private String jadeFrom;

    private String jadeCountry;


}
