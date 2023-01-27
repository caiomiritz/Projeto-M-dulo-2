package br.com.ada.crud.controller.pais;

import br.com.ada.crud.Constantes;
import br.com.ada.crud.model.pais.dao.PaisDAO;
import br.com.ada.crud.model.pais.dao.impl.PaisBinariaDAO;
import br.com.ada.crud.model.pais.dao.impl.PaisXmlDAO;
import br.com.ada.crud.persistencia.PersistenciaTipo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PaisDAOFactory {

    private static final PaisDAOFactory INSTANCE = new PaisDAOFactory();
    private static final String PERSISTENCIA_TIPO = "pais.persistencia.tipo";

    private PersistenciaTipo tipo;

    private PaisDAOFactory() {
    }

    public static PaisDAOFactory getInstance() {
        return INSTANCE;
    }

    public PaisDAO criar() {

        if (tipo == null) {
            carregarTipoPersistencia();
        }
        PaisDAO paisDAO = null;

        if (tipo == PersistenciaTipo.BINARIO) {
            paisDAO = new PaisBinariaDAO();
        }
        else if (tipo == PersistenciaTipo.XML) {
            paisDAO = new PaisXmlDAO();
        }
        return paisDAO;
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
