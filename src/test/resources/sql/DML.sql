INSERT INTO CONNECTA.TB_DATASOURCE (PK_DATASOURCE, DS_DATASOURCE, NM_DATASOURCE, TP_DATASOURCE, TXT_SENHA, TXT_USUARIO)
    VALUES (1, 'Descrição teste', 'Teste', 'TESTE', '123456', 'usuario');

INSERT INTO CONNECTA.TB_ANALYSIS (PK_ANALYSIS, DS_ANALYSIS, NM_ANALYSIS, TP_ANALYSIS, FK_DATASOURCE)
    VALUES (1, 'Teste descricao', 'Teste', 'TESTE', 1);


-- Análise que será deletada
INSERT INTO CONNECTA.TB_ANALYSIS (PK_ANALYSIS, DS_ANALYSIS, NM_ANALYSIS, TP_ANALYSIS, FK_DATASOURCE)
    VALUES (99, 'Teste descricao', 'Teste', 'TESTE', 1);


-- INSERT INTO CONNECTA.C_APPLICATIONS (ID, NAME, TITLE, HOST)
--     VALUES
-- (CONNECTA.SEQ_APPLICATION.nextval, 'presenter', 'Presenter', 'http://localhost:7001/presenter2-dev'),
-- (CONNECTA.SEQ_APPLICATION.nextval, 'collector', 'Collector', 'http://localhost:7001/collector2-dev'),
-- (CONNECTA.SEQ_APPLICATION.nextval, 'maps4',     'Maps4',     'http://localhost:7001/maps4-dev'     );