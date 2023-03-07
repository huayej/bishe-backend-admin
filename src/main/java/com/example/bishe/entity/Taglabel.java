package com.example.bishe.entity;

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
 * @since 2023-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Taglabel对象", description="")
public class Taglabel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String prop;


}
