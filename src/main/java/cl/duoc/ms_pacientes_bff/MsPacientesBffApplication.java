package cl.duoc.ms_pacientes_bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsPacientesBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPacientesBffApplication.class, args);
	}

}
