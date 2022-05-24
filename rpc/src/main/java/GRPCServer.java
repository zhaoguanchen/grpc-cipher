import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.CipherService;

public class GRPCServer {
	public static void main(String[] args) throws InterruptedException, IOException {

		Server server = ServerBuilder.forPort(9090).addService(new CipherService()).build();

		server.start();
		System.out.println("server start");

		server.awaitTermination();
	}
}
