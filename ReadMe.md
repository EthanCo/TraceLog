# TraceLog #


1. 传统日志打印
2. 支持日志保存至本地
3. 支持扩展 (上传日志至服务器等)  

> 未来将添加的功能  1.打印json字符串 2.快速定位 等  

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
	        compile 'com.github.EthanCo:TraceLog:1.0.1'
	}

### 使用 ###

TraceLog采用建造者模式，所有配置在Builder中进行配置即可。  

**初始化**

	TraceLog localLog = new TraceLog.Builder(App.getInstance()) //对于某些实现了IInit接口的ILog实现类，需要传入Context
	            .addLog(new DefaultLog())     //默认日志
	            .addLog(new LocalRecordLog()) //日志保存至本地文件
	            .setMaxFileCacheSize(1024 * 1024 * 10) //日志文件最大缓存，以B为单位
	            .setFolder("MyFolder") //日志保存文件夹，如果不设置，默认为TraceLog
	            .setFileName("MyFileName") //日志文件文件名，如果不设置，默认为TraceLog
	            .setDefaultTag("MyTag") //默认tag
	            .setEnable(BuildConfig.DEBUG) //是否启用
	            .build();

**打印**  

	localLog.i("MainActivity onCreate");

**如有多个TranceLog实现类，建议采用Factory，进行统一处理，并可实现快速替换** 

	//TraceLog newLog = TraceLogFactory.create(TraceLogFactory.Type.BUGLY);
    TraceLog newLog = TraceLogFactory.create(TraceLogFactory.Type.LOCAL);
    newLog.i("xxxxxxxxx"); 

**具体使用请查看Sample**  

