package com.testFileUpload.common.page;


import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页请求对象
 * @author LiuXu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest<T> {

    /**
     *
     */
    private static final Long serialVersionUID = 1L;

    /**
     * 请求对象
     */
    @ApiModelProperty(value = "入参对象")
    T requestObj;

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页", required = true)
    private Integer pageNumber = 1;

    /**
     * 当前页面条数
     */
    @ApiModelProperty(value = "页码大小", required = true)
    private Integer pageSize = 10;

    public Page<T> getPage() {
        return new Page<>(pageNumber, pageSize);
    }
}
