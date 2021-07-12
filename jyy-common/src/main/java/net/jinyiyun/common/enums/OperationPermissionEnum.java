package net.jinyiyun.common.enums;

/**
 * 操作权限 枚举
 *
 * @author dongshixiao
 */
public enum OperationPermissionEnum {
    // 列表
    LIST("list"),
    // 增加
    ADD("add"),
    // 编辑
    EDIT("edit"),
    //删除
    DELETE("delete"),
    // 查看
    SHOW("show"),
    // 拷贝
    COPY("copy");


    private String operation;

    OperationPermissionEnum(String operation) {
        this.operation = operation;
    }


    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
