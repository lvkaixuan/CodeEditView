# CodeEditView
### 效果图
<img src="https://github.com/lvkaixuan/CodeEditView/blob/master/04BB29B5-929C-474F-BDCB-0147F475FB94.png" width="60%">

### 动态图
![Alt text](https://github.com/lvkaixuan/CodeEditView/blob/master/QQ20180906-101622.gif)

### 使用方法
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

```
dependencies {
	implementation 'com.github.lvkaixuan:CodeEditView:v1.0.3'
}
```
布局中使用控件
```
<com.lkx.library.CodeEditView
  android:id="@+id/codeEditView"
  android:gravity="center_horizontal"
  android:layout_width="match_parent"
  android:layout_height="wrap_content"/>
```
代码中的回调监听
```
  codeEditView.setOnInputEndCallBack(new CodeEditView.inputEndListener() {
      @Override
      public void input(String text) { //输入完毕后的回调
           Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
      }

      @Override
      public void afterTextChanged(String text) { //输入内容改变后的回调
          textView.setText(text);
      }
 });
 
   //清除输入框内容 (v1.0.2新增方法)
   codeEditView.clearText();
   //新增长按粘贴 (v1.0.3新增)
```
### 高级使用(自定义)
```
<com.lkx.library.CodeEditView
  android:id="@+id/codeEditView1"
  android:gravity="center_horizontal"
  android:layout_width="match_parent"
  app:textColor="@color/colorAccent"
  app:borderNum="4"
  app:borderMargin="20"
  app:borderSize="48"
  app:textSize="10"
  android:layout_height="wrap_content"/>
```
```
app:textColor=@color/colorAccent //字体颜色
app:borderNum=4 //输入框个数
app:borderMargin=20 //输入框间距
app:borderSize=48 //输入框大小
app:textSize=10 //字体大小"
```

### Demo地址

 - CSDN博客地址: https://blog.csdn.net/lvkaixuan/article/details/82455366
 - 扫码下载apk
![这里写图片描述](https://github.com/lvkaixuan/CodeEditView/blob/master/scan.png)
 - 如果项目对你有帮助,欢迎star
