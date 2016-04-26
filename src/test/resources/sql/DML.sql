-- Datasource que os testes de análise dependem
INSERT INTO PRESENTER2.TB_DATASOURCE ( DS_DATASOURCE, NM_DATASOURCE, TP_DATASOURCE, TXT_SENHA, TXT_USUARIO, NM_DOMAIN)
    VALUES ( 'Descrição teste', 'Teste', 'DATABASE', '123456', 'usuario', 'cds');
--Datasources a serem deletados
INSERT INTO PRESENTER2.TB_DATASOURCE (PK_DATASOURCE, DS_DATASOURCE, NM_DATASOURCE, TP_DATASOURCE, TXT_SENHA, TXT_USUARIO, NM_DOMAIN)
    VALUES (98, 'Descrição teste', 'Teste', 'DATABASE', '123456', 'usuario', 'cds');
INSERT INTO PRESENTER2.TB_DATASOURCE (PK_DATASOURCE, DS_DATASOURCE, NM_DATASOURCE, TP_DATASOURCE, TXT_SENHA, TXT_USUARIO, NM_DOMAIN)
    VALUES (99, 'Descrição teste', 'Teste', 'DATABASE', '123456', 'usuario', 'cds');
INSERT INTO PRESENTER2.TB_DATASOURCE (PK_DATASOURCE, DS_DATASOURCE, NM_DATASOURCE, TP_DATASOURCE, TXT_SENHA, TXT_USUARIO, NM_DOMAIN)
    VALUES (100, 'Descrição teste', 'Teste', 'DATABASE', '123456', 'usuario', 'cds');

-- Análise a ser buscada
INSERT INTO PRESENTER2.TB_ANALYSIS ( DS_ANALYSIS, NM_ANALYSIS, FK_DATASOURCE, NM_DOMAIN)
    VALUES ( 'Teste descricao', 'Teste', 1, 'cds');
-- Análise a ser editada
INSERT INTO PRESENTER2.TB_ANALYSIS ( DS_ANALYSIS, NM_ANALYSIS, FK_DATASOURCE, NM_DOMAIN)
    VALUES ( 'Teste descricao', 'Teste', 1, 'cds');
-- Análises a serem deletadas
INSERT INTO PRESENTER2.TB_ANALYSIS (PK_ANALYSIS, DS_ANALYSIS, NM_ANALYSIS, FK_DATASOURCE, NM_DOMAIN)
    VALUES (98, 'Teste descricao', 'Teste', 1, 'cds');
INSERT INTO PRESENTER2.TB_ANALYSIS (PK_ANALYSIS, DS_ANALYSIS, NM_ANALYSIS, FK_DATASOURCE, NM_DOMAIN)
    VALUES (99, 'Teste descricao', 'Teste', 1, 'cds');
INSERT INTO PRESENTER2.TB_ANALYSIS (PK_ANALYSIS, DS_ANALYSIS, NM_ANALYSIS, FK_DATASOURCE, NM_DOMAIN)
    VALUES (100, 'Teste descricao', 'Teste', 1, 'cds');

-- SingleSource url a ser editado
INSERT INTO PRESENTER2.TB_SINGLE_SOURCE ( PK_SINGLE_SOURCE, NM_SINGLE_SOURCE, DS_SINGLE_SOURCE, TP_SINGLE_SOURCE, NM_DOMAIN)
    VALUES(1, 'Teste Url', 'Descrição url', 'URL', 'cds');

INSERT INTO PRESENTER2.TB_URL_WEB (PK_SINGLE_SOURCE, TP_URL_WEB, TXT_URL, TXT_USER, TXT_PASSWORD)
    VALUES(1, 'jpg', 'teste.com.br', 'teste user', 'aaaa');

-- SingleSource file a ser editado
INSERT INTO PRESENTER2.TB_SINGLE_SOURCE (PK_SINGLE_SOURCE, NM_SINGLE_SOURCE, DS_SINGLE_SOURCE, TP_SINGLE_SOURCE, NM_DOMAIN)
    VALUES(2, 'Teste File', 'Descrição File', 'FILE', 'cds');

INSERT INTO PRESENTER2.TB_SINGLE_SOURCE (PK_SINGLE_SOURCE, NM_SINGLE_SOURCE, DS_SINGLE_SOURCE, TP_SINGLE_SOURCE, NM_DOMAIN)
    VALUES(666, 'Teste File', 'Descrição File', 'FILE', 'cds');
    -- Mídias a serem deletadas
INSERT INTO PRESENTER2.TB_SINGLE_SOURCE (PK_SINGLE_SOURCE, NM_SINGLE_SOURCE, DS_SINGLE_SOURCE, TP_SINGLE_SOURCE, NM_DOMAIN)
    VALUES(1098, 'Teste File', 'Descrição File', 'FILE', 'cds');
INSERT INTO PRESENTER2.TB_SINGLE_SOURCE (PK_SINGLE_SOURCE, NM_SINGLE_SOURCE, DS_SINGLE_SOURCE, TP_SINGLE_SOURCE, NM_DOMAIN)
    VALUES(1099, 'Teste File', 'Descrição File', 'FILE', 'cds');
INSERT INTO PRESENTER2.TB_SINGLE_SOURCE (PK_SINGLE_SOURCE, NM_SINGLE_SOURCE, DS_SINGLE_SOURCE, TP_SINGLE_SOURCE, NM_DOMAIN)
    VALUES(1100, 'Teste File', 'Descrição File', 'FILE', 'cds');

INSERT INTO PRESENTER2.TB_BINARY_FILE
( BINARY_FILE)
VALUES(RAWTOHEX(' PNG

   
IHDR          wS    	pHYs          tIME !      tEXtComment Created with GIMPW    IDAT c   ?     Y     IEND B` '));

INSERT INTO PRESENTER2.TB_FILE_SINGLE_SOURCE
(PK_SINGLE_SOURCE, TP_FILE, NM_FILE, URL_FILE, TXT_USER, TXT_PASSWORD, FK_BINARY_FILE)
VALUES(2, 'PNG', 'Teste File.png', '', '', '', 1);

-- Attributes
INSERT INTO PRESENTER2.TB_ATTRIBUTE
    ( NM_ATTRIBUTE, DS_ATTRIBUTE, TP_ATTRIBUTE)
    VALUES( 'name', '...', 'TEXT');
INSERT INTO PRESENTER2.TB_ATTRIBUTE
    ( NM_ATTRIBUTE, DS_ATTRIBUTE, TP_ATTRIBUTE)
    VALUES( 'description', '...', 'MAP');
INSERT INTO PRESENTER2.TB_ATTRIBUTE
    ( NM_ATTRIBUTE, DS_ATTRIBUTE, TP_ATTRIBUTE)
    VALUES( 'type', '...', 'DATE');


-- -- -- SingleSource Attribute
INSERT INTO PRESENTER2.TA_ATTR_SNGL_SRC
( TXT_VALUE, FK_ATTRIBUTE, FK_SINGLE_SOURCE)
VALUES( '90', 1, 1);
INSERT INTO PRESENTER2.TA_ATTR_SNGL_SRC
( TXT_VALUE, FK_ATTRIBUTE, FK_SINGLE_SOURCE)
VALUES( '920', 2, 1);


-- -- -- Group
INSERT INTO PRESENTER2.TB_GROUP ( DS_GROUP, NM_GROUP, NM_DOMAIN)
    VALUES( 'Group Description', 'New Group', 'cds');
    -- Grupo a ser apagado
INSERT INTO PRESENTER2.TB_GROUP (PK_GROUP, DS_GROUP, NM_GROUP, NM_DOMAIN)
    VALUES(999, 'Group Description', 'New Group', 'cds');
    -- Grupos a serem deletados em massa
INSERT INTO PRESENTER2.TB_GROUP (PK_GROUP, DS_GROUP, NM_GROUP, NM_DOMAIN)
    VALUES(98, 'Group Description', 'New Group', 'cds');
INSERT INTO PRESENTER2.TB_GROUP (PK_GROUP, DS_GROUP, NM_GROUP, NM_DOMAIN)
    VALUES(99, 'Group Description', 'New Group', 'cds');
INSERT INTO PRESENTER2.TB_GROUP (PK_GROUP, DS_GROUP, NM_GROUP, NM_DOMAIN)
    VALUES(100, 'Group Description', 'New Group', 'cds');

-- Hierarquias
    -- Hierarquias a serem deletadas em massa
INSERT INTO PRESENTER2.TB_HIERARCHY(PK_HIERARCHY, NAME)
    VALUES (98, 'Hierarquia');
INSERT INTO PRESENTER2.TB_HIERARCHY(PK_HIERARCHY, NAME)
    VALUES (99, 'Hierarquia');
INSERT INTO PRESENTER2.TB_HIERARCHY(PK_HIERARCHY, NAME)
    VALUES (100, 'Hierarquia');


-- SingleSource QueryBuilder
INSERT INTO PRESENTER2.TB_SINGLE_SOURCE (PK_SINGLE_SOURCE, NM_SINGLE_SOURCE, DS_SINGLE_SOURCE, TP_SINGLE_SOURCE, NM_DOMAIN)
    VALUES(999, 'Teste Url', 'Descrição url', 'FILE', 'cds');
INSERT INTO PRESENTER2.TB_FILE_SINGLE_SOURCE
    (PK_SINGLE_SOURCE, TP_FILE, NM_FILE, URL_FILE, TXT_USER, TXT_PASSWORD, FK_BINARY_FILE)
    VALUES(999, 'PNG', 'Teste File.png', '', '', '', 1);
INSERT INTO PRESENTER2.TA_ATTR_SNGL_SRC ( TXT_VALUE, FK_ATTRIBUTE, FK_SINGLE_SOURCE)
    VALUES( 'Teste', 1, 999);


-- Criando os SingleSources(Mídias)
INSERT INTO PRESENTER2.TB_ATTRIBUTE
    (PK_ATTRIBUTE, NM_ATTRIBUTE, DS_ATTRIBUTE, TP_ATTRIBUTE)
    VALUES(99, 'cidade', '...', 'TEXT');


INSERT INTO PRESENTER2.TB_ATTRIBUTE
    (PK_ATTRIBUTE, NM_ATTRIBUTE, DS_ATTRIBUTE, TP_ATTRIBUTE)
    VALUES(100, 'lucro', '...', 'TEXT');

-- Single Source 1
INSERT INTO PRESENTER2.TB_BINARY_FILE
(PK_BINARY_FILE, BINARY_FILE)
VALUES(99, RAWTOHEX(' PNG

   
IHDR          wS    	pHYs          tIME !      tEXtComment Created with GIMPW    IDAT c   ?     Y     IEND B` '));

INSERT INTO PRESENTER2.TB_SINGLE_SOURCE (PK_SINGLE_SOURCE, NM_SINGLE_SOURCE, DS_SINGLE_SOURCE, TP_SINGLE_SOURCE, NM_DOMAIN)
    VALUES(99, 'Imagem 1', 'Descrição da imagem 1', 'FILE', 'cds');
INSERT INTO PRESENTER2.TB_FILE_SINGLE_SOURCE
    (PK_SINGLE_SOURCE, TP_FILE, NM_FILE, URL_FILE, TXT_USER, TXT_PASSWORD, FK_BINARY_FILE)
    VALUES(99, 'PNG', 'Teste File.png', '', '', '', 99);

-- Single Source 2
INSERT INTO PRESENTER2.TB_BINARY_FILE
(PK_BINARY_FILE, BINARY_FILE)
VALUES(100, RAWTOHEX(' PNG

   
IHDR          wS    	pHYs          tIME !      tEXtComment Created with GIMPW    IDAT c   ?     Y     IEND B` '));

INSERT INTO PRESENTER2.TB_SINGLE_SOURCE (PK_SINGLE_SOURCE, NM_SINGLE_SOURCE, DS_SINGLE_SOURCE, TP_SINGLE_SOURCE, NM_DOMAIN)
    VALUES(100, 'Imagem 2', 'Descrição da imagem 2', 'FILE', 'cds');
INSERT INTO PRESENTER2.TB_FILE_SINGLE_SOURCE
    (PK_SINGLE_SOURCE, TP_FILE, NM_FILE, URL_FILE, TXT_USER, TXT_PASSWORD, FK_BINARY_FILE)
    VALUES(100, 'PNG', 'Teste File.png', '', '', '', 100);
    
INSERT INTO PRESENTER2.TA_ATTR_SNGL_SRC ( TXT_VALUE, FK_ATTRIBUTE, FK_SINGLE_SOURCE)
        VALUES( 'Brasília', 99, 99);

INSERT INTO PRESENTER2.TA_ATTR_SNGL_SRC ( TXT_VALUE, FK_ATTRIBUTE, FK_SINGLE_SOURCE)
        VALUES( 'Brasília', 99, 100);

INSERT INTO PRESENTER2.TA_ATTR_SNGL_SRC ( TXT_VALUE, FK_ATTRIBUTE, FK_SINGLE_SOURCE)
        VALUES( '4000', 100, 99);

INSERT INTO PRESENTER2.TA_ATTR_SNGL_SRC ( TXT_VALUE, FK_ATTRIBUTE, FK_SINGLE_SOURCE)
        VALUES( '1000', 100, 100);

-- Testes dos visualizadores
INSERT INTO PRESENTER2.TB_ANALYSIS_COLUMNS ( FML_COLUMN, LB_COLUMN, NM_COLUMN, FK_ANALYSIS)
    VALUES( 'TBTAL.FULANO', 'FFA', 'FULANO', 1 );
INSERT INTO PRESENTER2.TB_ANALYSIS_COLUMNS ( FML_COLUMN, LB_COLUMN, NM_COLUMN, FK_ANALYSIS)
    VALUES( 'TBTAL.COLUNATAL', 'LALA', 'COLUNATAL', 2 );
    -- Visualizadores a serem deletados
INSERT INTO PRESENTER2.TB_ANALYSIS_COLUMNS (PK_ANALYSIS_COLUMNS, FML_COLUMN, LB_COLUMN, NM_COLUMN, FK_ANALYSIS)
    VALUES(98, 'TBTAL.FULANO', 'FFA', 'FULANO', 1 );
INSERT INTO PRESENTER2.TB_ANALYSIS_COLUMNS (PK_ANALYSIS_COLUMNS, FML_COLUMN, LB_COLUMN, NM_COLUMN, FK_ANALYSIS)
    VALUES(99, 'TBTAL.FULANO', 'FFA', 'FULANO', 1 );
INSERT INTO PRESENTER2.TB_ANALYSIS_COLUMNS (PK_ANALYSIS_COLUMNS, FML_COLUMN, LB_COLUMN, NM_COLUMN, FK_ANALYSIS)
    VALUES(100, 'TBTAL.FULANO', 'FFA', 'FULANO', 1 );


    -- Testes de retorno de visualizadores
        -- Visualizador de Análise
INSERT INTO PRESENTER2.TB_VIEWER (PK_VIEWER, NM_VIEWER, TP_VIEWER, NM_DOMAIN)
    VALUES (50, 'Analysis viewer', 'ANALYSIS', 'cds');
INSERT INTO PRESENTER2.TB_ANALYSIS_VIEWER (PK_VIEWER, NU_INTRVL_ATLZCO, NU_MAX_LINHAS, NM_LABEL)
    VALUES (50, 1000, 5, 'Analysis label');
INSERT INTO PRESENTER2.TB_ANALYSIS (PK_ANALYSIS, FK_DATASOURCE)
    VALUES (50, NULL);
INSERT INTO PRESENTER2.TB_ANALYSIS_COLUMNS (PK_ANALYSIS_COLUMNS, FK_ANALYSIS, TP_COLUMN, NM_COLUMN, FML_COLUMN, LB_COLUMN)
    VALUES (50, 50, NULL, 'nome', 'tabela.nome', 'Nome do fulano');
INSERT INTO PRESENTER2.TA_ANALYSIS_VW_COLUMNS
    (PK_ANALYSIS_VW_COLUMNS, FK_ANALYSIS_VIEWER, FK_ANALYSIS_COLUMNS, TP_COLUMN, TP_ORDER, TXT_MASK_FORMAT, TP_TYPE)
    VALUES (50, 50, 50, 'NUMBER', NULL, NULL, 'METRIC');
        -- Visualizador de Mídia
INSERT INTO PRESENTER2.TB_VIEWER (PK_VIEWER, NM_VIEWER, TP_VIEWER, NM_DOMAIN)
    VALUES (51, 'Single source viewer', 'SINGLESOURCE', 'cds');
INSERT INTO PRESENTER2.TB_SNGL_SRC_VIEWER (PK_VIEWER, FK_SINGLE_SOURCE)
    VALUES (51, 100);
        -- Visualizador de Grupo de Mídia
INSERT INTO PRESENTER2.TB_GROUP (PK_GROUP, NM_GROUP, DS_GROUP, NM_DOMAIN)
    VALUES (52, 'New Group', 'Group Description', 'cds');
INSERT INTO PRESENTER2.TB_VIEWER (PK_VIEWER, NM_VIEWER, TP_VIEWER, NM_DOMAIN)
    VALUES (52, 'Single source group viewer', 'SINGLESOURCE_GROUP', 'cds');
INSERT INTO PRESENTER2.TA_SNGL_SRC_VW_GROUP (PK_VIEWER, FK_GROUP, TP_VISUALIZATION)
    VALUES (52, 52, 'GALLERY');
    -- Visualizador combinado
INSERT INTO PRESENTER2.TB_VIEWER (PK_VIEWER, NM_VIEWER, TP_VIEWER, NM_DOMAIN)
    VALUES (53, 'Combined viewer', 'COMBINED', 'cds');
    
    -- TESTES RESULTADO DE ANÁLISE
INSERT INTO PRESENTER2.TB_DATASOURCE (PK_DATASOURCE, NM_DATASOURCE, TP_DATASOURCE, TXT_USUARIO, TXT_SENHA)
    VALUES (50, 'Banco MySQL', 'DATABASE', 'root', 'root');
INSERT INTO PRESENTER2.TB_DATABASE_DS (PK_DATASOURCE, CD_DRIVER, TXT_SERVER, TXT_PORT, TXT_SCHEMA)
    VALUES (50, 'MYSQL', '192.168.33.10', 3306, 'memorando');
INSERT INTO PRESENTER2.TB_DATASOURCE (PK_DATASOURCE, NM_DATASOURCE, TP_DATASOURCE, TXT_USUARIO, TXT_SENHA)
    VALUES (51, 'Banco Oracle', 'DATABASE', 'presenter2', 'cds312');
INSERT INTO PRESENTER2.TB_DATABASE_DS (PK_DATASOURCE, CD_DRIVER, TXT_SERVER, TXT_PORT, TXT_SID, TXT_SCHEMA)
    VALUES (51, 'ORACLE_SID', '192.168.3.14', 1521, 'cdsdev', 'PRESENTER2');
