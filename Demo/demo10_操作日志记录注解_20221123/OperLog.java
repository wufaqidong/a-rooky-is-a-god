/**
 * 操作日志记录注解
 * 使用方法：
 * 使用：
 *
 * 在方法上加
 *
 * @OperLog(value = "菜单管理", desc = "修改", param = false, result = true)
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperLog {

  /**
   * 模块
   */
  String value();

  /**
   * 功能
   */
  String desc();

  /**
   * 是否记录请求参数
   */
  boolean param() default true;

  /**
   * 是否记录返回结果
   */
  boolean result() default false;

}
