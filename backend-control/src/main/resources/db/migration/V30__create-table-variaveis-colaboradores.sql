CREATE TABLE variaveis_colaboradores (
                                     id INT PRIMARY KEY IDENTITY(1,1),
                                     cpf VARCHAR(255),
                                     mes VARCHAR(255),
                                     ano INT,
                                     prv FLOAT,
                                     comissao FLOAT,
                                     chat FLOAT,
                                     pontuacao INT,
                                     peso_pontuacao FLOAT,
                                     prv_final FLOAT,
                                     ajuste_salarial FLOAT,
                                     total_variavel FLOAT,
                                     aprovado_dp BIT NULL,
                                     aprovado_gestor BIT NULL,
                                     aprovado_rener BIT NULL,
                                     atualizado_por VARCHAR(255)

);