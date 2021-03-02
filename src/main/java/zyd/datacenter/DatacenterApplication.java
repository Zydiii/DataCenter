package zyd.datacenter;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zyd.datacenter.Service.Mail.MailService;

import java.util.Date;

@SpringBootApplication
public class DatacenterApplication {

	public static void main(String[] args) {

		SpringApplication.run(DatacenterApplication.class, args);

//		DocsConfig config = new DocsConfig();
//		config.setProjectPath("C:\\Users\\Zydiii\\Desktop\\DataCenter"); // 项目根目录
//		config.setProjectName("DataCenter"); // 项目名称
//		config.setApiVersion("V1.0");       // 声明该API的版本
//		config.setDocsPath("C:\\Users\\Zydiii\\Desktop\\DataCenter\\file"); // 生成API 文档所在目录
//		config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
//		Docs.buildHtmlDocs(config); // 执行生成文档
	}

}
