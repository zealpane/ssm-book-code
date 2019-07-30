package mini.mybatis;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SqlSessionFactory {

    public final Configuration conf = new Configuration();

    public SqlSessionFactory() {
        // 加载数据库配置信息
        loadDbInfo();
        // 加载mappers
        loadMappersInfo();
    }

    private void loadMappersInfo() {
    	URL resources = null;
    	resources = SqlSessionFactory.class.getClassLoader().getResource("mapper");
    	File mappers = new File(resources.getFile());
    	if (mappers.isDirectory()) {
    		File[] files = mappers.listFiles();
    		for (File file : files) {
				loadMapperInfo(file);
			}
    	}
    }

    /**
     * 加载指定文件夹下的mapper.xml文件
     * @param file
     * @throws DocumentException 
     */
    private void loadMapperInfo(File file) {
		// 这里要涉及到关于java操作xml的一些知识
    	SAXReader reader = new SAXReader();
    	Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			log.error("读取xml文件内容失败");
		}
		// 获取根节点元素<mapper>
		Element root = document.getRootElement();
		// 获取命名空间
		String namespace = root.attribute("namespace").getData().toString();
		// 获取select子节点列表
		List<Element> selectList = root.elements("select");
		// 遍历，将信息设置到MappedStatement对象，并注入到Configuration对象中
		for (Element element : selectList) {
			MappedStatement mappedStatement = new MappedStatement();
			String id = element.attributeValue("id"); // 实际上相当于调用了element.attribute("id").getData().toString();
			String resultType = element.attributeValue("resultType");
			String sql = element.getData().toString();
			String sourceId = namespace + "." + id;
			// 链式调用
			mappedStatement.setSourceId(sourceId)
				.setSql(sql)
				.setResultType(resultType)
				.setNamespace(namespace);
		}
	}

	private void loadDbInfo() {
        // 加载数据库配置文件
        InputStream inputStream = SqlSessionFactory.class.getClassLoader().getResourceAsStream("db.properties");
        Properties prop = new Properties();
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将数据库配置信息存入conf对象

    }
}
