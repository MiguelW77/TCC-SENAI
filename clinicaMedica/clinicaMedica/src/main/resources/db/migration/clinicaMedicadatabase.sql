CREATE DATABASE ClinicaMedica;
USE ClinicaMedica;


CREATE TABLE Medico (
    id_medico INT PRIMARY KEY AUTO_INCREMENT,
    id_cadastroMedico INT,
    nome VARCHAR(100) NOT NULL,
    especialidade VARCHAR(100),
    setor VARCHAR(50), 
    FOREIGN KEY (id_cadastroMedico) REFERENCES CadastroMedico(id_cadastroMedico)
);


CREATE TABLE CadastroPaciente (
    id_cadastroPaciente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL, 
    sexo CHAR(1), 
    profissao VARCHAR(100),
    cep VARCHAR(10),
    endereco VARCHAR(200),
    telefone VARCHAR(20),
    data_de_nascimento DATE
);


CREATE TABLE Agenda (
    id_agenda INT PRIMARY KEY AUTO_INCREMENT,
    data_disponivel DATE NOT NULL,
    horario TIME NOT NULL, 
    status ENUM ('Disponível', 'Indisponível'),
    id_medico INT,
    FOREIGN KEY (id_medico) REFERENCES Medico(id_medico)
);


CREATE TABLE Prontuario (
    id_prontuario INT PRIMARY KEY AUTO_INCREMENT,
     id_medico INT,
    id_paciente INT,
    id_consulta INT,
    anotacoes TEXT,  -- Queixa do paciente, Histórico da doença, antecedentes, resultado do exame e tratamento
     FOREIGN KEY (id_medico) REFERENCES Medico(id_medico),
    FOREIGN KEY (id_paciente) REFERENCES CadastroPaciente(id_paciente), -- MUDAR PARA id_cadastroPaciente
    FOREIGN KEY (id_consulta) REFERENCES Consulta(id_consulta)
);

-- Tabela Relatório
CREATE TABLE Relatorio (
    id_relatorio INT PRIMARY KEY AUTO_INCREMENT,
    id_medico INT,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    total_atendimentos INT DEFAULT 0,
    observacoes TEXT, -- Englobar todo o resto
    FOREIGN KEY (id_medico) REFERENCES Medico(id_medico)
);

CREATE TABLE CadastroMedico (
    id_cadastroMedico INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL, 
    sexo CHAR(1), 
    cep VARCHAR(10),
    endereco VARCHAR(200),
    telefone VARCHAR(20),
    data_de_nascimento DATE
);

CREATE TABLE Consulta (
id_consulta INT PRIMARY KEY AUTO_INCREMENT,
id_medico INT,
horario TIME NOT NULL,
especialidade VARCHAR(100), 
data_agendada DATE NOT NULL,
FOREIGN KEY (id_medico) REFERENCES Medico(id_medico)
);
ALTER TABLE Medico
drop column telefone,
drop column email,
drop column endereco,
drop column sexo,
drop column data_de_nascimento;

ALTER TABLE Medico
add column id_cadastroMedico INT;


CREATE TABLE Paciente (
id_paciente INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100),
FOREIGN KEY (id_cadastroPaciente) REFERENCES CadastroPaciente(id_cadastroPaciente) -- MUDAR PARA id_cadastroPaciente
);

alter table prontuario
drop column id_paciente;

alter table prontuario
drop foreign key id_cadastroPaciente;

alter table agenda
drop column horario_inicio,
drop column horario_fim,
add column horario TIME NOT NULL;

alter table Prontuario
drop column diagnostico,
drop column data_atendimento,
drop column queixa_paciente,
drop column historico_doenca,
drop column antecedentes,
drop column resultado_exame,
drop column tratamento;

alter table Prontuario
drop Foreign Key prontuario_ibfk_2,
add constraint FK_cadastroPaciente foreign key(id_cadastroPaciente) references CadastroPaciente(id_cadastroPaciente);

alter table prontuario
add column id_cadastroPaciente INT;

ALTER table prontuario
add constraint FK_cadastroPaciente
foreign key (id_cadastroPaciente) references CadastroPaciente(id_cadastroPaciente);

ALTER TABLE prontuario
drop column id_paciente;

ALTER TABLE Medico
add column nome VARCHAR(100);

ALTER TABLE cadastromedico
add constraint FK_cadastromedico foreign key (id_cadastroMedico) references medico (id_medico);

ALTER TABLE Medico 
add constraint FK_cadastromedico FOREIGN KEY (id_cadastroMedico) REFERENCES CadastroMedico(id_cadastroMedico);

ALTER TABLE Paciente 
add column id_cadastroPaciente INT,
add constraint FK_cadastropaciente FOREIGN KEY (id_cadastroPaciente) REFERENCES CadastroPaciente(id_cadastroPaciente);

DROP TABLE IF EXISTS Paciente;

CREATE TABLE Paciente (
    id_paciente INT PRIMARY KEY AUTO_INCREMENT,
    id_cadastroPaciente INT NOT NULL,
    nome VARCHAR(100),
    FOREIGN KEY (id_cadastroPaciente) REFERENCES CadastroPaciente(id_cadastroPaciente)
);

ALTER TABLE Paciente
add column sexo CHAR(1),
add column data_de_nascimento DATE;

alter table prontuario
drop foreign key FK_cadastroPaciente;

ALTER TABLE prontuario
add column id_paciente INT;

ALTER TABLE Prontuario
ADD CONSTRAINT FK_paciente FOREIGN KEY (id_paciente) REFERENCES Paciente(id_paciente);



ALTER TABLE Prontuario
ADD CONSTRAINT FK_consulta FOREIGN KEY (id_consulta) REFERENCES Consulta(id_consulta);

ALTER TABLE Prontuario
DROP COLUMN id_medico,
DROP foreign key prontuario_ibfk_1