# hhm-门神项目
## 功能介绍
可灵活定义各个接口的限流时间和限流速率。可用于在大流量时，对不必要的接口进行限流，让出资源给核心流程。
或者可以限制大流量接口的速率，避免影响正常业务。
### 策略
提供以下策略：
1. 固定时间策略。如在2023-03-09 08:00:00 到 2023-03-09 10:00:00 进行大促活动，活动的主要相关路径/acti/goods/info,/acti/order/create。
那么可以把这两个路径限流设置为大一点，其它路径设置小一点，给主路径让出资源。
2. 定时策略（未实现）。例如某个接口需要在每天固定的时间进行限流，然后其它时间不需要限流。又或者某个接口只在每天的某个时间段可用，其它时间段不可用。
3. 流量监控策略（未实现），当某个接口流量超出某个阈值时，对某些接口进行限流。

相同时间内，若某个url命中多条策略，则以限流粒度最大的为准。



