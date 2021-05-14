# 源码学习

客户端->服务端->服务端后台管理系统

## 服务端后台管理系统设计
## apollo-portal 是管理后台，所以从代码实现上，和业务系统非常相像。

在 Apollo 的架构中，一个环境( Env ) 对应一套 Admin Service 和 Config Service 。
而 Portal Service 会管理所有环境( Env ) 。因此，每次创建 App 后，需要进行同步。

### 1.Apollo 源码解析 —— Portal 创建 App
### 2.Apollo 源码解析 —— Portal 创建 Cluster
### 3.Apollo 源码解析 —— Portal 创建 Namespace
### 4.Apollo 源码解析 —— Portal 关联 Namespace
### 5.Apollo 源码解析 —— Portal 创建 Item
### 6.Apollo 源码解析 —— Portal 批量变更 Item
### 7.Apollo 源码解析 —— Admin Service 锁定 Namespace


### 问题
我们知道，但凡涉及跨系统的同步，无可避免会有事务的问题，对于 App 创建也会碰到这样的问题，例如：
Portal 在同步 App 到 Admin Service 时，发生网络异常，同步失败。那么此时会出现该 App 存在于 Portal ，却不存在于 Admin Service 中。
新增了一套环境( Env ) ，也会导致 Portal 和 Admin Service 不一致的情况。
那么 Apollo 是怎么解决这个问题的呢？感兴趣的胖友，可以先自己翻翻源码。嘿嘿。


## 服务端设计（核心）

### 1.配置发布后的实时推送设计
在配置中心中，一个重要的功能就是配置发布后实时推送到客户端。下面我们简要看一下这块是怎么设计实现的。
![img](./doc/images/release-message-notification-design.png)

### 2.发送ReleaseMessage的实现方式
Admin Service在配置发布后，需要通知所有的Config Service有配置发布，从而Config Service可以通知对应的客户端来拉取最新的配置。
从概念上来看，这是一个典型的消息使用场景，Admin Service作为producer发出消息，各个Config Service作为consumer消费消息。
通过一个消息组件（Message Queue）就能很好的实现Admin Service和Config Service的解耦。

在实现上，考虑到Apollo的实际使用场景，以及为了尽可能减少外部依赖，我们没有采用外部的消息中间件，而是通过数据库实现了一个简单的消息队列。

## 参考
https://github.com/ctripcorp/apollo/wiki