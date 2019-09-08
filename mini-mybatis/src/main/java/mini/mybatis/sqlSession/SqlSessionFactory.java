package mini.mybatis.sqlSession;

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
import mini.mybatis.config.Configuration;
import mini.mybatis.config.MappedStatement;

public class SqlSessionFactory {

    public final Configuration conf = new Configuration();

    public SqlSessionFactory(String xmlPackageName) {
        // 加载数据库配置信息
        loadDbInfo();
        // 加载mappers
        loadMappersInfo(xmlPackageName);
    }

    /**
	 * 加载数据库的连接信息，设置到conf中
	 */
	private void loadDbInfo() {
		InputStream dbInfo = SqlSessionFactory.class.getClassLoader()
			.getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		try {
			properties.load(dbInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conf.setJdbcDriver(properties.getProperty("jdbc.driver"));
		conf.setJdbcUrl(properties.getProperty("jdbc.url"));
		conf.setUsername(properties.getProperty("jdbc.username"));
		conf.setPassword(properties.getProperty("jdbc.password"));
	}
	
	private void loadMappersInfo(String xmlPackageName) {
		//获取存放mapper文件的路径
		String path = xmlPackageName.replaceAll("\\.", "/");
		URL url = this.getClass().getClassLoader().getResource(path);
		
		File mappers = new File(url.getFile());
		if (mappers.isDirectory()) {
			File[] listFiles = mappers.listFiles();
			if (listFiles == null || listFiles.length == 0)return;
			for (File mapper : listFiles) {
				loadMapperInfo(mapper);
			}
		}
	}
	
	private void loadMapperInfo(File mapper) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(mapper);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element node = document.getRootElement();
		String namespace = node.attribute("namespace").getData().toString();
		List<Element> selects = node.elements("select");
		if (selects == null || selects.isEmpty()) return;
		for (Element element : selects) {
			MappedStatement mappedStatement = new MappedStatement();
			String id = element.attribute("id").getData().toString();
			String resultType = element.attribute("resultType").getData().toString();
			String sql = element.getData().toString();
			String sourceId = namespace+"."+id;
			
			mappedStatement.setSourceId(sourceId);
			mappedStatement.setNamespace(namespace);
			mappedStatement.setResultType(resultType);
			mappedStatement.setSql(sql);
			conf.getMappedStatements().put(sourceId, mappedStatement);
		}
	}
	
	public SqlSession openSession() {
		return new DefaultSqlSession(conf);
	}
}
