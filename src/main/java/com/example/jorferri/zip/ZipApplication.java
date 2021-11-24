package com.example.jorferri.zip;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.zip.splitter.UnZipResultSplitter;
import org.springframework.integration.zip.transformer.UnZipTransformer;
import org.springframework.integration.zip.transformer.ZipTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.nativex.hint.TypeHint;

import java.io.File;

@TypeHint(types = GenericMessage.class)
@SpringBootApplication
public class ZipApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipApplication.class, args);
	}

	@Bean
	UnZipTransformer unZipTransformer() {
		return new UnZipTransformer();
	}

	@Bean
	UnZipResultSplitter unZipResultSplitter() {
		return new UnZipResultSplitter();
	}

	@Bean
	ZipTransformer zipTransformer() {
		return new ZipTransformer();
	}

	@Bean
	IntegrationFlow files(@Value("file:///${user.home}/Desktop/in") File in,
						  @Value("file:///${user.home}/Desktop/out") File out,
						  UnZipTransformer unZipTransformer,
						  UnZipResultSplitter unZipResultSplitter,
						  ZipTransformer zipTransformer) {

		var inbound = Files.inboundAdapter(in).autoCreateDirectory(true);


		// We can't have more than one handler
		return IntegrationFlows
				.from(inbound, pm -> pm.poller(p -> p.fixedRate(1000)))
				.transform(unZipTransformer)
//				.handle(message -> {
//					System.out.println("Original Aggregated message: " + message.getPayload());
//					System.out.println("Headers:");
//					message.getHeaders().forEach((k, v) -> System.out.println(k + "=" + v));
//				})
				.split(unZipResultSplitter)
//				.handle(message -> {
//					System.out.println("new message: " + message.getPayload());
//					System.out.println("Headers:");
//					message.getHeaders().forEach((k, v) -> System.out.println(k + "=" + v));
//				})
				.transform(zipTransformer)
				.handle(Files.outboundAdapter(out)
						.autoCreateDirectory(true)
						.fileNameGenerator(message -> message.getHeaders().get(FileHeaders.FILENAME) + ".zip"))
				.get();

	}

}
