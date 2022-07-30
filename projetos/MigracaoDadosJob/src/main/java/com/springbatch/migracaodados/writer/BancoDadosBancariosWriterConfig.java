package com.springbatch.migracaodados.writer;

import com.springbatch.migracaodados.dominio.DadosBancarios;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Classe que configura o escritor da tabela 'dados_bancarios' no banco de dados 'migracao_dados'.
 */
@Configuration
public class BancoDadosBancariosWriterConfig {

    @Bean
    public JdbcBatchItemWriter<DadosBancarios> bancoDadosBancariosWriter(@Qualifier("appDataSource") DataSource dataSource) {

        return new JdbcBatchItemWriterBuilder<DadosBancarios>()
                .dataSource(dataSource)
                .sql("INSERT INTO dados_bancarios (id, pessoa_id, agencia, conta, banco) " +
                        "VALUES " +
                        "(:id, :pessoaId, :agencia, :conta, :banco)")
                //Mapeia os nomes das propriedades a serem inseridas igual aos nomes dos atributos do bean que está sendo utilizado: 'DadosBancarios'
                .beanMapped() //Suficiente para dados não complexos
                .build();
    }
}
