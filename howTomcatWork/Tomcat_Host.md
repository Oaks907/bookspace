Context的父容器通常是Host容器，也有可能可能是其他实现，或者如果不必要，就可以不使用父容器。



## Host

```java
public interface Host extends Container {
    // ----------------------------------------------------- Manifest Constants
    //Alias Evnet Type
    public static final String ADD_ALIAS_EVENT = "addAlias";
    public static final String REMOVE_ALIAS_EVENT = "removeAlias";


    // ------------------------------------------------------------- Properties
    //返回此主机的应用程序根目录。这可以是绝对路径名、相对路径名或URL。
    public String getAppBase();
    public void setAppBase(String appBase);
    //返回AutoDebug部署标志的值。如果为真，则表明该主机的子WebApp应该被发现并自动部署。
    public boolean getAutoDeploy();
    public void setAutoDeploy(boolean autoDeploy);
    //添加默认的Context
    public void addDefaultContext(DefaultContext defaultContext);
    public DefaultContext getDefaultContext();
    //返回此容器表示的虚拟主机的规范、完全限定名。用于展示
    public String getName();
    public void setName(String name);

    // --------------------------------------------------------- Public Methods
    public void importDefaultContext(Context context);
    //Host的alias name,匹配多个Request路径到同一个Host
    public void addAlias(String alias);
    public String[] findAliases();
    public void removeAlias(String alias);
    //返回用于处理指定Host请求URI的Context
    public Context map(String uri);
}
```

