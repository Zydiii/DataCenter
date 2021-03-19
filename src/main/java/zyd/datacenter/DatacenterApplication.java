package zyd.datacenter;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableRetry
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

	@Bean
	MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}

//	@Bean
//	public ServletWebServerFactory servletContainer() {
//		// Enable SSL Trafic
//		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//			@Override
//			protected void postProcessContext(Context context) {
//				SecurityConstraint securityConstraint = new SecurityConstraint();
//				securityConstraint.setUserConstraint("CONFIDENTIAL");
//				SecurityCollection collection = new SecurityCollection();
//				collection.addPattern("/*");
//				securityConstraint.addCollection(collection);
//				context.addConstraint(securityConstraint);
//			}
//		};
//
//		// Add HTTP to HTTPS redirect
//		tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());
//
//		return tomcat;
//	}
//
//	/*
//    We need to redirect from HTTP to HTTPS. Without SSL, this application used
//    port 8082. With SSL it will use port 8443. So, any request for 8082 needs to be
//    redirected to HTTPS on 8443.
//     */
//	private Connector httpToHttpsRedirectConnector() {
//		Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//		connector.setScheme("http");
//		connector.setPort(8082);
//		connector.setSecure(false);
//		connector.setRedirectPort(8080);
//		return connector;
//	}

}
