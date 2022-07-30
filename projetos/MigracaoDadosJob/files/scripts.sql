DROP TABLE IF EXISTS pessoa;
CREATE TABLE pessoa(id INT, nome VARCHAR(500),email VARCHAR(500),data_nascimento DATETIME,idade INT, PRIMARY KEY(id));
DROP TABLE IF EXISTS dados_bancarios;
CREATE TABLE dados_bancarios(id INT, pessoa_id INT,agencia INT, conta INT, banco INT , PRIMARY KEY(id));
DROP TABLE IF EXISTS batch_job_execution, batch_job_execution_context, batch_job_execution_params,batch_job_execution_seq,batch_job_instance,batch_job_seq,batch_step_execution,batch_step_execution_context,batch_step_execution_seq;