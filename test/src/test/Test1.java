/**
 * 字符串类
 */
public class CMBString
{

	/**
	 * 获取当前字符个数
	 */
	public int length();

	/**
	   * 取给定位置的字符
	   */
	public char charAt(intpos);

	/**
	 * 将对象转换为字符串类
	 */
	public CMBString valueOf(Object obj);

	/**
	 * 追加字符
	 */
	public CMBString append(CMBString obj);

	/**
	 * 追加字符
	 */
	public CMBString append(String obj);
}

public class CMBStringBuilder
{
	// 添加字符串
	public CMBString append(CMBString s);

	// 返回字符串
	public CMBString toCMBString();
}

// XML节点
public class CMBXMLNode
{
	// 获取节点名
	public CMBString getName();
}

// XML节点属性
public class CMBXMLAttribute extends CMBXMLNode
{
	// 获取节点属性值
	public CMBString getValue();
}

// XML文档
public class CMBXMLDocument extends CMBXMLNode
{
	// 获取XML文档根节点
	public CMBXMLElement getRoot();
}

// XML元素
public class CMBXMLElement extends CMBXMLNode
{
	// 获取所有子元素
	public List<CMBXMLElement> getChildren();

	/**
	 * 获取指定元素名的所有子元素
	 * 
	 * @param name
	 *            元素名
	 */
	public List<CMBXMLElement> getChildren(CMBString name);

	/**
	 * 添加元素作为子元素
	 * 
	 * @param element
	 *            元素
	 */
	public void addChild(CMBXMLElement element);

	/**
	 * 是否含有子元素
	 * 
	 * @return
	 */
	public boolean hasChild();

	// 获取元素所有的属性
	public Set<CMBXMLAttribute> getAttributes();

	/**
	 * 设置元素属性
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public CMBXMLAttribute setAttribute(CMBString name, CMBString value);
}

public class CMBXMLUtils
{
	// 元素唯一标识的属性
	public static CMBString KEY_ID = "id";
	// 换行分割符
	public static CMBString LINE_SPLIT = "\r\n";
	// 缩进
	public static CMBString INDENTATION = "  ";

	// 合并两个节点。
	public static void merge(CMBXMLElement element1, CMBXMLElement element2) {
        for (CMBXMLAttribute attr :element2.getAttributes()) {
            element2.setAttribute(attr.getName(), attr.getValue());
        }
        for (CMBXMLElement elementChild : element1.getChildren()) {
            /* 子元素添加到目标元素 */
            addChild(element1, elementChild);
        }
     }

/**
   * 将子节点添加到父节点下，并进行合并
   */
private static void addChild(CMBXMLElement elementFather, CMBXMLElement elementChild) {
    CMBXMLElement sameChild = getSameChild(elementFather, elementChild);
    if (null != sameChild) {
         merge(【         elementFather            】, sameChild);
    } else {
        elementFather.addChild(elementChild);
    }
}

	/**
	 * 获取父节点elementFather下与子节点elementChild相同的子节点
	 * 
	 * @param elementFather
	 *            父节点
	 * @param elementChild
	 *            子节点
	 * @return
	 */
	private static CMBXMLElement getSameChild(CMBXMLElement elementFather, CMBXMLElement elementChild);

/**
   * 将XML文档转换为字符串
   * @param doc
   * @return
   */
public static CMBString prase(CMBXMLDocument doc) {
    CMBXMLElement root = doc.getRoot().get;
    CMBStringBuilder builder = new CMBStringBuilder ();
    builder.append("<?xml version=\"1.0\" encoding=\"GBK\"?>" + LINE_SPLIT);
    builder.append(valueOf));
    return builder.toCMBString();
  }

	/**
	 * 将XML节点解析成字符串
	 * 
	 * @param element
	 *            XML节点元素
	 * @param deep
	 *            节点离根节点的深度,根节点表示0
	 * @return
	 */
	private static CMBString praseElement(CMBXMLElement element, int deep);

	/**
	 * 获取XML文档
	 * 
	 * @param xmlPath
	 *            XML文档路径
	 * @return
	 */
	public static CMBXMLDocument getDocument(CMBString xmlPath);

public static void main(String[] args) {
    CMBXMLDocument doc_dest = CMBXMLUtils.getDocument("/xml/dest.xml");
    CMBXMLDocument doc_src = CMBXMLUtils.getDocument("/xml/src.xml");
    /* 将doc_src文档按规则合并到doc_dest文档中 */
    CMBXMLElement A1 = doc_src.getRoot();
    CMBXMLElement A2 = doc_dest.getRoot();
    CMBXMLUtils.merge(A1,A2);
    System.out.println(CMBXMLUtils.prase(doc_src));
  }
}