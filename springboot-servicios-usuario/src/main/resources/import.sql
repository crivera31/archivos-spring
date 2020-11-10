INSERT INTO usuarios(username, password, enabled) VALUES ('admin','$2a$10$qGyDfZLBB.SgLv7GCP3uZe3oM38fVtr58T1iZ1LNOvO61loNUAAaK',true);


INSERT INTO roles(nombre,cod_empresa) VALUES ('ROLE_ADMIN','COVESA');
INSERT INTO roles(nombre,cod_empresa) VALUES ('ROLE_COVESA_HDS','COVESA');
INSERT INTO roles(nombre,cod_empresa) VALUES ('ROLE_COVESA_LOG','COVESA');
INSERT INTO roles(nombre,cod_empresa) VALUES ('ROLE_COVESA_ALM','COVESA');
INSERT INTO roles(nombre,cod_empresa) VALUES ('ROLE_COVESA_TEC','COVESA');
INSERT INTO roles(nombre,cod_empresa) VALUES ('ROLE_COVESA_SEG','COVESA');
INSERT INTO roles(nombre,cod_empresa) VALUES ('ROLE_COVESA_MEC','COVESA');
INSERT INTO roles(nombre,cod_empresa) VALUES ('ROLE_COVESA_ORIPLAN','COVESA');
INSERT INTO roles(nombre,cod_empresa) VALUES ('ROLE_COVESA_CONT','COVESA');
INSERT INTO roles(nombre,cod_empresa) VALUES ('ROLE_ORIANA_CONT','ORIANA');
INSERT INTO roles(nombre,cod_empresa) VALUES ('ROLE_VILLASOL_CONT','VILLASOL');

INSERT INTO usuarios_roles(usuario_id, rol_id) VALUES (1, 1);
INSERT INTO usuarios_roles(usuario_id, rol_id) VALUES (1, 2);
INSERT INTO usuarios_roles(usuario_id, rol_id) VALUES (1, 3);
INSERT INTO usuarios_roles(usuario_id, rol_id) VALUES (1, 4);
INSERT INTO usuarios_roles(usuario_id, rol_id) VALUES (1, 5);
INSERT INTO usuarios_roles(usuario_id, rol_id) VALUES (1, 6);
INSERT INTO usuarios_roles(usuario_id, rol_id) VALUES (1, 7);
INSERT INTO usuarios_roles(usuario_id, rol_id) VALUES (1, 8);
INSERT INTO usuarios_roles(usuario_id, rol_id) VALUES (1, 9);
INSERT INTO usuarios_roles(usuario_id, rol_id) VALUES (1, 10);
INSERT INTO usuarios_roles(usuario_id, rol_id) VALUES (1, 11);