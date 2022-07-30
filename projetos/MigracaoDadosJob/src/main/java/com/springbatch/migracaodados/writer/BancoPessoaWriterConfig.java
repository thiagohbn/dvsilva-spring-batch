package com.springbatch.migracaodados.writer;

import com.springbatch.migracaodados.dominio.Pessoa;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Date;

/**
 * Classe que configura o escritor da tabela 'pessoa' no banco de dados 'migracao_dados'.
 */
@Configuration
public class BancoPessoaWriterConfig {

	@Bean
	public JdbcBatchItemWriter<Pessoa> bancoPessoaWriter(@Qualifier("appDataSource") DataSource dataSource) {

		return new JdbcBatchItemWriterBuilder<Pessoa>()
				.dataSource(dataSource)
				.sql("INSERT INTO pessoa (id, nome, email, data_nascimento, idade) VALUES (?, ?, ?, ?, ?)")
				.itemPreparedStatementSetter(itemPreparedStatementSetter()) // para converter a data
				.build();
	}

	private ItemPreparedStatementSetter<Pessoa> itemPreparedStatementSetter() {

		return (pessoa, ps) -> {
			ps.setInt(1, pessoa.getId());
			ps.setString(2, pessoa.getNome());
			ps.setString(3, pessoa.getEmail());
			ps.setDate(4, new Date(pessoa.getDataNascimento().getTime()));
			ps.setInt(5, pessoa.getIdade());
		};
	}


}
