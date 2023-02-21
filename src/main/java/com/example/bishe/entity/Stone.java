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
@ApiModel(value="Stone对象", description="")
public class Stone implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String stoneName;

    private String stoneDescribe;

    private String stoneImage;

    private String stoneProv;

    private String stoneBirth;

    private String stoneFrom;

    private String stoneCountry;


}
