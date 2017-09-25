# TraceLog #

1. 支持传统日志打印
3. 支持日志的格式化输出
4. 支持日志的快速定位
5. 支持打印Json、XML、List、Map、Object等
2. 支持日志保存至本地
3. 支持一个App中有多个TraceLog实例
3. 支持扩展 (上传日志至服务器等)    

![](http://oqk78xit2.bkt.clouddn.com/Log_json_1.png)

## 添加依赖 ##
### Step 1. Add the JitPack repository to your build file ###
Add it in your root build.gradle at the end of repositories:  

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}  

### Step 2. Add the dependency ###

	dependencies {
		compile 'com.github.EthanCo:TraceLog:2.0.3'
		compile 'com.github.EthanCo.TraceLog:loggerex:2.0.3'
		compile 'com.github.EthanCo.TraceLog:logglobal:2.0.3'
	}

> TraceLog是核心库，必须依赖  
> loggerex是Logger扩展库，使用Looger来进行打印时，需要依赖该库  
> logglobal是全局打印扩展库，可通过L.java这个类全局使用日志

## 使用 ##

### 初始化

	TraceLog traceLog = new TraceLog.Builder()
        //.addTrace(TraceLog.defaultTrace()) //默认日志
        .setDefaultTag("DefaultTag")  //默认TAG
        .addTrace(new LoggerTrace()) //使用Logger进行日志输出
        //.addTrace(new DiskLogTrace(context)) //将日志保存至本地
        .build();

### 基础打印  

	traceLog.d("onCreate");
	traceLog.t(TAG).i("onCreate");

### 扩展打印
	
	traceLog.json(json)
	traceLog.json(xml)
	traceLog.i(list)
	traceLog.i(map)
	traceLog.i(object)

### 使用L.java便捷打印
要使用L.java全局便捷打印需要额外添加以下依赖  

	dependencies {
     	compile 'com.github.EthanCo.TraceLog:logglobal:2.0.3'
	}  

#### 初始化  

    TraceLog traceLog = new TraceLog.Builder()
            .addTrace(new LoggerTrace(2, 6))
            .build();
    L.init(traceLog);  

### 应用内全局可使用  

	L.i("hello world !");  

## 其他

> 感谢 [Looger](https://github.com/orhanobut/logger) | [LogUtils](https://github.com/pengwei1024/LogUtils) | [Timber](https://github.com/JakeWharton/timber)
