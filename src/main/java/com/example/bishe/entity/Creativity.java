package com.example.bishe.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author huaye
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Creativity对象", description="")
public class Creativity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer quantity;

    private String description;

    private String image;

    private String business;

    private Integer uid;

    private Integer sale;
}
