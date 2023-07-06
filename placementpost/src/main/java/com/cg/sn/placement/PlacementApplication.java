package com.cg.sn.placement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PlacementApplication {

	public static void main(String[] args) {
		// comment
		SpringApplication.run(PlacementApplication.class, args);
	}

}
