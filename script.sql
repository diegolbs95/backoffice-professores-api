\c backoffice_database

CREATE TABLE tb_professores (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255),
    senha VARCHAR(255),
    nome VARCHAR(255),
    status INTEGER,
    role VARCHAR(255)
);

CREATE UNIQUE INDEX idx_tb_professor_email ON tb_professores (email);

CREATE TABLE tb_aulas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255),
    descricao VARCHAR(255),
    data_prevista_aula DATE,
    professor_id BIGINT NOT NULL,
    CONSTRAINT fk_tb_aula_professor FOREIGN KEY (professor_id) REFERENCES tb_professores (id)
);

CREATE TABLE tb_tokens (
  id SERIAL PRIMARY KEY,
  token VARCHAR(255) UNIQUE NOT NULL,
  token_type VARCHAR(255) NOT NULL,
  professor_id BIGINT,
  revoked BOOLEAN NOT NULL,
  expired BOOLEAN NOT NULL,
  FOREIGN KEY (professor_id) REFERENCES tb_professores (id)
);