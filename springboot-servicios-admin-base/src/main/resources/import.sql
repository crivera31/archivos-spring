INSERT INTO nivel_cuenta(id, nom_nivel_cuenta,abre_nom_nivel_cuenta) values(1,'Balance','B');
INSERT INTO nivel_cuenta(id, nom_nivel_cuenta,abre_nom_nivel_cuenta) values(2,'Sub-Cuenta','S');
INSERT INTO nivel_cuenta(id, nom_nivel_cuenta,abre_nom_nivel_cuenta) values(3,'Registro','R');

INSERT INTO moneda(id, nom_moneda, abre_nom_moneda) values(1,'Soles','S');
INSERT INTO moneda(id, nom_moneda, abre_nom_moneda) values(2,'Dolares','D');

INSERT INTO tipo_cuenta(id, nom_tipo_cuenta, abre_nom_tipo_cuenta) values(1,'Activo','A');
INSERT INTO tipo_cuenta(id, nom_tipo_cuenta, abre_nom_tipo_cuenta) values(2,'Pasivo','P');
INSERT INTO tipo_cuenta(id, nom_tipo_cuenta, abre_nom_tipo_cuenta) values(3,'Resultado','R');
INSERT INTO tipo_cuenta(id, nom_tipo_cuenta, abre_nom_tipo_cuenta) values(4,'Naturaleza','N');
INSERT INTO tipo_cuenta(id, nom_tipo_cuenta, abre_nom_tipo_cuenta) values(5,'Función','F');
INSERT INTO tipo_cuenta(id, nom_tipo_cuenta, abre_nom_tipo_cuenta) values(6,'Orden','O');
INSERT INTO tipo_cuenta(id, nom_tipo_cuenta, abre_nom_tipo_cuenta) values(7,'Mayor','M');

INSERT INTO tipo_analisis(id, nom_tipo_analisis, abre_nom_tipo_analisis) values(1,'Sin Análisis','');
INSERT INTO tipo_analisis(id, nom_tipo_analisis, abre_nom_tipo_analisis) values(2,'Por Documentos','S');
INSERT INTO tipo_analisis(id, nom_tipo_analisis, abre_nom_tipo_analisis) values(3,'Cuenta de Banco','B');
INSERT INTO tipo_analisis(id, nom_tipo_analisis, abre_nom_tipo_analisis) values(4,'Sólo Detalle','D');


INSERT INTO opcion_plan_cuentas(id, nom_opcion_plan_cuenta, abre_nom_opcion_plan_cuenta) values(1,'Ajuste por Diferencia de Cambio, a nivel de saldos, para cuentas en Dólares','S');
INSERT INTO opcion_plan_cuentas(id, nom_opcion_plan_cuenta, abre_nom_opcion_plan_cuenta) values(2,'Conversión a Moneda Extranjera, a nivel de saldos, para cuentas en Soles','D');
INSERT INTO opcion_plan_cuentas(id, nom_opcion_plan_cuenta, abre_nom_opcion_plan_cuenta) values(3,'Sin Nada','Z');




INSERT INTO tipo_documento(id, nombre, codigo) values(1,'OTROS','-');
INSERT INTO tipo_documento(id, nombre, codigo) values(2,'OTROS TIPOS','0');
INSERT INTO tipo_documento(id, nombre, codigo) values(3,'DNI','1');
INSERT INTO tipo_documento(id, nombre, codigo) values(4,'CARNET EXT.','4');
INSERT INTO tipo_documento(id, nombre, codigo) values(5,'RUC','6');
INSERT INTO tipo_documento(id, nombre, codigo) values(6,'PASAPORTE','7');

INSERT INTO documento(id, nombre_documento, codigo) values(1,'OTROS','00');
INSERT INTO documento(id, nombre_documento, codigo) values(2,'FACTURA','01');
INSERT INTO documento(id, nombre_documento, codigo) values(3,'RECIBO POR HONORARIO','02');
INSERT INTO documento(id, nombre_documento, codigo) values(4,'BOLETA DE VENTA','03');
INSERT INTO documento(id, nombre_documento, codigo) values(5,'LIQUIDAC. DE COMPRA','04');
INSERT INTO documento(id, nombre_documento, codigo) values(6,'BOLETO DE AVION','05');
INSERT INTO documento(id, nombre_documento, codigo) values(7,'CARTA PORTE AEREO','06');
INSERT INTO documento(id, nombre_documento, codigo) values(8,'NOTA DE CREDITO','07');
INSERT INTO documento(id, nombre_documento, codigo) values(9,'NOTA DE DEBITO','08');
INSERT INTO documento(id, nombre_documento, codigo) values(10,'GUIA DE REMISION','09');
INSERT INTO documento(id, nombre_documento, codigo) values(11,'RECIBO DE ARRENDAMIE','10');
INSERT INTO documento(id, nombre_documento, codigo) values(12,'POLIZA BOLSA VALORES','11');
INSERT INTO documento(id, nombre_documento, codigo) values(13,'TICKET MAQUINA REGIS','12');
INSERT INTO documento(id, nombre_documento, codigo) values(14,'DOCUMENTO BANCOS','13');
INSERT INTO documento(id, nombre_documento, codigo) values(15,'RECIBOS SERV. PUBLIC','14');
INSERT INTO documento(id, nombre_documento, codigo) values(16,'BOLETO TRANS. PUBLIC','15');
INSERT INTO documento(id, nombre_documento, codigo) values(17,'BOLETO DE VIAJE T.P','16');
INSERT INTO documento(id, nombre_documento, codigo) values(18,'DOCUMENTO IGLESIA C.','17');
INSERT INTO documento(id, nombre_documento, codigo) values(19,'DOCUMENTO AFP','18');
INSERT INTO documento(id, nombre_documento, codigo) values(20,'BOLETO ESP. PUBLICOS','19');
INSERT INTO documento(id, nombre_documento, codigo) values(21,'COMP. DE RETENCION','20');
INSERT INTO documento(id, nombre_documento, codigo) values(22,'CONOCIM. EMBARQUE','21');
INSERT INTO documento(id, nombre_documento, codigo) values(23,'COMPROB. NO HABITUAL','22');
INSERT INTO documento(id, nombre_documento, codigo) values(24,'POLIZA DE ADJUDICAC.','23');
INSERT INTO documento(id, nombre_documento, codigo) values(25,'CERTIF. REGALIAS','24');
INSERT INTO documento(id, nombre_documento, codigo) values(26,'DOC. ATRIB. ISC','25');
INSERT INTO documento(id, nombre_documento, codigo) values(27,'REC. UNIDAD DE AGUA','26');
INSERT INTO documento(id, nombre_documento, codigo) values(28,'SEGURO C.T.R.','27');
INSERT INTO documento(id, nombre_documento, codigo) values(29,'TARIFA U. AEROPUERTO','28');
INSERT INTO documento(id, nombre_documento, codigo) values(30,'DOC. COFOPRI','29');
INSERT INTO documento(id, nombre_documento, codigo) values(31,'DOC. EMP. EN T.CRED','30');
INSERT INTO documento(id, nombre_documento, codigo) values(32,'GUIA RT','31');
INSERT INTO documento(id, nombre_documento, codigo) values(33,'DOC. INDUS. GAS NATU','32');
INSERT INTO documento(id, nombre_documento, codigo) values(34,'DOC. OPERADOR','34');
INSERT INTO documento(id, nombre_documento, codigo) values(35,'DOC. PARTICIPE ','35');
INSERT INTO documento(id, nombre_documento, codigo) values(36,'REC. DIST. GAS NATUR','36');
INSERT INTO documento(id, nombre_documento, codigo) values(37,'DOC. REVI. TEC. VEHI','37');
INSERT INTO documento(id, nombre_documento, codigo) values(38,'D.U.A. IMPORTACION','50');
INSERT INTO documento(id, nombre_documento, codigo) values(39,'DESP. SIM. IMP. SIMP','52');
INSERT INTO documento(id, nombre_documento, codigo) values(40,'DECLARAC. MENSAJERIA','53');
INSERT INTO documento(id, nombre_documento, codigo) values(41,'LIQUID. COBRANZAS','54');
INSERT INTO documento(id, nombre_documento, codigo) values(42,'NOTA DE CREDITO ES.','87');
INSERT INTO documento(id, nombre_documento, codigo) values(43,'NOTA DE DEBITO ESP.','88');
INSERT INTO documento(id, nombre_documento, codigo) values(44,'COMP. DE NO DOMICILI','91');
INSERT INTO documento(id, nombre_documento, codigo) values(45,'EXC. CFR DE BIENES','92');
INSERT INTO documento(id, nombre_documento, codigo) values(46,'N.C. NO DOMICILIADO','97');
INSERT INTO documento(id, nombre_documento, codigo) values(47,'N.D. NO DOMICILIADO','98');
INSERT INTO documento(id, nombre_documento, codigo) values(48,'CONSOLID. BOLETAS VN','99');
INSERT INTO documento(id, nombre_documento, codigo) values(49,'BOLETA DE PAGO','BP');
INSERT INTO documento(id, nombre_documento, codigo) values(50,'BOLETA DE SUELDO','BS');
INSERT INTO documento(id, nombre_documento, codigo) values(51,'CHEQUE','CH');
INSERT INTO documento(id, nombre_documento, codigo) values(52,'COMPROB DE PERCEPCIO','CP');
INSERT INTO documento(id, nombre_documento, codigo) values(53,'DEPOSITO','DP');
INSERT INTO documento(id, nombre_documento, codigo) values(54,'LETRA','LE');
INSERT INTO documento(id, nombre_documento, codigo) values(55,'RECIBO DE EGRESO','RE');
INSERT INTO documento(id, nombre_documento, codigo) values(56,'RECIBO DE INGRESO','RI');
INSERT INTO documento(id, nombre_documento, codigo) values(57,'TRANSFERENCIA','TR');

INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(1,'001','DEPOSITO EN CTA');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(2,'002','GIRO');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(3,'003','TRANS. DE FONDOS');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(4,'004','ORDEN DE PAGO');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(5,'005','TARJETA DE DEBITO');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(6,'006','TARJETA DE CREDITO');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(7,'007','CHEQUE NO NEGOCIABLE');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(8,'008','EFECTIVO N.O.A.M.P.');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(9,'009','EFECTIVO O.U.M.P');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(10,'010','MP COMER.EXT.');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(11,'011','LETRAS DE CAMBIO');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(12,'101','TRANS. COM.EXT.');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(13,'102','CHE.BCO COM.EXT.');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(14,'103','ORD.PAGO SIMPLE COM.EXT');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(15,'104','ORDEN DE PAGO DOCU. COM.EXT');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(16,'105','REMESA SIMPLE COM.EXT.');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(17,'106','REMESA DOCU. COM.EXT.');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(18,'107','CARTA CRED.SIMP COM.EXT.');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(19,'108','CARTA CRED.DOCU. COM.EXT');
INSERT INTO medio_pago(id, codigo, nombre_medio_pago) values(20,'999','OTROS MEDIOS DE PAGO');

INSERT INTO origen(id, codigo, descripcion, enabled) values(1,'00','APERTURA',1);
INSERT INTO origen(id, codigo, descripcion, enabled) values(2,'01','COMPRAS',1);
INSERT INTO origen(id, codigo, descripcion, enabled) values(3,'02','VENTAS',1);
INSERT INTO origen(id, codigo, descripcion, enabled) values(4,'03','INGRESOS',1);
INSERT INTO origen(id, codigo, descripcion, enabled) values(5,'04','EGRESOS',1);
INSERT INTO origen(id, codigo, descripcion, enabled) values(6,'05','DIARIO',1);
INSERT INTO origen(id, codigo, descripcion, enabled) values(7,'15','HONORARIOS',1);
INSERT INTO origen(id, codigo, descripcion, enabled) values(8,'99','AUTOMATICOS',1);



INSERT INTO clientes(id, nombre, apellido, email, create_at) values(1,'NOMBRE 1','APELLIDO 1','corre1@gmail.com','2016-01-30');
INSERT INTO clientes(id, nombre, apellido, email, create_at) values(2,'NOMBRE 2','APELLIDO 2','corre2@gmail.com','2017-02-15');
INSERT INTO clientes(id, nombre, apellido, email, create_at) values(3,'NOMBRE 3','APELLIDO 3','corre3@gmail.com','2018-10-16');
INSERT INTO clientes(id, nombre, apellido, email, create_at) values(4,'NOMBRE 4','APELLIDO 4','corre4@gmail.com','2019-12-19');

INSERT INTO roles(nombre) VALUES ('ROLE_USER');
INSERT INTO roles(nombre) VALUES ('ROLE_ADMIN');


INSERT INTO centro_costo(id, nombre,nombre_abreviatura) values(1,'Sin Centro de Costo','');
INSERT INTO centro_costo(id, nombre,nombre_abreviatura) values(2,'Cuenta de Pérdida','P');
INSERT INTO centro_costo(id, nombre,nombre_abreviatura) values(3,'Cuenta de Ganancia','G');

INSERT INTO mes_to_job(id, nombre_to_job,codigo,estado) values(1,'Apertura',0,1);
INSERT INTO mes_to_job(id, nombre_to_job,codigo,estado) values(2,'Enero',1,1);
INSERT INTO mes_to_job(id, nombre_to_job,codigo,estado) values(3,'Febrero',2,1);
INSERT INTO mes_to_job(id, nombre_to_job,codigo,estado) values(4,'Marzo',3,1);
INSERT INTO mes_to_job(id, nombre_to_job,codigo,estado) values(5,'Abril',4,1);
INSERT INTO mes_to_job(id, nombre_to_job,codigo,estado) values(6,'Mayo',5,1);
INSERT INTO mes_to_job(id, nombre_to_job,codigo,estado) values(7,'Junio',6,1);
INSERT INTO mes_to_job(id, nombre_to_job,codigo,estado) values(8,'Julio',7,1);
INSERT INTO mes_to_job(id, nombre_to_job,codigo,estado) values(9,'Agosto',8,1);
INSERT INTO mes_to_job(id, nombre_to_job,codigo,estado) values(10,'Septiembre',9,1);
INSERT INTO mes_to_job(id, nombre_to_job,codigo,estado) values(11,'Octubre',10,1);
INSERT INTO mes_to_job(id, nombre_to_job,codigo,estado) values(12,'Noviembre',11,1);
INSERT INTO mes_to_job(id, nombre_to_job,codigo,estado) values(13,'Diciembre',12,1);
INSERT INTO mes_to_job(id, nombre_to_job,codigo,estado) values(14,'Cierre',13,1);

INSERT INTO periodo_anio(id,anio_periodo,estado) values(1,'2019',1);
INSERT INTO periodo(id,estado,mes_to_job_id,periodo_anio_id) values(1,1,13,1);
