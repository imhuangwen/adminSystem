package com.hw.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel implements Serializable, Cloneable {
    /**
     * 逻辑主键
     */
    protected Integer id;

    /**
     * 创建时间
     */
    protected Date createdTime;

    /**
     * 最后修改时间
     */
    protected Date updatedTime;
}
