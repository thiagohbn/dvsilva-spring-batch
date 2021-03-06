package com.springbatch.migracaodados.reader;

import com.springbatch.migracaodados.dominio.DadosBancarios;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 * Responsável por configurar o leitor do arquivo 'dados_bancarios.csv'.
 */
@Configuration
public class ArquivoDadosBancariosReaderConfig {

	@Bean
	public FlatFileItemReader<DadosBancarios> dadosBancariosReader() {

		return new FlatFileItemReaderBuilder<DadosBancarios>()
				.name("dadosBancariosReader")
				.resource(new FileSystemResource("projetos/MigracaoDadosJob/files/dados_bancarios.csv"))
				.delimited()
				.names("pessoaId", "agencia", "conta", "banco", "id")
				.addComment("--")
				.targetType(DadosBancarios.class) //Não contém campos complexos, só primitivos, por isso pode ser mapeado diretamente via targetType
				.build();
	}
}
