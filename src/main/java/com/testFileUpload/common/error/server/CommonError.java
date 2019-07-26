package com.testFileUpload.common.error.server;

/**
 * 通用性server异常枚举
 * @author CAIFUCHENG3
 */
public enum CommonError implements ServerError{
	// 未知异常
	UNEXPECTED,

	// 抱歉，目前网络出现不知名错误，可能连接用户过多，请稍候重新连接。
	UNRECOGNIZED_EXCEPTION,

	// 抱歉，目前网络繁忙，请稍候重新连接。（空指针）
	NULL_POINTER_EXCEPTION,

	// 抱歉，目前网络繁忙，请稍候重新连接。（类型转换失败）
	CLASS_CAST_EXCEPTION,

	// 抱歉，目前网络繁忙，请稍候重新连接。（数字格式化失败）
	NUMBER_FORMAT_EXCEPTION,

	// 抱歉，目前网络繁忙，请稍候重新连接。（文件没有找到）
	FILE_NOTFOUND_EXCEPTION,

	// 抱歉，目前网络繁忙，请稍候重新连接。（IO读取错）
	IOEXCEPTION,

	// 抱歉，目前网络繁忙，请稍候重新连接。（数组越界）
	ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION,

	// 抱歉，目前网络繁忙，请稍候重新连接。（索引越界）
	INDEX_OUT_OF_BOUNDS_EXCEPTION,

	// 抱歉，目前网络繁忙，请稍候重新连接。（找不到类）
	CLASS_NOT_FOUND_EXCEPTION,

	// 抱歉，目前网络繁忙，请稍候重新连接。（找不到方法）
	NO_SUCH_METHOD_EXCEPTION,

	// 抱歉，目前网络繁忙，请稍候重新连接。（安全异常）
	SECURITY_EXCEPTION,

	// 抱歉，目前网络繁忙，请稍候重新连接。（无对象）
	NO_SUCH_OBJECT_EXCEPTION,

	// 抱歉，目前网络繁忙，请稍候重新连接。（不支持的Encoding）
	UNSUPPORTED_ENCODING_EXCEPTION,

	// 抱歉，目前网络繁忙，请稍候重新连接。（网络连接异常）
	CONNECT_EXCEPTION,

	// 抱歉，目前网络繁忙，请稍候重新连接。（feign服务异常）
	NO_SUCH_BEAN_DEFINITION,

	//BEAN拷贝异常
	BEAN_COPY_ERROR,

	//唯一标识重复
	UNIQUE_IDENTITY_DUPLICATION,

	//缺失必要参数
	MISSINGKEY_NECESSARY_PARAMETERS,

	//存在模型实例，不能删除。
	EXIST_OBJ_DES_INSTANCE,

	//不能存在多个必校验项。
	CAN_NOT_HAVE_MULTIPLE_MANDATORY_ITEMS,

	//模型至少需要有一组的唯一项
	NEED_LEAST_ONE_UNIQUE_ITEMS,

	//模型属性已被分组唯一引用，删除失败
	ATTR_REFERENCED_BY_UNIQUE_CHECK,

	//改名字已经存在
	NAME_IS_EXISTED,

	//请先清空该分组下的字段
	REMOVE_ATTR_OF_GROUP,

	//文件过大
	FILE_IS_TOO_LARGE,

	//数据唯一性校验失败，唯一组合重复
	UNIQUE_REPEAT,

	//必要参数为空
	PARAM_IS_NULL,

	//查询结果为空
	RESULT_IS_NULL,

	//模型的关联关系已经被实例化，不能被删除
	OBJECT_ASSOCIATION_INSTANCED,

	//该实例已经被关联，不允许删除或归档
	INSTANCE_ASSOCIATED,

	//实例已存在冲突关联，请检查对应关系
	INSTANCE_ASSOCIATED_EXIST,

	//导出异常
	EXPORT_ERROR,

	//导入异常
	IMPORT_ERROR,

	//字段不存在
	FIELD_NOT_EXIST,
}
