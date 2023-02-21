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
@ApiModel(value="Relic对象", description="")
public class Relic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String relicName;

    private String relicDescribe;

    private String relicImage;

    private String relicProv;

    private String relicBirth;

    private String relicFrom;

    private String relicCountry;


}
