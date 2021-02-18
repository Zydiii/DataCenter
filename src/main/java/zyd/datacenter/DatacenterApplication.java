package zyd.datacenter;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DatacenterApplication {

	public static void main(String[] args) {

		SpringApplication.run(DatacenterApplication.class, args);
	}

}
