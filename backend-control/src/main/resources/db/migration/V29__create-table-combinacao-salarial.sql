CREATE TABLE combinacao_salarial (
                                           id INT PRIMARY KEY IDENTITY(1,1),
                                           cpf VARCHAR(255) unique,
                                           base FLOAT,
                                           gratificacao_funcao FLOAT,
                                           possui_grat_func BIT NULL,
                                           ajuda_custo FLOAT,
                                           auxilio_combustivel FLOAT,
                                           auxilio_moradia FLOAT,
                                           comissao BIT NULL,
                                           chat VARCHAR(255),
                                           prv FLOAT,
                                           vale_transporte BIT NULL,
                                           vale_alimentacao BIT NULL,
                                           vale_refeicao BIT NULL,
                                           atualizado_por VARCHAR(255)

);
