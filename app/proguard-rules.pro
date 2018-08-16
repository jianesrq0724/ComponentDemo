# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in F:\AndroidSDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interfaces
# class:
#-keepclassmembers class fqcn.of.javascript.interfaces.for.webview {
#   public *;
#}
#指定代码的压缩级别
-optimizationpasses 5

#包明不混合大小写
-dontusemixedcaseclassnames

#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses

 #优化  不优化输入的类文件
-dontoptimize

 #预校验
-dontpreverify

 #混淆时是否记录日志
-verbose

 # 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#保护注解
-keepattributes *Annotation*

#抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable

#重命名抛出异常时的文件名称
-renamesourcefileattribute SourceFile

# 保持哪些类不被混淆
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
#如果有引用v4包可以添加下面这行
-keep public class * extends android.support.v4.app.Fragment

#忽略警告
-ignorewarning

##记录生成的日志数据,gradle build时在本项目根目录输出##
#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射
-printmapping mapping.txt

########记录生成的日志数据，gradle build时 在本项目根目录输出-end######

#####混淆保护自己项目的部分代码以及引用的第三方jar包library#######

#-libraryjars libs/umeng-analytics-v5.2.4.jar

#三星应用市场需要添加:sdk-v1.0.0.jar,look-v1.0.1.jar
#-libraryjars libs/sdk-v1.0.0.jar
#-libraryjars libs/look-v1.0.1.jar

#如果不想混淆 keep 掉
#-keep class com.lippi.recorder.iirfilterdesigner.** {*; }
#友盟
#-keep class com.umeng.**{*;}
#项目特殊处理代码

#忽略警告
-dontwarn com.lippi.recorder.utils**
#保留一个完整的包


#如果引用了v4或者v7包
-dontwarn android.support.**
-keep class org.xmlpull.v1.** { *; }

####混淆保护自己项目的部分代码以及引用的第三方jar包library-end####

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

#保持 jni调用不被混淆
-keep class com.your.jnicallback.class { *; }
-keep class com.chmtech.** { *; }
-keep class com.wintone.**{ *; }

#保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

#保持自定义控件类不被混淆
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable

#保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#保持枚举 enum 类不被混淆 如果混淆报错，建议直接使用上面的 -keepclassmembers class * implements java.io.Serializable即可
#-keepclassmembers enum * {
#  public static **[] values();
#  public static ** valueOf(java.lang.String);
#}

-keepclassmembers class * {
    public void *ButtonClicked(android.view.View);
}

#不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
}

#避免混淆泛型 如果混淆报错建议关掉
#–keepattributes Signature

# 保持测试相关的代码
-dontnote junit.framework.**
-dontnote junit.runner.**
-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**

#butterknife相关代码
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#导航
-keep class com.autonavi.**{*;}

#    2D地图
-keep class com.amap.api.maps2d.**{*;}
-keep class com.amap.api.mapcore2d.**{*;}

#    导航
-keep class com.amap.api.navi.**{*;}
-keep class com.autonavi.**{*;}
-keep class com.a.a.**  {*;}

#网络Rx
-dontwarn rx.**
-keep class rx.**{ *; }
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes Exceptions

#bean处理    //全部忽略 //继承网络框架的ResBase的对象
#-keep class com.ecaray.e_pda.berth_activity_batch_order.entity.**{*;}
#-keep class com.ecaray.e_pda.check.entity.**{*;}
#-keep class com.ecaray.e_pda.day_charge.entity.**{*;}
#-keep class com.ecaray.e_pda.login.entity.**{*;}
#-keep class com.ecaray.e_pda.check.presenter.**{*;}
-keep class * extends com.ecar.ecarnetwork.bean.ResBase
-keep class com.ecaray.e_pda.berth.entity.** { *;}
-keep class com.ecaray.e_pda.check.entity.** { *;}
-keep class com.ecaray.e_pda.day_charge.entity.** { *;}
-keep class com.ecaray.e_pda.login.entity.** { *;}
-keep class com.ecaray.e_pda.pub.entity.** { *;}

#访问网络Service
-keep class com.ecaray.e_pda.check.model.CheckService{*;}
-keep class com.ecaray.e_pda.berth.model.BerthService{*;}
-keep class com.ecaray.e_pda.berth.model.ChargeService{*;}
-keep class com.ecaray.e_pda.berth.model.LoginService{*;}

-keepclassmembers class * extends android.app.Activity

#如果用用到Gson解析包的，直接添加下面这几行就能成功混淆，不然会报错。
#gson
#-libraryjars libs/gson-2.2.2.jar
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

#loading
-keep class com.wang.avi.** { *; }
-keep class com.wang.avi.indicators.** { *; }

#litepal相关
-keep class org.litepal.** { *; }
-keep enum org.litepal.**
-keep interface org.litepal.** { *; }
-keep public class * extends org.litepal.**
-keepattributes *Annotation*
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keepclassmembers class * extends org.litepal.crud.DataSupport{*;}

#EcarBugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#引用打印lib
-dontwarn com.ecaray.printlib.**
-keep public class com.ecaray.printlib.entity.** {*;}
-keep public class com.ecaray.printlib.PrinterModule{*;}
-dontwarn android.device.**
-keep public class android.device.** {*;}
-dontwarn android.print.**
-keep public class android.print.** {*;}

#合肥联迪取消SDK混淆
-dontwarn com.landicorp.android.eptapi.**
-keep public class com.landicorp.android.eptapi.** {*;}
#瑞公引用SDK取消混淆
-dontwarn rego.printlib.**
-keep public class rego.printlib.** {*;}

#世麦科技SDK取消混淆
-dontwarn com.ecaray.basewinlib.**
-keep class com.ecaray.basewinlib.** {*;}
-keep class com.basewin.** {*;}
-dontwarn com.basewin.**

#思必拓SDK取消混淆
-dontwarn com.ecaray.cardlib.**
-keep class com.ecaray.cardlib.** {*;}
-keep class com.speedata.** {*;}
-dontwarn com.speedata.**
-keep class com.android.hflibs.** {*;}
-dontwarn com.android.hflibs.**

#刷卡
#西安
-dontwarn com.ab.util.**
-keep class com.ab.util.** {*;}
-dontwarn com.bouwa.**
-keep class com.bouwa.** {*;}
-dontwarn com.huierm.**
-keep class com.huierm.** {*;}

#commomlib
-dontwarn  com.ecar.epark.etestlib.**
-keep class com.ecar.epark.etestlib.** {*;}

#codetestlib
-dontwarn com.ecar.epark.commonlib.**
-keep class com.ecar.epark.commonlib.** {*;}

#evoicelib
-dontwarn com.ecar.epark.evoicelib.**
-keep class com.ecar.epark.evoicelib.** {*;}

#pushlib
-dontwarn com.ecar.pushlib.**
-keep class com.ecar.pushlib.** {*;}

#greenDaolib
-dontwarn com.ecar.pushlib.**
-keep class com.ecar.pushlib.** {*;}

### greenDAO 3
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties

# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
# If you do not use RxJava:
-dontwarn rx.**

### greenDAO 2
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
-keep class com.ecar.epark.greendaolib.*{ *; }

#rxbBus
-keepattributes *Annotation*
-keep @rxbus.ecaray.com.rxbuslib.rxbus.RxBusReact class * {*;}
-keep class * {
    @rxbus.ecaray.com.rxbuslib.rxbus.RxBusReact <fields>;
}
