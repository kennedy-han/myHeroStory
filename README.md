# myHeroStory

•如果想要连接自己的服务器调试代码的话，

•前端测试地址：

（v1)•[http://](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[cdn0001.afrxvk.cn](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[/](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[hero_story](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[/demo/](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[step010](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[/](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[index.html?serverAddr](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[=](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[127.0.0.1:12345&userId](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[=1](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)

（v2解决移动玩家不显示问题，修改了protobuf协议)•[http://](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[cdn0001.afrxvk.cn](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[/](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[hero_story](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[/demo/](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[step020](http://cdn0001.afrxvk.cn/hero_story/demo/step020/index.html?serverAddr=127.0.0.1:12345&userId=1)[/](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[index.html?serverAddr](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[=](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[127.0.0.1:12345&userId](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[=1](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)

（v3添加登录功能)•[http://](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[cdn0001.afrxvk.cn](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[/](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[hero_story](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[/demo/](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[step030](http://cdn0001.afrxvk.cn/hero_story/demo/step030/index.html?serverAddr=127.0.0.1:12345&userId=1)[/](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[index.html?serverAddr](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[=](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[127.0.0.1:12345&userId](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)[=1](http://cdn0001.afrxvk.cn/hero_story/demo/step010/index.html?serverAddr=127.0.0.1:12345&userId=1)

****

***命令行工具 protobuf***

下载地址（选择对应的OS）•[https://](https://github.com/protocolbuffers/protobuf)[github.com](https://github.com/protocolbuffers/protobuf)[/](https://github.com/protocolbuffers/protobuf)[protocolbuffers](https://github.com/protocolbuffers/protobuf)[/](https://github.com/protocolbuffers/protobuf)[protobuf](https://github.com/protocolbuffers/protobuf)

目前使用版本：protoc-3.13.0-rc-3-win64.zip



### 使用Protobuf生成Java代码

•protoc.exe --java_out=${目标目录} .\GameMsgProtocol.proto

例子：protoc.exe --java_out=.\src\main\java .\GameMsgProtocol.proto



关于 **Protobuf** 

•syntax = “proto3”;

•消息命名规则是 XxxCmd 和 XxxResult；

•XxxCmd 代表客户端发往服务器的消息；

•XxxResult 代表服务器返回给客户端的消息；

•UserEntryCmd；

•UserEntryResult；

