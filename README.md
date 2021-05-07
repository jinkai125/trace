### trace

	提供对全链路进行统一标记,提供通过dubbo和tomcat方式记录链路标识,标识记录支持SL4J和log4j的MDC方式记录,实现在请求进入到结束能够将完整链路进行串联

### 变更记录

| 版本  | 变更日期   | 变更内容                                                     |
| ----- | ---------- | ------------------------------------------------------------ |
| 0.0.1 | 2021-04-15 | 初始版本搭建,添加支持serlvet和dubbo两种方式集成,支持MDC方式日志记录 |
| 0.0.1 | 2021-04-17 | 添加Apache 2.0 LICENSE                                       |
| 0.0.1 | 2021-04-17 | 更新README                                                   |
| 0.0.1 | 2021-04-17 | 更新版本号,调整内部结构,修改tomcat为servlet，对DubboFilter和ServletFilter调整为使用系统加载配置方式 |
| 0.0.1 | 2021-04-19 | 修复MDC初始化 sl4j问题，更新组件trace-filter-tomcat为trace-filter-servlet,完善servlet用例和logback配置用例 |
| 0.0.1 | 2021-05-07 | 完善demo-servlet示例,添加log4j2示例,MDC支持使用基于SL4J完成,去除log4j+sl4j双重方式,filter-servlet,添加部分注释说明 |

### 配置说明

```
trace.servlet.property-->该配置用于在解析参数时提取TraceId使用,在初始化ServletTraceFilter时通过init完成初始化,若未设置初始化则依次通过环境变量,System.getProperty(ServletTraceFilter.PROPERTY_KEY)获取

trace.dubbo.property-->该配置用于dubbo解析TraceId和传递TraceId时使用,通过环境变量或者System.setProperty(DubboTraceIdConstants.PROPERTY_KEY, "${属性名称}")设置

trace.mdc.property-->该配置用于作为log配置文件内的TraceId配置使用,可通过环境变量和System.setProperty(MDCTraceId.PROPERTY_KEY, "${属性名称}")设置,设置的该名称为logback或者log4j2等配置文件内的标识

TraceId:默认的标识,若未设置以上参数,在各个场景下分别取该参数读取配置
```

