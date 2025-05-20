-- 1) CARGOS
INSERT INTO cargos (nome, nivel_permissao, permissoes) VALUES ('ADMIN', 5, '["create","read","update","delete"]');
INSERT INTO cargos (nome, nivel_permissao, permissoes) VALUES ('OPERADOR', 3, '["read","update"]');
INSERT INTO cargos (nome, nivel_permissao, permissoes) VALUES ('MANUTENCAO', 2, '["read","update_status"]');
INSERT INTO cargos (nome, nivel_permissao, permissoes) VALUES ('VISITANTE', 1, '["read"]');
INSERT INTO cargos (nome, nivel_permissao, permissoes) VALUES ('AUDITOR', 4, '["read","create_log","read_log"]');

-- 2) USUÁRIOS 
INSERT INTO usuarios (nome, email, senha, id_cargo, ativo) VALUES ('João Silva', 'joao@ex.com', '$2y$12$hash1...', 1, 1);
INSERT INTO usuarios (nome, email, senha, id_cargo, ativo) VALUES ('Maria Souza', 'maria@ex.com', '$2y$12$hash2...', 2, 1);
INSERT INTO usuarios (nome, email, senha, id_cargo, ativo) VALUES ('Paulo Costa', 'paulo@ex.com', '$2y$12$hash3...', 3, 1);
INSERT INTO usuarios (nome, email, senha, id_cargo, ativo) VALUES ('Ana Pereira', 'ana@ex.com', '$2y$12$hash4...', 4, 1);
INSERT INTO usuarios (nome, email, senha, id_cargo, ativo) VALUES ('Carlos Lima', 'carlos@ex.com', '$2y$12$hash5...', 5, 1);

-- 3) MOTOS
INSERT INTO motos (placa, marca, modelo, cor, presente, imagem_referencia) VALUES ('ABC1234', 'Honda', 'CB 500', 'Preta', 1, 'ref1.jpg');
INSERT INTO motos (placa, marca, modelo, cor, presente, imagem_referencia) VALUES ('DEF5678', 'Yamaha', 'YBR 125', 'Vermelha', 0, 'ref2.jpg');
INSERT INTO motos (placa, marca, modelo, cor, presente, imagem_referencia) VALUES ('GHI9012', 'Suzuki', 'GSR 750', 'Azul', 1, 'ref3.jpg');
INSERT INTO motos (placa, marca, modelo, cor, presente, imagem_referencia) VALUES ('JKL3456', 'Kawasaki', 'Ninja 400', 'Verde', 1, 'ref4.jpg');
INSERT INTO motos (placa, marca, modelo, cor, presente, imagem_referencia) VALUES ('MNO7890', 'Ducati', 'Monster', 'Branca', 0, 'ref5.jpg');

-- 4) CÂMERAS 
INSERT INTO cameras (localizacao, status) VALUES ('Portão de Entrada', 'ativo');
INSERT INTO cameras (localizacao, status) VALUES ('Área Interna 1', 'manutencao');
INSERT INTO cameras (localizacao, status) VALUES ('Garagem Norte', 'ativo');
INSERT INTO cameras (localizacao, status) VALUES ('Garagem Sul', 'inativo');
INSERT INTO cameras (localizacao, status) VALUES ('Saída', 'ativo');

-- 5) RECONHECIMENTOS
INSERT INTO reconhecimentos (id_moto, id_camera, precisao, imagem_capturada, confianca_minima, data_hora) VALUES (1, 1, 0.9523, 'rec1.jpg', 0.8000, CURRENT_TIMESTAMP);
INSERT INTO reconhecimentos (id_moto, id_camera, precisao, imagem_capturada, confianca_minima, data_hora) VALUES (2, 3, 0.8734, 'rec2.jpg', 0.8500, CURRENT_TIMESTAMP);
INSERT INTO reconhecimentos (id_moto, id_camera, precisao, imagem_capturada, confianca_minima, data_hora) VALUES (3, 1, 0.9101, 'rec3.jpg', 0.9000, CURRENT_TIMESTAMP);
INSERT INTO reconhecimentos (id_moto, id_camera, precisao, imagem_capturada, confianca_minima, data_hora) VALUES (4, 5, 0.7822, 'rec4.jpg', 0.7500, CURRENT_TIMESTAMP);
INSERT INTO reconhecimentos (id_moto, id_camera, precisao, imagem_capturada, confianca_minima, data_hora) VALUES (5, 2, 0.9955, 'rec5.jpg', 0.9500, CURRENT_TIMESTAMP);

-- 6) REGISTROS 
INSERT INTO registros (id_moto, id_usuario, id_reconhecimento, tipo, modo_registro, data_hora, id_camera) VALUES (1, 2, 1, 'entrada', 'automatico', CURRENT_TIMESTAMP, 1);
INSERT INTO registros (id_moto, id_usuario, id_reconhecimento, tipo, modo_registro, data_hora, id_camera) VALUES (1, 3, 1, 'saida', 'manual', CURRENT_TIMESTAMP, 1);
INSERT INTO registros (id_moto, id_usuario, id_reconhecimento, tipo, modo_registro, data_hora, id_camera) VALUES (2, 2, 2, 'entrada', 'automatico', CURRENT_TIMESTAMP, 1);
INSERT INTO registros (id_moto, id_usuario, id_reconhecimento, tipo, modo_registro, data_hora, id_camera) VALUES (3, 5, 3, 'entrada', 'automatico', CURRENT_TIMESTAMP, 1);
INSERT INTO registros (id_moto, id_usuario, id_reconhecimento, tipo, modo_registro, data_hora, id_camera) VALUES (4, 1, 4, 'saida', 'automatico', CURRENT_TIMESTAMP, 1);

-- 7) LOGS 
INSERT INTO log_alteracoes (id_usuario, id_moto, tipo_acao, campo_alterado, valor_antigo, valor_novo, data_hora) VALUES (1, 1, 'edicao', 'cor', 'Preta', 'Azul', CURRENT_TIMESTAMP);
INSERT INTO log_alteracoes (id_usuario, id_moto, tipo_acao, campo_alterado, valor_antigo, valor_novo, data_hora) VALUES (2, 2, 'edicao', 'modelo', 'YBR 125', 'YBR 150', CURRENT_TIMESTAMP);
INSERT INTO log_alteracoes (id_usuario, id_moto, tipo_acao, campo_alterado, valor_antigo, valor_novo, data_hora) VALUES (3, 3, 'edicao', 'imagem_referencia', 'ref3.jpg', 'ref3_updated.jpg', CURRENT_TIMESTAMP);
INSERT INTO log_alteracoes (id_usuario, id_moto, tipo_acao, campo_alterado, valor_antigo, valor_novo, data_hora) VALUES (5, 4, 'insercao', 'presente', NULL, '1', CURRENT_TIMESTAMP);
INSERT INTO log_alteracoes (id_usuario, id_moto, tipo_acao, campo_alterado, valor_antigo, valor_novo, data_hora) VALUES (1, 5, 'exclusao', 'imagem_referencia', 'ref5.jpg', NULL, CURRENT_TIMESTAMP);
