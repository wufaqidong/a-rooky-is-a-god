/**
 * 操作日志记录
 *
 */
@Aspect
@Component
public class OperLogAspect {
  private ThreadLocal<Long> startTime = new ThreadLocal<>();
  @Autowired
  private OperRecordService operRecordService;

  @Pointcut("@annotation(com.egao.common.core.annotation.OperLog)")
  public void operLog() {
  }

  @Before("operLog()")
  public void doBefore(JoinPoint joinPoint) throws Throwable {
    startTime.set(System.currentTimeMillis());
  }

  @AfterReturning(pointcut = "operLog()", returning = "result")
  public void doAfterReturning(JoinPoint joinPoint, Object result) {
    saveLog(joinPoint, result, null);
  }

  @AfterThrowing(value = "operLog()", throwing = "e")
  public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
    saveLog(joinPoint, null, e);
  }

  private void saveLog(JoinPoint joinPoint, Object result, Exception e) {
    // 获取reques对象
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = (attributes == null ? null : attributes.getRequest());
    // 构建操作日志
    OperRecord operRecord = new OperRecord();
    operRecord.setUserId(getLoginUserId());
    if (startTime.get() != null) operRecord.setSpendTime(System.currentTimeMillis() - startTime.get());
    if (request != null) {
      operRecord.setRequestMethod(request.getMethod());
      operRecord.setUrl(request.getRequestURI());
      operRecord.setIp(UserAgentGetter.getIp(request));
    }
    // 记录异常信息
    if (e != null) {
      operRecord.setState(1);
      operRecord.setComments(StrUtil.sub(e.toString(), 0, 2000));
    }
    // 记录模块名、操作功能、请求方法、请求参数、返回结果
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    operRecord.setOperMethod(joinPoint.getTarget().getClass().getName() + "." + signature.getName());
    Method method = signature.getMethod();
    if (method != null) {
      OperLog operLog = method.getAnnotation(OperLog.class);
      if (operLog != null) {
        operRecord.setModel(operLog.value());
        operRecord.setDescription(operLog.desc());
        if (operLog.param() && request != null) {
          operRecord.setParam(StrUtil.sub(JSON.toJSONString(request.getParameterMap()), 0, 2000));
        }
        if (operLog.result() && result != null) {
          operRecord.setResult(StrUtil.sub(JSON.toJSONString(result), 0, 2000));
        }
      }
    }
    operRecordService.saveAsync(operRecord);
  }

  /**
   * 获取当前登录的userId
   */
  private Integer getLoginUserId() {
    Subject subject = SecurityUtils.getSubject();
    if (subject == null) return null;
    Object object = subject.getPrincipal();
    if (object instanceof User) return ((User) object).getUserId();
    return null;
  }

}
