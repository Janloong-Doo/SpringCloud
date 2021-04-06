package com.janloong.common.validate;

/**
 * 参数校验规则分组（支持继承自由扩展）
 * <p>
 * 例如：
 * <pre>{@code
 * @NotBlank(groups = {ValidateParams.Update.class, ValidateParams.Save.class}, message = "该字段不能为空")
 * }</pre>
 *
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version V1.0
 * @since 2020-01-04 17:31
 **/
public class ValidateParams {

    /**
     * 保存操作
     */
    public interface Save {
    }

    /**
     * 更新操作
     */
    public interface Update {
    }

    /**
     * 查询操作
     */
    public interface query {
    }

    /**
     * 无操作
     */
    public interface None {
    }

}
