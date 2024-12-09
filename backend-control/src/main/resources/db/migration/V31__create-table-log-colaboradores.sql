CREATE TABLE log_colaboradores(

                                 id INT PRIMARY KEY IDENTITY(1,1),
                                 tipo VARCHAR(255),
                                 mensagem VARCHAR(5000),
                                 nome_colaborador VARCHAR(255),
                                 cpf_colaborador VARCHAR(255),
                                 usuario VARCHAR(255),
                                 mes_ano VARCHAR(255),
                                 dpid INT,
                                 data_hora DATETIME DEFAULT GETDATE()

)