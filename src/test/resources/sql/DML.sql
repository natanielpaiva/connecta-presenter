-- Datasource que os testes de análise dependem
INSERT INTO CONNECTA.TB_DATASOURCE (PK_DATASOURCE, DS_DATASOURCE, NM_DATASOURCE, TP_DATASOURCE, TXT_SENHA, TXT_USUARIO)
    VALUES (CONNECTA.TB_DATASOURCE_SEQ.nextval, 'Descrição teste', 'Teste', 'TESTE', '123456', 'usuario');

-- Análise a ser buscada
INSERT INTO CONNECTA.TB_ANALYSIS (PK_ANALYSIS, DS_ANALYSIS, NM_ANALYSIS, TP_ANALYSIS, FK_DATASOURCE)
    VALUES (CONNECTA.TB_ANALYSIS_SEQ.nextval, 'Teste descricao', 'Teste', 'TESTE', 1);
-- Análise a ser editada
INSERT INTO CONNECTA.TB_ANALYSIS (PK_ANALYSIS, DS_ANALYSIS, NM_ANALYSIS, TP_ANALYSIS, FK_DATASOURCE)
    VALUES (CONNECTA.TB_ANALYSIS_SEQ.nextval, 'Teste descricao', 'Teste', 'TESTE', 1);
-- Análise a ser deletada
INSERT INTO CONNECTA.TB_ANALYSIS (PK_ANALYSIS, DS_ANALYSIS, NM_ANALYSIS, TP_ANALYSIS, FK_DATASOURCE)
    VALUES (99, 'Teste descricao', 'Teste', 'TESTE', 1);


-- SingleSource url a ser editado
INSERT INTO CONNECTA.TB_SINGLE_SOURCE (PK_SINGLE_SOURCE, NM_SINGLE_SOURCE, DS_SINGLE_SOURCE, TP_SINGLE_SOURCE)
    VALUES(CONNECTA.TB_SINGLE_SOURCE_SEQ.nextval, 'Teste Url', 'Descrição url', 'URL');

INSERT INTO CONNECTA.TB_URL_WEB (PK_SINGLE_SOURCE, TP_URL_WEB, TXT_URL, TXT_USER, TXT_PASSWORD)
    VALUES(1, 'jpg', 'teste.com.br', 'teste user', 'aaaa');



-- SingleSource file a ser editado
INSERT INTO CONNECTA.TB_SINGLE_SOURCE (PK_SINGLE_SOURCE, NM_SINGLE_SOURCE, DS_SINGLE_SOURCE, TP_SINGLE_SOURCE)
    VALUES(CONNECTA.TB_SINGLE_SOURCE_SEQ.nextval, 'Teste File', 'Descrição File', 'FILE');

INSERT INTO CONNECTA.TB_BINARY_FILE
(PK_BINARY_FILE, BINARY_FILE)
VALUES(CONNECTA.TB_BINARY_FILE_SEQ.nextval, RAWTOHEX(' PNG

   
IHDR          wS    	pHYs          tIME !      tEXtComment Created with GIMPW    IDAT c   ?     Y     IEND B` '));

INSERT INTO CONNECTA.TB_FILE_SINGLE_SOURCE
(PK_SINGLE_SOURCE, TP_FILE, NM_FILE, URL_FILE, TXT_USER, TXT_PASSWORD, FK_BINARY_FILE)
VALUES(2, 'IMAGE', 'Teste File', '', '', '', 1);

-- Attributes
INSERT INTO CONNECTA.TB_ATTRIBUTE
    (PK_ATTRIBUTE, NM_ATTRIBUTE, DS_ATTRIBUTE, TP_ATTRIBUTE)
    VALUES(CONNECTA.TB_ATTRIBUTE_SEQ.nextval, 'name', '...', 'TEXT');
INSERT INTO CONNECTA.TB_ATTRIBUTE
    (PK_ATTRIBUTE, NM_ATTRIBUTE, DS_ATTRIBUTE, TP_ATTRIBUTE)
    VALUES(CONNECTA.TB_ATTRIBUTE_SEQ.nextval, 'description', '...', 'MAP');
INSERT INTO CONNECTA.TB_ATTRIBUTE
    (PK_ATTRIBUTE, NM_ATTRIBUTE, DS_ATTRIBUTE, TP_ATTRIBUTE)
    VALUES(CONNECTA.TB_ATTRIBUTE_SEQ.nextval, 'type', '...', 'DATE');


-- -- -- SingleSource Attribute
INSERT INTO CONNECTA.TA_ATTR_SNGL_SRC
(PK_ATTR_SNGL_SRC, TXT_VALUE, FK_ATTRIBUTE, FK_SINGLE_SOURCE)
VALUES(CONNECTA.TA_ATTR_SNGL_SRC_SEQ.nextval, '90', 1, 1);
INSERT INTO CONNECTA.TA_ATTR_SNGL_SRC
(PK_ATTR_SNGL_SRC, TXT_VALUE, FK_ATTRIBUTE, FK_SINGLE_SOURCE)
VALUES(CONNECTA.TA_ATTR_SNGL_SRC_SEQ.nextval, '920', 2, 1);