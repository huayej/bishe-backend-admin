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
 * @since 2023-02-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Sundry对象", description="")
public class Sundry implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String sundryName;

    private String sundryDescribe;

    private String sundryImage;

    private String sundryProv;

    private String sundryBirth;

    private String sundryFrom;

    private String sundryCountry;


}
