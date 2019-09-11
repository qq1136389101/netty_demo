# netty_demo
netty 的简单实用

## 编写命令
### 1. 创建控制台指令
此处涉及的类都在 `com.chun.netty.console` 包下
1. 创建控制台指令处理器  
创建实现 `ConsoleCommand` 接口的类

2. 注入到工厂类中   
`ConsoleCommandVar` 类中定义控制台指令的常量  
`ConsoleCommandFactory` 类中的静态代码块中，将刚刚定义的控制台指令与第一步创建的命令处理器绑定

### 2. 创建请求响应包对象
1. 创建指令
`com.chun.netty.packet.command.var` 包下的 `CommandVar` 类中添加指令常量
2. 创建请求包对象
`com.chun.netty.packet.request` 包下创建继承 `Packet` 类的请求包对象
3. 创建响应包对象
`com.chun.netty.packet.response` 包下创建继承 `CommonResponse` 类的响应包对象
4. 将指令与请求包对象和响应包对象绑定
`com.chun.netty.packet.var` 包下的 `PacketVar` 类中将指令与请求包对象和响应包对象绑定

### 3. 创建请求处理器与响应处理器
1. 创建请求处理器
`com.chun.netty.handler.request` 包下创建继承 `SimpleChannelInboundHandler<刚刚创建的请求包对象>` 的请求处理器类
2. 创建响应处理器
`com.chun.netty.handler.response` 包下创建继承 `SimpleChannelInboundHandler<刚刚创建的响应包对象>` 的响应处理器类
3. 将处理器加入到客户端与服务端的 `pipeline` 中
服务端的 `handler` 中添加刚创建的请求处理器  
客户端的 `handler` 中添加刚创建的响应处理器


## 使用
### 运行服务端
直接运行 `com.chun.netty.Server` 的 `main` 方法

### 运行客户端
直接运行 `com.chun.netty.Client` 的 `main` 方法, 然后根据客户端的提示登录，再根据提示进行其他操作


