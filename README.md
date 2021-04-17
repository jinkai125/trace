### trace

	提供对全链路进行统一标记,提供通过dubbo和tomcat方式记录链路标识,标识记录支持SL4J和log4j的MDC方式记录,实现在请求进入到结束能够将完整链路进行串联

### 变更记录

| 版本 | 变更日期   | 变更内容                                                     |
| ---- | ---------- | ------------------------------------------------------------ |
| 1.0  | 2021-04-15 | 初始版本搭建,添加支持serlvet和dubbo两种方式集成,支持MDC方式日志记录 |
| 1.0  | 2021-04-17 | 添加Apache 2.0 LICENSE                                       |
| 1.0  | 2021-04-17 | 更新README                                                   |
| 1.0  | 2021-04-17 | 更新版本号,调整内部结构,修改tomcat为servlet，对DubboFilter和ServletFilter调整为使用系统加载配置方式 |

### 使用说明

```
引入类包-->servlet项目引入trace-filter-servlet,dubbo项目引入trace-filter-dubbo,若两个全部使用则全部引入
配置系统配置,配置系统配置 "trace.mdc.property"该配置为TraceId mdc方式记录的标识,通过此标识可在log4j或者sl4j使用配置项
dubbo项目引入后需要配置"trace.dubbo.property",该配置为拦截dubbo请求后提取dubbo内的属性字段
servlet项目支持自定义配置和默认配置两种,自定义配置通过初始化ServletTraceFilte时将自定义解析到进行处理,默认配置会读取"trace.servlet.property"系统变量获取到请求参数的key
```

