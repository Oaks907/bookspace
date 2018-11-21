package ioc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by haifei on 21/11/2018 11:14 PM.
 */
public class SimpleIOC {

  private Map<String, Object> beanMap = new HashMap<>();

  public SimpleIOC(String location) throws Exception {
    loadBeans(location);
  }


  public Object getBean(String name) {
    Object bean = beanMap.get(name);
    if (bean == null) {
      throw new IllegalArgumentException("there is no bean with name " + name);
    }

    return bean;
  }

  private void loadBeans(String locations) throws Exception {
    // 加载 xml 配置文件
    InputStream inputStream = new FileInputStream(locations);
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = factory.newDocumentBuilder();
    Document doc = docBuilder.parse(inputStream);

    Element element = doc.getDocumentElement();
    final NodeList childNodes = element.getChildNodes();

    // 遍历 <bean> 标签
    for (int i = 0; i < childNodes.getLength(); i++) {
      Node node = childNodes.item(i);
      if (node instanceof Element) {
        Element ele = (Element) node;
        String id = ele.getAttribute("id");
        String className = ele.getAttribute("class");

        // 加载 beanClass
        Class beanClass = null;

        try {
          System.out.println("className:" + className);
          beanClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
          return;
        }

        // 创建 bean
        Object bean = beanClass.newInstance();

        // 遍历 <property> 标签
        NodeList propertyNodes = ele.getElementsByTagName("property");
        for (int j = 0; j < propertyNodes.getLength(); j++) {
          final Node item = propertyNodes.item(j);
          if (item instanceof Element) {
            Element propertyItem = (Element) item;
            final String name = propertyItem.getAttribute("name");
            final String value = propertyItem.getAttribute("value");

            // 利用反射将 bean 相关字段访问权限设为可访问
            final Field declaredField = bean.getClass().getDeclaredField(name);
            declaredField.setAccessible(true);

            if (value != null && value.length() > 0) {
              // 将属性值填充到相关字段中
              declaredField.set(bean, name);
            } else {
              String ref = propertyItem.getAttribute("ref");
              if (ref == null || ref.length() == 0) {
                throw new IllegalArgumentException("ref config error");
              }

              // 将引用填充到相关字段中
              declaredField.set(bean, getBean(ref));
            }

            // 将 bean 注册到 bean 容器中
            registerBean(id, bean);
          }
        }
      }
    }
  }

  private void registerBean(String id, Object bean) {
    beanMap.put(id, bean);
  }
}
