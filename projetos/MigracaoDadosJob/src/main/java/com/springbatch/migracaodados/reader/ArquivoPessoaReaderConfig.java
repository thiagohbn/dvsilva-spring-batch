package com.springbatch.migracaodados.reader;

import com.springbatch.migracaodados.dominio.Pessoa;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.Date;

/**
 * Classe responsável por configurar o leitor do arquivo de pessoas.csv
 */
@Configuration
public class ArquivoPessoaReaderConfig {

    @Bean
    public FlatFileItemReader<Pessoa> arquivoPessoaReader() {

        return new FlatFileItemReaderBuilder<Pessoa>()
                .name("arquivoPessoaReader")
                .resource(new FileSystemResource("projetos/MigracaoDadosJob/files/pessoas.csv"))
                .delimited()
                .names("nome", "email", "dataNascimento", "idade", "id")
                .addComment("--") // arquivos com comentarios [ignora a primeira linha do arquivo]
                // .targetType(Pessoa.class); devido ao tipo Date precisa do cast nesse caso precisa criar um mapper
                .fieldSetMapper(fieldSetMapper()) //Mapeador de campos customizado devido a existência de um tipo complexo Date
                .build();
    }

    /**
     * Mapeador de dados customizado
     *
     * @return FieldSetMapper
     */
    private FieldSetMapper<Pessoa> fieldSetMapper() {

        return fieldSet -> {

            Pessoa pessoa = new Pessoa();
            pessoa.setNome(fieldSet.readString("nome"));
            pessoa.setEmail(fieldSet.readString("email"));
            pessoa.setDataNascimento(new Date(fieldSet.readDate("dataNascimento", "yyyy-MM-dd HH:mm:ss").getTime()));
            pessoa.setIdade(fieldSet.readInt("idade"));
            pessoa.setId(fieldSet.readInt("id"));

            return pessoa;
        };
    }
}
