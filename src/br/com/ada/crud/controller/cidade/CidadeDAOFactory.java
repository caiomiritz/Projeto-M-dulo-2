package br.com.ada.crud.controller.cidade;

import br.com.ada.crud.Constantes;
import br.com.ada.crud.model.cidade.dao.CidadeDAO;
import br.com.ada.crud.model.cidade.dao.impl.CidadeBinariaDAO;
import br.com.ada.crud.model.cidade.dao.impl.CidadeXmlDAO;
import br.com.ada.crud.persistencia.PersistenciaTipo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CidadeDAOFactory {

    private static final CidadeDAOFactory INSTANCE = new CidadeDAOFactory();
    private static final String PERSISTENCIA_TIPO = "cidade.persistencia.tipo";

    private PersistenciaTipo tipo;

    private CidadeDAOFactory() {
    }

    public static CidadeDAOFactory getInstance() {
        return INSTANCE;
    }

    public CidadeDAO criar() {

        if (tipo == null) {
            carregarTipoPersistencia();
        }
        CidadeDAO cidadeDAO = null;

        if (tipo == PersistenciaTipo.BINARIO) {
            cidadeDAO = new CidadeBinariaDAO();
        }
        else if (tipo == PersistenciaTipo.XML) {
            cidadeDAO = new CidadeXmlDAO();
        }
        return cidadeDAO;
    }

    private void carregarTipoPersistencia() {

        try {

            Properties properties = new Properties();
            properties.load(new FileInputStream(Constantes.ARQUIVO_PROPRIEDADES));

            String valorNoArquivo = properties.getProperty(PERSISTENCIA_TIPO);
            tipo = PersistenciaTipo.valueOf(valorNoArquivo);
        }
        catch (IOException ex) {

            throw new RuntimeException("Não foi possível ler o arquivo de configurações", ex);
        }
    }
}
