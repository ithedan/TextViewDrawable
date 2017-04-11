####先看大众点评的购买须知
![大众.png](http://upload-images.jianshu.io/upload_images/3523210-e7971f5aa1049148.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
######如上图，需求在每条提示语句前加一个小圆点，我刚看到需求就想到用 android:drawableLeft 来做，可做完发现：当TextView内容为单行的时候是没有问题的，多行的时候，添加的这个drawableLeft就随着TextView高度而垂直居中了。
######一般解决办法嵌套布局解决，将(Textview)文字  与（ImageView）小圆点分离，这肯定是不符合布局优化。
######前面写过一篇文章[Android 优化布局（解决TextView drawableLeft/top/right布局中大小不可控的方法）](http://www.jianshu.com/p/d3027acf475a)主要针对TextView  drawable属性的大小进行了控制，这次在原来的基础上通过设置drawable. setBounds(int left, int top, int right, int bottom)位置进行了控制达到下面的效果：

![123.png](http://upload-images.jianshu.io/upload_images/3523210-ae55e5504b7aae6e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

######也可以达到图标大小可控的效果

![000.png](http://upload-images.jianshu.io/upload_images/3523210-0ad048c82ce9bad8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
#####下面看看使用方法
######step1:Add it in your root build.gradle at the end of repositories:
````
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
````
######step2:Add the dependency
````
dependencies {
	        compile 'com.github.ithedan:TextViewDrawable:v1.0'
	}
````
#####图标与第一行文字对齐 xml (app:isAliganCenter="false"默认是true)
````
 <com.hedan.textdrawablelibrary.TextViewDrawable
        android:id="@+id/text3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:drawableLeft="@drawable/dot_blue"
        android:drawablePadding="10dp"
        android:padding="10dp"
        android:text="孩童100-140CM半价，100CM以下收60元餐费。本团购使用时间为周一到周五的晚餐以及周末的午餐!每天限接待30份\n团购用户不可同时享受其他优惠"
        android:textColor="#000000"
        app:isAliganCenter="false"
        />
````
#####图标与第一行文字对齐 activity
````
TextViewDrawable tv4=(TextViewDrawable)findViewById(R.id.text4);
 tv4.setText("提供免费WIFI\n停车位收费标准：详情咨询商家");
````
#####图标大小 xml
````
 <com.hedan.textdrawablelibrary.TextViewDrawable
         android:padding="10dp"
         android:layout_width="match_parent"
         android:layout_height="55dp"
         android:background="#eeeeee"
         android:drawablePadding="10dp"
         android:drawableLeft="@drawable/icon_tab_home_checked"
         app:drawableLeftWidth="30dp"
         app:drawableLeftHeight="30dp"
         android:drawableRight="@drawable/iconfont_youjiantou"
         app:drawableRightWidth="15dp"
         app:drawableRightHeight="20dp"
         android:textColor="#000000"
         android:gravity="center_vertical"
         android:text="首页"
         android:textSize="20sp"
         />
````
####如有什么问题，敬请提出，十分感谢！希望越来越好，谢谢！如果喜欢，还请点击start，喜欢支持一下了，谢谢O(∩_∩)O~
