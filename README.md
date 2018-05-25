# BrowserToAppDemo
## 从浏览器打开本地应用

### 注意事项
- 通过设置 android:allowTaskReparenting=”true” 属性将处于浏览器任务栈中的 Activity，在本地应用启动时移动到自己的任务栈中，实现按HOME键启动应用能回到浏览器唤起的页面。
- 通过 Splash 页面做过渡，通过 Intent.FLAG_ACTIVITY_NEW_TASK 的方式启动浏览器唤起的页面，使得按返回键能接着显示本地应用中已打开的页面。
- 通过判断 Activity 的数量，决定是直接唤起页面，还是先唤起主界面再打开需要打开的界面，使得按返回键不至于直接从二级或者三级界面退出应用，以提高用户体验。

下面是设计流程图，按照这个设计流程，并且在 AndroidManifest.xml 中将 MainActivity 以及浏览器需要唤起的 Activity 设置 android:allowTaskReparenting=”true” 属性，你也可以实现知乎那样的浏览器唤起应用。
![](http://oe9xsykvg.bkt.clouddn.com/blog/zhuimengfb/%E6%B5%8F%E8%A7%88%E5%99%A8%E6%89%93%E5%BC%80%E5%BA%94%E7%94%A8.png)

### 鸣谢
- [如何优雅地从浏览器打开本地应用](http://blog.zhuimengfb.com/2017/04/30/%E5%A6%82%E4%BD%95%E4%BC%98%E9%9B%85%E5%9C%B0%E4%BB%8E%E6%B5%8F%E8%A7%88%E5%99%A8%E6%89%93%E5%BC%80%E6%9C%AC%E5%9C%B0%E5%BA%94%E7%94%A8/)
