-- =============================================================
-- V2 - Cria usuário administrador
-- =============================================================

-- Usuario
INSERT INTO USUARIO (id, nome, email, senha, tipo_usuario, unidade_id, criacao, ultima_modificacao)
VALUES ( nextval('usuario_sequence'), 'Admin', 'admin@farmacheck.com', 'admin', 'ADMINISTRADOR', null, NOW(), NOW());